<template>
  <div class="knowledge-detail-container">
    <a-layout style="height: 100%">
      <!-- 左侧目录树 -->
      <a-layout-sider width="300" :style="siderStyle">
        <BasicTree
          ref="treeRef"
          title="知识库目录"
          toolbar
          search
          :treeData="treeData"
          :fieldNames="fieldNames"
          :clickRowToExpand="false"
          :beforeRightClick="getRightMenuList"
          @select="onTreeSelect"
        />
      </a-layout-sider>
      
      <!-- 右侧文档列表 -->
      <a-layout-content :style="contentStyle">
        <div class="file-list-container">
          <!-- 工具栏 -->
          <div class="toolbar">
            <a-button type="primary" @click="handleCreateText">
              <Icon icon="ant-design:form-outlined" />手动录入
            </a-button>
            <a-button type="primary" @click="handleCreateUpload">
              <Icon icon="ant-design:cloud-upload-outlined" />文件上传
            </a-button>
            <a-upload
              accept=".zip"
              name="file"
              :data="{ knowId: knowledgeId }"
              :showUploadList="false"
              :headers="headers"
              :beforeUpload="beforeUpload"
              :action="uploadUrl"
              @change="handleUploadChange"
              style="display: inline-block;"
            >
              <a-button type="primary">
                <Icon icon="ant-design:project-outlined" />文档库上传
              </a-button>
            </a-upload>
            <a-button type="primary" @click="handleHitTest">
              <Icon icon="ant-design:experiment-outlined" />命中测试
            </a-button>
          </div>
          
          <!-- 文档列表 -->
          <BasicTable @register="registerTable">
            <template #action="{ record }">
              <TableAction :actions="getTableAction(record)" />
            </template>
          </BasicTable>
        </div>
      </a-layout-content>
    </a-layout>
    
    <!-- 手工录入文本 -->
    <AiragKnowledgeDocTextModal @register="docTextRegister" @success="handleSuccess" />
    
    <!-- 文本明细 -->
    <AiTextDescModal @register="docTextDescRegister" />
  </div>
</template>

<script lang="ts">
  import { defineComponent, ref, onMounted } from 'vue';
  import { useRoute } from 'vue-router';
  import { BasicTree, TreeActionType } from '/@/components/Tree';
  import { BasicTable, useTable, TableAction } from '/@/components/Table';
  import { useModal } from '/@/components/Modal';
  import { getTree, knowledgeDocList, knowledgeRebuildDoc, knowledgeDeleteBatchDoc } from './AiKnowledgeBaseDetail.api';
  import AiragKnowledgeDocTextModal from './components/AiragKnowledgeDocTextModal.vue';
  import AiTextDescModal from './components/AiTextDescModal.vue';
  import Icon from '@/components/Icon';
  import { useMessage } from '@/hooks/web/useMessage';
  import { getHeaders } from '@/utils/common/compUtils';
  import { useGlobSetting } from '/@/hooks/setting';

  export default defineComponent({
    name: 'AiKnowledgeBaseDetailList',
    components: {
      BasicTree,
      BasicTable,
      TableAction,
      AiragKnowledgeDocTextModal,
      AiTextDescModal,
      Icon,
    },
    setup() {
      const route = useRoute();
      const { createMessage } = useMessage();
      
      // 知识库ID
      const knowledgeId = ref<string>('');
      
      // 树相关
      const treeRef = ref<Nullable<TreeActionType>>(null);
      const treeData = ref<any[]>([]);
      const fieldNames = { children: 'children', title: 'name', key: 'id' };
      const selectedTreeNode = ref<any>(null);
      
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
      
      // 上传相关
      const headers = getHeaders();
      const globSetting = useGlobSetting();
      const uploadUrl = ref<string>(globSetting.domainUrl + '/airag/knowledge/doc/import/zip');
      
      // 注册模态框
      const [docTextRegister, { openModal: docTextOpenModal }] = useModal();
      const [docTextDescRegister, { openModal: docTextDescOpenModal }] = useModal();
      
      // 表格配置
      const [registerTable, { reload: reloadTable }] = useTable({
        title: '',
        api: fetchDocumentList,
        columns: [
          {
            title: '文件名称',
            dataIndex: 'title',
            width: 200,
          },
          {
            title: '文档类型',
            dataIndex: 'type',
            width: 100,
            customRender: ({ record }: any) => {
              return record.type === 'text' ? '文本' : '文件';
            }
          },
          {
            title: '状态',
            dataIndex: 'status',
            width: 100,
            customRender: ({ record }: any) => {
              const statusMap: Record<string, string> = {
                draft: '草稿',
                building: '构建中',
                complete: '已完成',
                failed: '失败'
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
          width: 150,
          title: '操作',
          dataIndex: 'action',
          slots: { customRender: 'action' },
        },
      });
      
      // 获取文档列表
      async function fetchDocumentList(params: any) {
        // 如果没有选择节点，默认使用知识库ID
        const nodeId = selectedTreeNode.value?.id || '0';
        
        const queryParams = {
          ...params,
          knowledgeId: knowledgeId.value,
          nodeId: nodeId, // 添加节点ID参数
          column: 'createTime',
          order: 'desc'
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
          console.error('获取文档列表失败:', error);
        }
        return { records: [], total: 0 };
      }
      
      // 加载目录树数据
      async function loadTreeData() {
        if (!knowledgeId.value) return;
        
        try {
          const res = await getTree({ knowledgeId: knowledgeId.value });
          if (res.success) {
            // 将平铺数据转换为树形结构
            treeData.value = buildTreeData(res.result || []);
          }
        } catch (error) {
          console.error('加载目录树失败:', error);
        }
      }
      
      // 构建树形数据
      function buildTreeData(data: any[]): any[] {
        const map: Record<string, any> = {};
        const roots: any[] = [];

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

      function getRightMenuList(node: any): ContextMenuItem[] {
        return [
          {
            label: '新增',
            handler: () => {
              console.log('点击了新增', node);
            },
            icon: 'bi:plus',
          },
          {
            label: '删除',
            handler: () => {
              console.log('点击了删除', node);
            },
            icon: 'bx:bxs-folder-open',
          },
        ];
      }
      
      // 树节点选择事件
      function onTreeSelect(selectedKeys: string[], e: any) {
        if (selectedKeys.length > 0) {
          selectedTreeNode.value = e.node.dataRef;
        } else {
          selectedTreeNode.value = null;
        }
        // 重新加载文档列表
        reloadTable();
      }
      
      // 手动录入
      function handleCreateText() {
        docTextOpenModal(true, { 
          knowledgeId: knowledgeId.value, 
          type: "text",
          nodeId: selectedTreeNode.value?.id || '0'
        });
      }
      
      // 文件上传
      function handleCreateUpload() {
        docTextOpenModal(true, { 
          knowledgeId: knowledgeId.value, 
          type: "file",
          nodeId: selectedTreeNode.value?.id || '0'
        });
      }
      
      // 上传前事件
      function beforeUpload(file: any) {
        let fileType = file.type;
        if (fileType !== 'application/zip' && fileType !== 'application/x-zip-compressed') {
          createMessage.warning('请上传zip文件');
          return false;
        }
        return true;
      }
      
      // 文件上传回调事件
      function handleUploadChange(info: any) {
        let { file } = info;
        if (file.status === 'error' || (file.response && file.response.code == 500)) {
          createMessage.error(file.response?.message || `${file.name} 上传失败,请查看服务端日志`);
          return;
        }
        if (file.status === 'done') {
          if (!file.response.success) {
            createMessage.warning(file.response.message);
            return;
          }
          createMessage.success(file.response.message);
          handleSuccess();
        }
      }
      
      // 命中测试
      function handleHitTest() {
        createMessage.info('命中测试功能待实现');
      }
      
      // 表格操作列
      function getTableAction(record: any): any[] {
        return [
          {
            label: '向量化',
            onClick: handleVectorization.bind(null, record),
          },
          {
            label: '编辑',
            onClick: handleEdit.bind(null, record),
          },
          {
            label: '删除',
            color: 'error',
            popConfirm: {
              title: '是否确认删除',
              confirm: handleDelete.bind(null, record),
            },
          }
        ];
      }
      
      // 向量化
      async function handleVectorization(record: any) {
        try {
          await knowledgeRebuildDoc({ docIds: record.id }, handleSuccess);
          createMessage.success('向量化任务已提交');
        } catch (error: any) {
          createMessage.error('向量化失败: ' + (error.message || '未知错误'));
        }
      }
      
      // 编辑文件
      function handleEdit(record: any) {
        docTextOpenModal(true, {
          record,
          isUpdate: true
        });
      }
      
      // 删除文件
      function handleDelete(record: any) {
        knowledgeDeleteBatchDoc({ ids: record.id }, () => {
          createMessage.success('删除成功');
          reloadTable();
        });
      }
      
      // 操作成功回调
      function handleSuccess() {
        reloadTable();
      }
      
      // 组件挂载时加载数据
      onMounted(() => {
        // 获取知识库ID
        knowledgeId.value = route.query.knowledgeId as string || '';
        if (knowledgeId.value) {
          loadTreeData();
        }
      });
      
      return {
        treeRef,
        treeData,
        fieldNames,
        siderStyle,
        contentStyle,
        registerTable,
        docTextRegister,
        docTextDescRegister,
        knowledgeId,
        headers,
        uploadUrl,
        handleCreateText,
        handleCreateUpload,
        beforeUpload,
        handleUploadChange,
        handleHitTest,
        getTableAction,
        handleSuccess,
        onTreeSelect,
        getRightMenuList,
      };
    },
  });
</script>

<style scoped lang="less">
.knowledge-detail-container {
  height: 100%;
  background: #fff;
  
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
