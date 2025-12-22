
<template>
  <BasicModal v-bind="$attrs" @register="registerModal" destroyOnClose :title="title" :maxHeight="500" :width="800" @ok="handleSubmit">
    <BasicForm @register="registerForm" ref="formRef" name="SsWeekReportForm"/>
    <!-- 子表单区域 -->
    <a-tabs v-model:activeKey="activeKey" animated @change="handleChangeTabs" class="jeecg-tab">
      <a-tab-pane tab="本周总结" key="ssWeeklySummary" :forceRender="true">
        <JVxeTable
          keep-source
          resizable
          ref="ssWeeklySummary"
          :loading="ssWeeklySummaryTable.loading"
          :columns="ssWeeklySummaryTable.columns"
          :dataSource="ssWeeklySummaryTable.dataSource"
          :height="340"
          :rowNumber="true"
          :rowSelection="true"
          :disabled="formDisabled"
          :toolbar="true"
          />
      </a-tab-pane>
      <a-tab-pane tab="本周工作记录" key="ssWeeklyRecord" :forceRender="true">
        <SsWeeklyRecordForm ref="ssWeeklyRecordForm" :disabled="formDisabled"></SsWeeklyRecordForm>
      </a-tab-pane>

      <a-tab-pane tab="下周工作计划" key="ssWorklyPlan" :forceRender="true">
        <JVxeTable
          keep-source
          resizable
          ref="ssWorklyPlan"
          :loading="ssWorklyPlanTable.loading"
          :columns="ssWorklyPlanTable.columns"
          :dataSource="ssWorklyPlanTable.dataSource"
          :height="340"
          :rowNumber="true"
          :rowSelection="true"
          :disabled="formDisabled"
          :toolbar="true"
          />
      </a-tab-pane>
    </a-tabs>
  </BasicModal>
</template>

<script lang="ts" setup>
    import {ref, computed, unref,reactive} from 'vue';
    import {BasicModal, useModalInner} from '/@/components/Modal';
    import {BasicForm, useForm} from '/@/components/Form/index';
    import { JVxeTable } from '/@/components/jeecg/JVxeTable'
    import { useJvxeMethod } from '/@/hooks/system/useJvxeMethods.ts'
    import SsWeeklyRecordForm from './SsWeeklyRecordForm.vue'
    import {formSchema,ssWeeklySummaryColumns,ssWorklyPlanColumns} from '../SsWeekReport.data';
    import {saveOrUpdate,ssWeeklySummaryList,ssWeeklyRecordList,ssWorklyPlanList} from '../SsWeekReport.api';
    import { VALIDATE_FAILED } from '/@/utils/common/vxeUtils'
    import { useMessage } from '/@/hooks/web/useMessage';
    import { getDateByPicker } from '/@/utils';
    //日期个性化选择
    const fieldPickers = reactive({
    });
      const ssWeeklySummaryFieldPickers = reactive({
      });
      const ssWorklyPlanFieldPickers = reactive({
      });
    const { createMessage } = useMessage();
    // Emits声明
    const emit = defineEmits(['register','success']);
    const isUpdate = ref(true);
    const formDisabled = ref(false);
    const refKeys = ref(['ssWeeklySummary', 'ssWeeklyRecord', 'ssWorklyPlan', ]);
    const activeKey = ref('ssWeeklySummary');
    const ssWeeklySummary = ref();
    const ssWeeklyRecordForm = ref();
    const ssWorklyPlan = ref();
    const tableRefs = {ssWeeklySummary, ssWorklyPlan, };
    const ssWeeklySummaryTable = reactive({
          loading: false,
          dataSource: [],
          columns:ssWeeklySummaryColumns,
          baseRowStyle: { padding: "0 20px"}
    })
    const ssWorklyPlanTable = reactive({
          loading: false,
          dataSource: [],
          columns:ssWorklyPlanColumns,
          baseRowStyle: { padding: "0 20px"}
    })
    //表单配置
    const [registerForm, {setProps,resetFields, setFieldsValue, validate}] = useForm({
        labelWidth: 150,
        schemas: formSchema,
        showActionButtonGroup: false,
        baseColProps: {span: 24},
        baseRowStyle: { padding: "0 20px" }
    });
     //表单赋值
    const [registerModal, {setModalProps, closeModal}] = useModalInner(async (data) => {
        //重置表单
        await reset();
        setModalProps({confirmLoading: false,showCancelBtn:data?.showFooter,showOkBtn:data?.showFooter});
        isUpdate.value = !!data?.isUpdate;
        formDisabled.value = !data?.showFooter;
        if (unref(isUpdate)) {
            //表单赋值
            await setFieldsValue({
                ...data.record,
            });
             ssWeeklyRecordForm.value.initFormData(ssWeeklyRecordList,data?.record?.id)
             requestSubTableData(ssWeeklySummaryList, {id:data?.record?.id}, ssWeeklySummaryTable)
             requestSubTableData(ssWorklyPlanList, {id:data?.record?.id}, ssWorklyPlanTable)
        }
        // 隐藏底部时禁用整个表单
       setProps({ disabled: !data?.showFooter })
    });
    //方法配置
    const [handleChangeTabs,handleSubmit,requestSubTableData,formRef] = useJvxeMethod(requestAddOrEdit,classifyIntoFormData,tableRefs,activeKey,refKeys,validateSubForm);

    //设置标题
    const title = computed(() => (!unref(isUpdate) ? '新增' : !unref(formDisabled) ? '编辑' : '详情'));

    async function reset(){
      await resetFields();
      activeKey.value = 'ssWeeklySummary';
      ssWeeklySummaryTable.dataSource = [];
      ssWeeklyRecordForm.value.resetFields();
      ssWorklyPlanTable.dataSource = [];
    }
    function classifyIntoFormData(allValues) {
         let main = Object.assign({}, allValues.formValue)
         return {
           ...main, // 展开
           ssWeeklySummaryList: allValues.tablesValue[0].tableData,
           ssWeeklyRecordList: ssWeeklyRecordForm.value.getFormData(),
           ssWorklyPlanList: allValues.tablesValue[1].tableData,
         }
       }
     //校验所有一对一子表表单
     function validateSubForm(allValues){
         return new Promise((resolve,reject)=>{
             Promise.all([
                  ssWeeklyRecordForm.value.validateForm(1),
             ]).then(() => {
                 resolve(allValues)
             }).catch(e => {
                 if (e.error === VALIDATE_FAILED) {
                     // 如果有未通过表单验证的子表，就自动跳转到它所在的tab
                     activeKey.value = e.index == null ? unref(activeKey) : refKeys.value[e.index]
                     if (e.errorFields) {
                       const firstField = e.errorFields[0];
                       if (firstField) {
                         e.scrollToField(firstField.name, { behavior: 'smooth', block: 'center' });
                       }
                     }
                 } else {
                     console.error(e)
                 }
             })
         })
     }
    //表单提交事件
    async function requestAddOrEdit(values) {
        try {
            // 预处理日期数据
            changeDateValue(values);
            setModalProps({confirmLoading: true});
            //提交表单
            await saveOrUpdate(values, isUpdate.value);
            //关闭弹窗
            closeModal();
            //刷新列表
            emit('success');
        } finally {
            setModalProps({confirmLoading: false});
        }
    }

    /**
     * 处理日期值
     * @param formData 表单数据
     */
    const changeDateValue = (formData) => {
      if (formData && fieldPickers) {
          for (let key in fieldPickers) {
              if (formData[key]) {
                  formData[key] = getDateByPicker(formData[key], fieldPickers[key]);
              }
          }
      }
      if(formData && formData.ssWeeklySummaryList && formData.ssWeeklySummaryList.length > 0){
          formData.ssWeeklySummaryList.forEach(subFormData=>{
              for (let key in ssWeeklySummaryFieldPickers) {
                  if (subFormData[key]) {
                      subFormData[key] = getDateByPicker(subFormData[key], ssWeeklySummaryFieldPickers[key]);
                  }
              }
          })
      }
      if(formData && formData.ssWorklyPlanList && formData.ssWorklyPlanList.length > 0){
          formData.ssWorklyPlanList.forEach(subFormData=>{
              for (let key in ssWorklyPlanFieldPickers) {
                  if (subFormData[key]) {
                      subFormData[key] = getDateByPicker(subFormData[key], ssWorklyPlanFieldPickers[key]);
                  }
              }
          })
      }
    };
</script>

<style lang="less" scoped>
	/** 时间和数字输入框样式 */
  :deep(.ant-input-number) {
    width: 100%;
  }

  :deep(.ant-calendar-picker) {
    width: 100%;
  }
  
  .jeecg-tab {
    padding: 0 20px;
  }
</style>