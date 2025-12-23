import { FormSchema } from '/@/components/Table';

// 文档表单数据
export const docFormSchema: FormSchema[] = [
  {
    label: '文档标题',
    field: 'title',
    component: 'Input',
    required: true,
    colProps: { span: 24 },
  },
  {
    label: '文档类型',
    field: 'type',
    component: 'RadioGroup',
    defaultValue: 'text',
    componentProps: {
      options: [
        { label: '文本', value: 'text' },
        { label: '文件', value: 'file' },
      ],
    },
    colProps: { span: 24 },
  },
  {
    label: '文档内容',
    field: 'content',
    component: 'InputTextArea',
    ifShow: ({ values }) => values.type === 'text',
    colProps: { span: 24 },
  },
  {
    label: '上传文件',
    field: 'file',
    component: 'JUpload',
    ifShow: ({ values }) => values.type === 'file',
    componentProps: {
      fileMax: 1,
    },
    colProps: { span: 24 },
  },
  {
    label: '知识库ID',
    field: 'knowledgeId',
    component: 'Input',
    show: false,
  },
  {
    label: '状态',
    field: 'status',
    component: 'Input',
    show: false,
  },
  {
    label: '',
    field: 'id',
    component: 'Input',
    show: false,
  },
];