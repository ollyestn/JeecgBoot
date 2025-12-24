<template>
  <div>
    <!--引用表格-->
   <BasicTable @register="registerTable" :rowSelection="rowSelection">
     <!--插槽:table标题-->
      <template #tableTitle>
          <a-button type="primary" v-auth="'smartcity:ss_week_report:add'"  @click="handleAdd" preIcon="ant-design:plus-outlined"> 新增</a-button>
          <a-button  type="primary" v-auth="'smartcity:ss_week_report:exportXls'"  preIcon="ant-design:export-outlined" @click="onExportXls"> 导出</a-button>
          <j-upload-button  type="primary" v-auth="'smartcity:ss_week_report:importExcel'"  preIcon="ant-design:import-outlined" @click="onImportXls">导入</j-upload-button>
          <a-dropdown v-if="selectedRowKeys.length > 0">
              <template #overlay>
                <a-menu>
                  <a-menu-item key="1" @click="batchHandleDelete">
                    <Icon icon="ant-design:delete-outlined"></Icon>
                    删除
                  </a-menu-item>
                </a-menu>
              </template>
              <a-button v-auth="'smartcity:ss_week_report:deleteBatch'">批量操作
                <Icon icon="mdi:chevron-down"></Icon>
              </a-button>
        </a-dropdown>
        <!-- 高级查询 -->
        <super-query :config="superQueryConfig" @search="handleSuperQuery" />
      </template>
       <!--操作栏-->
      <template #action="{ record }">
        <TableAction :actions="getTableAction(record)" :dropDownActions="getDropDownAction(record)"/>
      </template>
      <!--字段回显插槽-->
      <template v-slot:bodyCell="{ column, record, index, text }">
      </template>
    </BasicTable>
    <!-- 表单区域 -->
    <SsWeekReportModal @register="registerModal" @success="handleSuccess"></SsWeekReportModal>
  </div>
</template>

<script lang="ts" name="smartcity-ssWeekReport" setup>
  import {ref, reactive, computed, unref} from 'vue';
  import {BasicTable, useTable, TableAction} from '/@/components/Table';
  import { useListPage } from '/@/hooks/system/useListPage'
  import {useModal} from '/@/components/Modal';
  import SsWeekReportModal from './components/SsWeekReportModal.vue'
  import {columns, searchFormSchema, superQuerySchema} from './SsWeekReport.data';
  import {list, deleteOne, batchDelete, getImportUrl,getExportUrl, commit} from './SsWeekReport.api';

  import {downloadFile} from '/@/utils/common/renderUtils';
  import { useUserStore } from '/@/store/modules/user';
  import { useMessage } from '/@/hooks/web/useMessage';
  import { getDateByPicker } from '/@/utils';
  //日期个性化选择
  const fieldPickers = reactive({
  });
  const queryParam = reactive<any>({});
  const checkedKeys = ref<Array<string | number>>([]);
  const userStore = useUserStore();
  const { createMessage } = useMessage();
  //注册model
  const [registerModal, {openModal}] = useModal();
   //注册table数据
  const { prefixCls,tableContext,onExportXls,onImportXls } = useListPage({
      tableProps:{
           title: '周报',
           api: list,
           columns,
           canResize:true,
           formConfig: {
                //labelWidth: 120,
                schemas: searchFormSchema,
                autoSubmitOnEnter:true,
                showAdvancedButton:true,
                fieldMapToNumber: [
                ],
                fieldMapToTime: [
                ],
            },
           actionColumn: {
               width: 240,
               fixed:'right'
           },
           beforeFetch: (params) => {
              if (params && fieldPickers) {
                for (let key in fieldPickers) {
                  if (params[key]) {
                    params[key] = getDateByPicker(params[key], fieldPickers[key]);
                  }
                }
              }
             return Object.assign(params, queryParam);
           },
        },
        exportConfig: {
            name:"周报",
            url: getExportUrl,
            params: queryParam,
        },
        importConfig: {
            url: getImportUrl,
            success: handleSuccess
        },
    })

  const [registerTable, {reload},{ rowSelection, selectedRowKeys }] = tableContext

  // 高级查询配置
  const superQueryConfig = reactive(superQuerySchema);

  /**
   * 高级查询事件
   */
  function handleSuperQuery(params) {
    Object.keys(params).map((k) => {
      queryParam[k] = params[k];
    });
    reload();
  }

   /**
    * 新增事件
    */
  function handleAdd() {
     openModal(true, {
       isUpdate: false,
       showFooter: true,
     });
  }
   /**
    * 编辑事件
    */
  function handleEdit(record: Recordable) {
     openModal(true, {
       record,
       isUpdate: true,
       showFooter: true,
     });
   }
   /**
    * 编辑事件
    */
  async function handleCommit(record: Recordable) {
    try {
      const result = await commit({ id: record.id });
      if (result == "提交成功") { // result.success
        //createMessage.success('提交成功');
        reload(); // 重新加载表格数据
      } else {
        createMessage.error(result.message || '提交失败');
      }
    } catch (error) {
      console.error('提交失败:', error);
      createMessage.error('提交失败');
    }
   }
   /**
    * 详情
   */
  function handleDetail(record: Recordable) {
     openModal(true, {
       record,
       isUpdate: true,
       showFooter: false,
     });
   }
   /**
    * 删除事件
    */
  async function handleDelete(record) {
     await deleteOne({id: record.id}, handleSuccess);
   }
   /**
    * 批量删除事件
    */
  async function batchHandleDelete() {
     await batchDelete({ids: selectedRowKeys.value},handleSuccess);
   }
   /**
    * 成功回调
    */
  function handleSuccess() {
      (selectedRowKeys.value = []) && reload();
   }
   /**
    * 操作栏
    */
  function getTableAction(record){
       const disabled = record.status==1;
       return [
         {
           label: '编辑',
           onClick: handleEdit.bind(null, record),
           auth: 'smartcity:ss_week_report:edit'
         },
         {
            label: '提交',
            disabled: disabled,
            onClick: handleCommit.bind(null, record),
            auth: 'smartcity:ss_week_report:commit'
          },
          {
          label: '详情',
          onClick: handleDetail.bind(null, record),
         },
         {
           label: '删除',
           popConfirm: {
             title: '是否确认删除',
             confirm: handleDelete.bind(null, record),
             placement: 'topLeft'
           },
           auth: 'smartcity:ss_week_report:delete'
         }
       ]
   }


  /**
   * 下拉操作栏
   */
  function getDropDownAction(record){
    return [

    ]
  }



</script>

<style lang="less" scoped>
  :deep(.ant-picker),:deep(.ant-input-number){
    width: 100%;
  }
</style>
