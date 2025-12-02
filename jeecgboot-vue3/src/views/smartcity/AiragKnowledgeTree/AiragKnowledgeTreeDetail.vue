<template>
  <div class="knowledge-detail-container">
    <a-layout style="height: 100%">
      <a-layout-sider width="300" :style="siderStyle">
        <div class="tree-header">
          <span class="tree-title">目录结构</span>
          <a-button type="link" @click="handleAddTreeNode">
            <Icon icon="ant-design:plus-outlined" />
          </a-button>
        </div>
        <a-tree
          class="directory-tree"
          :tree-data="treeData"
          :expanded-keys="expandedKeys"
          :selected-keys="selectedKeys"
          :field-names="fieldNames"
          @expand="onExpand"
          @select="onSelect"
        >
          <template #title="{ name }">
            <span>{{ name }}</span>
          </template>
        </a-tree>
      </a-layout-sider>
      
      <a-layout-content :style="contentStyle">
        <div class="file-list-container">
          <div class="toolbar">
            <a-button type="primary" @click="handleManualEntry">
              <Icon icon="ant-design:form-outlined" />手动录入
            </a-button>
            <a-button type="primary" @click="handleFileUpload">
              <Icon icon="ant-design:cloud-upload-outlined" />文件上传
            </a-button>
            <a-button type="primary" @click="handleDocLibraryUpload">
              <Icon icon="ant-design:project-outlined" />文档库上传
            </a-button>
          </div>
          
          <BasicTable @register="registerTable">
            <template #action="{ record }">
              <TableAction :actions="getTableAction(record)" />
            </template>
          </BasicTable>
        </div>
      </a-layout-content>
    </a-layout>
    
    <!-- 添加/编辑目录节点模态框 -->
    <AiragKnowledgeTreeModal @register="registerTreeModal" @success="loadTreeData" />
    
    <!-- 文件操作模态框 -->
    <AiragKnowledgeDocModal @register="registerDocModal" @success="reloadTable" />
  </div>
</template>

<script lang="ts" setup>
  import { ref, onMounted } from 'vue';
  import { useRoute } from 'vue-router';
  import { BasicTable, useTable, TableAction } from '/@/components/Table';
  import { useModal } from '/@/components/Modal';
  import { getTree } from './AiragKnowledgeTree.api';
  import { knowledgeDocList, knowledgeRebuildDoc, knowledgeDeleteBatchDoc } from '../../super/airag/aiknowledge/AiKnowledgeBase.api';
  import AiragKnowledgeTreeModal from './components/AiragKnowledgeTreeModal.vue';
  import AiragKnowledgeDocModal from './components/AiragKnowledgeDocModal.vue';
  import Icon from '@/components/Icon';
  import { useMessage } from '/@/hooks/web/useMessage';

  const route = useRoute();
  const { createMessage } = useMessage();

  // 获取知识库ID
  const knowledgeId = route.query.knowledgeId as string;

  // 树结构相关
  const treeData = ref<any[]>([]);
  const expandedKeys = ref<string[]>([]);
  const selectedKeys = ref<string[]>([]);
  const fieldNames = { children: 'children', title: 'name', key: 'id' };

  // 布局样式
  const siderStyle = {
    background: '#fff',
    borderRight: '1px solid #eee',
    padding: '16px',
    height: '100%'
  };

  const contentStyle = {
    background: '#fff',
    padding: '16px',
    height: '100%',
    overflow: 'auto'
  };

  // 注册模态框
  const [registerTreeModal, { openModal: openTreeModal }] = useModal();
  const [registerDocModal, { openModal: openDocModal }] = useModal();

  // 文件列表表格配置
  const [registerTable, { reload: reloadTable }] = useTable({
    title: '文件列表',
    api: fetchFileList,
    columns: [
      {
        title: '文件名称',
        dataIndex: 'title',
        width: 200,
      },
      {
        title: '状态',
        dataIndex: 'status',
        width: 100,
        customRender: ({ record }) => {
          const statusMap: Record<string, string> = {
            'draft': '草稿',
            'building': '构建中',
            'complete': '已完成',
            'failed': '失败'
          };
          return statusMap[record.status] || record.status;
        }
      },
      {
        title: '创建时间',
        dataIndex: 'createTime',
        width: 180,
      },
      {
        title: '操作',
        dataIndex: 'action',
        width: 150,
        slots: { customRender: 'action' },
      }
    ],
    pagination: true,
    striped: false,
    useSearchForm: false,
    showTableSetting: false,
    bordered: true,
    showIndexColumn: false,
    canResize: false,
    actionColumn: {
      width: 120,
      title: '操作',
      dataIndex: 'action',
      slots: { customRender: 'action' },
    },
  });

  // 获取文件列表数据
  async function fetchFileList(params: Record<string, any>) {
    if (!knowledgeId) {
      return { records: [], total: 0 };
    }

    const queryParams = {
      ...params,
      knowledgeId: knowledgeId,
      pid: selectedKeys.value.length > 0 ? selectedKeys.value[0] : null
    };

    try {
      const res = await knowledgeDocList(queryParams);
      if (res.success) {
        return {
          records: res.result.records || [],
          total: res.result.total || 0
        };
      }
    } catch (error) {
      console.error('获取文件列表失败:', error);
    }
    return { records: [], total: 0 };
  }

  // 加载目录树数据
  async function loadTreeData() {
    if (!knowledgeId) return;

    try {
      const res = await getTree({ knowledgeId });
      if (res) {
        // 将平铺数据转换为树形结构
        treeData.value = buildTreeData(res.records || []);
        // 默认展开所有节点
        expandedKeys.value = getAllKeys(treeData.value);
      }
    } catch (error) {
      console.error('加载目录树失败:', error);
    }
  }

  // 将平铺数据转换为树形结构
  interface TreeNode {
    id: string;
    pid: string;
    name: string;
    children: TreeNode[];
    [key: string]: any;
  }

  function buildTreeData(data: any[]): TreeNode[] {
    const map: Record<string, TreeNode> = {};
    const roots: TreeNode[] = [];

    // 初始化映射
    data.forEach(item => {
      map[item.id] = { ...item, children: [] };
    });

    // 构建树结构
    data.forEach(item => {
      const node = map[item.id];
      if (item.pid === '0' || !item.pid) {
        roots.push(node);
      } else {
        const parent = map[item.pid];
        if (parent) {
          parent.children.push(node);
        }
      }
    });

    return roots;
  }

  // 获取所有节点的key
  function getAllKeys(nodes: TreeNode[]): string[] {
    const keys: string[] = [];
    function traverse(items: TreeNode[]) {
      items.forEach(item => {
        keys.push(item.id);
        if (item.children && item.children.length > 0) {
          traverse(item.children);
        }
      });
    }
    traverse(nodes);
    return keys;
  }

  // 树节点展开事件
  function onExpand(expandedKeysValue: string[]) {
    expandedKeys.value = expandedKeysValue;
  }

  // 树节点选择事件
  function onSelect(selectedKeysValue: string[]) {
    selectedKeys.value = selectedKeysValue;
    // 重新加载文件列表
    reloadTable();
  }

  // 添加目录节点
  function handleAddTreeNode() {
    openTreeModal(true, {
      knowledgeId: knowledgeId,
      isUpdate: false
    });
  }

  // 手动录入
  function handleManualEntry() {
    openDocModal(true, {
      knowledgeId: knowledgeId,
      type: 'text'
    });
  }

  // 文件上传
  function handleFileUpload() {
    openDocModal(true, {
      knowledgeId: knowledgeId,
      type: 'file'
    });
  }

  // 文档库上传
  function handleDocLibraryUpload() {
    createMessage.info('文档库上传功能正在开发中...');
  }

  // 表格操作列
  function getTableAction(record: Record<string, any>) {
    return [
      {
        label: '向量化',
        onClick: () => handleVectorization(record),
        disabled: record.status === 'building'
      },
      {
        label: '编辑',
        onClick: () => handleEdit(record)
      },
      {
        label: '删除',
        popConfirm: {
          title: '确定要删除该文件吗？',
          confirm: () => handleDelete(record)
        }
      }
    ];
  }

  // 向量化操作
  async function handleVectorization(record: Record<string, any>) {
    try {
      knowledgeRebuildDoc({ docIds: record.id }, () => {
        createMessage.success('向量化任务已提交');
        reloadTable();
      });
    } catch (error: any) {
      createMessage.error('向量化失败: ' + (error.message || '未知错误'));
    }
  }

  // 编辑文件
  function handleEdit(record: Record<string, any>) {
    openDocModal(true, {
      record,
      isUpdate: true
    });
  }

  // 删除文件
  function handleDelete(record: Record<string, any>) {
    knowledgeDeleteBatchDoc({ ids: record.id }, () => {
      createMessage.success('删除成功');
      reloadTable();
    });
  }

  // 组件挂载时加载数据
  onMounted(() => {
    loadTreeData();
  });
</script>

<style scoped lang="less">
.knowledge-detail-container {
  height: 100%;
  background: #fff;
  
  .tree-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 16px;
    padding-bottom: 8px;
    border-bottom: 1px solid #eee;
    
    .tree-title {
      font-size: 16px;
      font-weight: 500;
    }
  }
  
  .directory-tree {
    background: #fff;
  }
  
  .file-list-container {
    height: 100%;
    display: flex;
    flex-direction: column;
    
    .toolbar {
      margin-bottom: 16px;
      
      button {
        margin-right: 8px;
      }
    }
  }
}
</style>