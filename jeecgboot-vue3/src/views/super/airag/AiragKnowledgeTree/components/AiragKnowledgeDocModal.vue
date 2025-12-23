<template>
  <BasicModal
    v-bind="$attrs"
    @register="registerModal"
    :title="getTitle"
    @ok="handleSubmit"
    @cancel="handleCancel"
    width="600px"
  >
    <BasicForm @register="registerForm" />
  </BasicModal>
</template>

<script lang="ts" setup>
  import { ref, computed, unref } from 'vue';
  import { BasicModal, useModalInner } from '/@/components/Modal';
  import { BasicForm, useForm } from '/@/components/Form';
  import { docFormSchema } from '../AiragKnowledgeTreeDetail.data';
  import { knowledgeSaveDoc } from '/@/views/super/airag/aiknowledge/AiKnowledgeBase.api';
  import { useMessage } from '/@/hooks/web/useMessage';

  const emit = defineEmits(['success', 'register']);
  const { createMessage } = useMessage();

  const isUpdate = ref(false);
  const currentRecord = ref<Recordable>({});

  const getTitle = computed(() => {
    return unref(isUpdate) ? '编辑文档' : '新增文档';
  });

  const [registerForm, { resetFields, setFieldsValue, validate }] = useForm({
    schemas: docFormSchema,
    showActionButtonGroup: false,
    baseColProps: { span: 24 },
  });

  const [registerModal, { setModalProps, closeModal }] = useModalInner(async (data) => {
    resetFields();
    setModalProps({ confirmLoading: false });
    
    isUpdate.value = !!data?.isUpdate;
    if (unref(isUpdate)) {
      currentRecord.value = data.record;
      setFieldsValue({
        ...data.record,
      });
    } else {
      currentRecord.value = {};
      setFieldsValue({
        knowledgeId: data.knowledgeId,
        type: data.type,
        status: 'draft'
      });
    }
  });

  async function handleSubmit() {
    try {
      const values = await validate();
      setModalProps({ confirmLoading: true });
      
      await knowledgeSaveDoc(values);
      
      createMessage.success(unref(isUpdate) ? '更新成功' : '添加成功');
      closeModal();
      emit('success');
    } finally {
      setModalProps({ confirmLoading: false });
    }
  }

  function handleCancel() {
    closeModal();
  }
</script>