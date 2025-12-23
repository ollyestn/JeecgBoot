<template>
    <BasicForm @register="registerForm" name="SsWeeklyRecordForm" class="basic-modal-form">
      <template #aiTranslateButton="{ model, field }">
        <a-button type="primary" @click="handleAiTranslate(model)" :loading="aiLoading">AI转写</a-button>
      </template>
    </BasicForm>
    
    <!-- AI转写结果对话框 -->
    <!--
    <AiTranslateResultModal
      @register="registerResultModal"
      @apply="handleApplyAiResult"
      @cancel="handleCloseAiResult"
      ref="aiResultModalRef"
    />
    -->
</template>
<script lang="ts">
    import {defineComponent, ref} from 'vue';
    import {BasicForm, useForm} from '/@/components/Form/index';
    import {ssWeeklyRecordFormSchema} from '../SsWeekReport.data';
    import {defHttp} from '/@/utils/http/axios';
    import { VALIDATE_FAILED } from '/@/utils/common/vxeUtils';
    import { useMessage } from '/@/hooks/web/useMessage';
    import { useModal } from '/@/components/Modal';
    import AiTranslateResultModal from './AiTranslateResultModal.vue';

    export default defineComponent({
        name:"SsWeeklyRecordForm",
        components: {BasicForm, AiTranslateResultModal},
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
            const currentModel = ref({});
            const aiResultModalRef = ref();
            const [registerForm, { setProps, resetFields, setFieldsValue, getFieldsValue, validate, scrollToField }] = useForm({
                labelWidth: 150,
                schemas: ssWeeklyRecordFormSchema,
                showActionButtonGroup: false,
                baseColProps: {span: 24}
            });
            const [registerResultModal, { openModal }] = useModal();
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
                    // 保存当前模型引用
                    currentModel.value = model;
                    // 调用后端AI转写接口
                    const result = await defHttp.post({
                        url: '/smartcity/ssWeekReport/aiTranslate',
                        data: { content: workContent }
                    }, { isTransformResponse: false,
                     timeout: 180000});
                    
                    if (result.success) {
                        // 设置AI转写结果并打开对话框
                        //if (aiResultModalRef.value) {
                        //    aiResultModalRef.value.setAiResultContent(result.result);
                        //}
                        //openModal(true, {});
                        model.workContent = result.result;
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
            * 应用AI转写结果
            */
            function handleApplyAiResult(content) {
                // 将AI转写结果应用到工作内容记录字段
                setFieldsValue({ workContent: content });
                createMessage.success('已应用AI转写结果');
            }
            
            /**
            * 关闭AI转写结果对话框
            */
            function handleCloseAiResult() {
                // 对话框会在组件内部自动关闭，这里可以添加其他逻辑
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
                handleAiTranslate,
                registerResultModal,
                aiResultModalRef,
                handleApplyAiResult,
                handleCloseAiResult
            }
        }
    })
</script>
<style lang="less" scoped>
  .basic-modal-form {
    overflow: auto;
    height: 360px;
  }
</style>
