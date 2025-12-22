<template>
    <BasicForm @register="registerForm" name="SsWeeklyRecordForm" class="basic-modal-form">
      <template #aiTranslateButton="{ model, field }">
        <a-button type="primary" @click="handleAiTranslate(model)" :loading="aiLoading">AI转写</a-button>
      </template>
    </BasicForm>
</template>
<script lang="ts">
    import {defineComponent, ref} from 'vue';
    import {BasicForm, useForm} from '/@/components/Form/index';
    import {ssWeeklyRecordFormSchema} from '../SsWeekReport.data';
    import {defHttp} from '/@/utils/http/axios';
    import { VALIDATE_FAILED } from '/@/utils/common/vxeUtils';
    import { useMessage } from '/@/hooks/web/useMessage';

    export default defineComponent({
        name:"SsWeeklyRecordForm",
        components: {BasicForm},
        emits:['register'],
        props:{
            disabled: {
                type: Boolean,
                default: false
            }
        },
        setup(props,{emit}) {
            const { createMessage } = useMessage();
            const aiLoading = ref(false);
            const [registerForm, { setProps, resetFields, setFieldsValue, getFieldsValue, validate, scrollToField }] = useForm({
                labelWidth: 150,
                schemas: ssWeeklyRecordFormSchema,
                showActionButtonGroup: false,
                baseColProps: {span: 24}
            });
            /**
            *初始化加载数据
            */
            function initFormData(url,id){
                if(id){
                     defHttp.get({url,params:{id}},{isTransformResponse:false}).then(res=>{
                       res.success && setFieldsValue({...res.result[0]});
                    })
                }
                setProps({disabled: props.disabled})
            }
           /**
            *获取表单数据
            */
            function getFormData(){
               let formData = getFieldsValue();
               Object.keys(formData).map(k=>{
                    if(formData[k] instanceof Array){
                        formData[k] = formData[k].join(',')
                    }
               });
               return [formData];
            }
            /**
            *AI转写功能
            */
            async function handleAiTranslate(model) {
                const workContent = model.workContent;
                if (!workContent) {
                    createMessage.warning('请先输入工作内容记录');
                    return;
                }
                
                try {
                    aiLoading.value = true;
                    // 调用后端AI转写接口
                    const result = await defHttp.post({
                        url: '/smartcity/ssWeekReport/aiTranslate',
                        params: { content: workContent }
                    }, { isTransformResponse: false });
                    
                    if (result.success) {
                        // 将结果设置到AI转写结果字段
                        setFieldsValue({ aiResult: result.result });
                        createMessage.success('AI转写成功');
                    } else {
                        createMessage.error(result.message || 'AI转写失败');
                    }
                } catch (error) {
                    console.error('AI转写错误:', error);
                    createMessage.error('AI转写请求失败');
                } finally {
                    aiLoading.value = false;
                }
            }
            
            /**
            *表单校验
            */
            function validateForm(index){
                return new Promise((resolve, reject) => {
                    // 验证子表表单
                    validate().then(()=>{
                        return resolve()
                    }).catch(({ errorFields }) => {
                      return reject({ error: VALIDATE_FAILED, index, errorFields: errorFields, scrollToField: scrollToField });
                    });
                })
            }
            return {
                registerForm,
                resetFields,
                initFormData,
                getFormData,
                validateForm,
                aiLoading,
                handleAiTranslate
            }
        }
    })
</script>
<style lang="less" scoped>
  .basic-modal-form {
    overflow: auto;
    height: 500px;
  }
</style>
