<template>
  <BasicModal
    v-bind="$attrs"
    @register="registerModal"
    title="AI转写结果"
    :width="600"
    @ok="handleOk"
    @cancel="handleCancel"
  >
    <a-textarea
      v-model:value="aiResultContent"
      :rows="10"
      placeholder="AI转写结果将显示在这里"
    />
  </BasicModal>
</template>

<script lang="ts">
import { defineComponent, ref, nextTick } from 'vue';
import { BasicModal, useModalInner } from '/@/components/Modal';

export default defineComponent({
  name: 'AiTranslateResultModal',
  emits: ['apply', 'cancel', 'register'],
  setup(_, { emit }) {
    const aiResultContent = ref('');
    
    const [registerModal, { closeModal }] = useModalInner();

    // 设置AI转写结果内容
    function setAiResultContent(content: string) {
      aiResultContent.value = content;
    }

    // 确定按钮事件
    function handleOk() {
      emit('apply', aiResultContent.value);
      closeModal();
    }

    // 取消按钮事件
    function handleCancel() {
      emit('cancel');
      closeModal();
    }

    return {
      registerModal,
      aiResultContent,
      setAiResultContent,
      handleOk,
      handleCancel,
    };
  },
});
</script>