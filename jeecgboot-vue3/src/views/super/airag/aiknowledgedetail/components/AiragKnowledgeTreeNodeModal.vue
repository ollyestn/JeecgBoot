<template>
  <BasicModal v-bind="$attrs" @register="registerModal" :title="modalTitle" @ok="handleSubmit" @cancel="handleCancel">
    <BasicForm @register="registerForm" />
  </BasicModal>
</template>

<script lang="ts">
  import { defineComponent, ref, computed } from 'vue';
  import { BasicModal, useModalInner } from '/@/components/Modal';
  import { BasicForm, useForm } from '/@/components/Form';
  import { useMessage } from '/@/hooks/web/useMessage';
  import { saveOrUpdate } from '../../AiragKnowledgeTree/AiragKnowledgeTree.api';

  export default defineComponent({
    name: 'AiragKnowledgeTreeNodeModal',
    components: { BasicModal, BasicForm },
    emits: ['success'],
    setup(_, { emit }) {
      const { createMessage } = useMessage();
      const isUpdate = ref<boolean>(false);
      const currentNode = ref<any>(null);
      const knowledgeId = ref<string>('');

      const modalTitle = computed(() => {
        return isUpdate.value ? '编辑目录节点' : '新增目录节点';
      });

      const [registerForm, { resetFields, setFieldsValue, validate, getFieldsValue }] = useForm({
        labelWidth: 100,
        schemas: [
          {
            field: 'name',
            label: '节点名称',
            component: 'Input',
            required: true,
            componentProps: {
              placeholder: '请输入节点名称',
            },
          },
        ],
        showActionButtonGroup: false,
      });

      const [registerModal, { setModalProps, closeModal }] = useModalInner(async (data) => {
        resetFields();
        setModalProps({ confirmLoading: false });
        
        isUpdate.value = !!data?.isUpdate;
        currentNode.value = data?.record || null;
        knowledgeId.value = data?.knowledgeId || '';

        if (isUpdate.value && currentNode.value) {
          setFieldsValue({
            name: currentNode.value.name,
          });
        }
      });

      async function handleSubmit() {
        try {
          const values = await validate();
          setModalProps({ confirmLoading: true });

          // 准备提交数据
          const params: any = {
            ...values,
            knowledgeId: knowledgeId.value,
          };

          if (isUpdate.value && currentNode.value) {
            // 编辑模式
            params.id = currentNode.value.id;
            params.pid = currentNode.value.pid;
            params.level = currentNode.value.level;
          } else {
            // 新增模式
            // 修复：确保在新增根节点时正确处理
            if (currentNode.value) {
              params.pid = currentNode.value.id || '0'; // 父节点ID
              // 计算层级
              params.level = (currentNode.value.level !== undefined) ? currentNode.value.level + 1 : 1;
            } else {
              // 如果没有当前节点信息，则为根节点下的新增
              params.pid = '0';
              params.level = 1;
            }
          }

          // 调用保存或更新接口
          await saveOrUpdate(params, isUpdate.value);
          
          createMessage.success(`${isUpdate.value ? '编辑' : '新增'}成功`);
          closeModal();
          emit('success');
        } finally {
          setModalProps({ confirmLoading: false });
        }
      }

      function handleCancel() {
        closeModal();
      }

      return {
        registerModal,
        registerForm,
        modalTitle,
        handleSubmit,
        handleCancel,
      };
    },
  });
</script>
