<template>
  <div>
    <BasicForm @register="registerForm" ref="formRef"/>
    
    <!-- 展平的子表单区域 -->
    <div class="flattened-form-container">
      <!-- 本周总结 -->
      <div class="form-section">
        <div class="section-title">本周总结</div>
        <JVxeTable
          keep-source
          resizable
          ref="ssWeeklySummary"
          v-if="ssWeeklySummaryTable.show"
          :loading="ssWeeklySummaryTable.loading"
          :columns="ssWeeklySummaryTable.columns"
          :dataSource="ssWeeklySummaryTable.dataSource"
          :height="340"
          :rowNumber="true"
          :rowSelection="true"
          :disabled="formDisabled"
          :toolbar="true"
          :stripe="true"
        />
      </div>
      
      <!-- 本周工作记录 -->
      <div class="form-section">
        <div class="section-title">本周工作记录</div>
        <SsWeeklyRecordForm ref="ssWeeklyRecordForm" :disabled="formDisabled"></SsWeeklyRecordForm>
      </div>
      
      <!-- 下周工作计划 -->
      <div class="form-section">
        <div class="section-title">下周工作计划</div>
        <JVxeTable
          keep-source
          resizable
          ref="ssWorklyPlan"
          v-if="ssWorklyPlanTable.show"
          :loading="ssWorklyPlanTable.loading"
          :columns="ssWorklyPlanTable.columns"
          :dataSource="ssWorklyPlanTable.dataSource"
          :height="340"
          :rowNumber="true"
          :rowSelection="true"
          :disabled="formDisabled"
          :toolbar="true"
          :stripe="true"
        />
      </div>
    </div>

    <div style="width: 100%;text-align: center" v-if="!formDisabled">
      <a-button @click="handleSubmit" pre-icon="ant-design:check" type="primary">提 交</a-button>
    </div>
  </div>
</template>

<script lang="ts">

  import {BasicForm, useForm} from '/@/components/Form/index';
  import { computed, defineComponent, reactive, ref, unref } from 'vue';
  import {defHttp} from '/@/utils/http/axios';
  import { propTypes } from '/@/utils/propTypes';
  import { useJvxeMethod } from '/@/hooks/system/useJvxeMethods';
  import { VALIDATE_FAILED } from '/@/utils/common/vxeUtils';
  import SsWeeklyRecordForm from './SsWeeklyRecordForm.vue'
  import {getBpmFormSchema,ssWeeklySummaryColumns,ssWorklyPlanColumns} from '../SsWeekReport.data';
  import {saveOrUpdate,ssWeeklySummaryList,ssWeeklyRecordList,ssWorklyPlanList} from '../SsWeekReport.api';

  export default defineComponent({
    name: "SsWeekReportForm",
    components:{
      BasicForm,
      SsWeeklyRecordForm,
    },
    props:{
      formData: propTypes.object.def({}),
      formBpm: propTypes.bool.def(true),
    },
    setup(props){
      const [registerForm, { setFieldsValue, setProps }] = useForm({
        labelWidth: 150,
        schemas: getBpmFormSchema(props.formData),
        showActionButtonGroup: false,
        baseColProps: {span: 24}
      });

      const formDisabled = computed(()=>{
        if(props.formData.disabled === false){
          return false;
        }
        return true;
      });

      const ssWeeklySummary = ref();
      const ssWeeklyRecordForm = ref();
      const ssWorklyPlan = ref();
      const ssWeeklySummaryTable = reactive({
        loading: false,
        dataSource: [],
        columns:ssWeeklySummaryColumns,
        show: false
      })
      const ssWorklyPlanTable = reactive({
        loading: false,
        dataSource: [],
        columns:ssWorklyPlanColumns,
        show: false
      })

      const [,handleSubmit,requestSubTableData,formRef] = useJvxeMethod(requestAddOrEdit,classifyIntoFormData,null,null,null,validateSubForm);

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
        return new Promise((resolve, _reject)=>{
          Promise.all([
            ssWeeklyRecordForm.value.validateForm(1),
          ]).then(() => {
            resolve(allValues)
          }).catch(e => {
            if (e.error === VALIDATE_FAILED) {
              // 如果有未通过表单验证的子表，就自动跳转到它所在的tab
              activeKey.value = e.index == null ? unref(activeKey) : refKeys.value[e.index]
            } else {
              console.error(e)
            }
          })
        })
      }

      //表单提交事件
      async function requestAddOrEdit(values) {
        await saveOrUpdate(values, true);
      }

      const queryByIdUrl = '/smartcity/ssWeekReport/queryById';
      async function initFormData(){
        let params = {id: props.formData.dataId};
        const data = await defHttp.get({url: queryByIdUrl, params});
        //设置表单的值
        await setFieldsValue({...data});
        requestSubTableData(ssWeeklySummaryList, {id: data.id}, ssWeeklySummaryTable, ()=>{
          ssWeeklySummaryTable.show = true;
        });
        ssWeeklyRecordForm.value.initFormData(ssWeeklyRecordList, data.id);
        requestSubTableData(ssWorklyPlanList, {id: data.id}, ssWorklyPlanTable, ()=>{
          ssWorklyPlanTable.show = true;
        });
        //默认是禁用
        await setProps({disabled: formDisabled.value})
      }

      initFormData();

      return {
        registerForm,
        formDisabled,
        formRef,
        handleSubmit,
        ssWeeklySummary,
        ssWeeklyRecordForm,
        ssWorklyPlan,
        ssWeeklySummaryTable,
        ssWorklyPlanTable,
      }
    }
  });
</script>

<style lang="less" scoped>
.flattened-form-container {
  margin-top: 20px;
  
  .form-section {
    margin-bottom: 30px;
    
    .section-title {
      font-size: 16px;
      font-weight: bold;
      margin-bottom: 15px;
      padding-left: 10px;
      border-left: 4px solid #1890ff;
    }
  }
}
</style>