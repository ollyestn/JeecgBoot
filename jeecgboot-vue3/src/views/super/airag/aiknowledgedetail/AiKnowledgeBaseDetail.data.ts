import { FormSchema } from '@/components/Form';
import { BasicColumn } from '@/components/Table';

/**
 * 表单
 */
export const formSchema: FormSchema[] = [
  {
    label: 'id',
    field: 'id',
    component: 'Input',
    show: false,
  },
  {
    label: '知识库名称',
    field: 'name',
    required: true,
    componentProps: {
      placeholder: '请输入知识库名称',
      //是否展示字数
      showCount: true,
      maxlength: 64,
    },
    component: 'Input',
  },
  {
    label: '知识库描述',
    field: 'descr',
    component: 'InputTextArea',
    componentProps: {
      placeholder: '描述知识库的内容，详尽的描述将帮助AI能深入理解该知识库的内容，能更准确的检索到内容，提高该知识库的命中率。',
      //是否展示字数
      showCount: true,
      maxlength: 256,
    },
  },
  {
    label: '向量模型',
    field: 'embedId',
    required: true,
    component: 'JDictSelectTag',
    componentProps: {
      dictCode: "airag_model where model_type = 'EMBED' and activate_flag = 1,name,id",
    },
  },
  {
    label: '状态',
    field: 'status',
    required: true,
    component: 'JDictSelectTag',
    componentProps: {
      options: [
        { label: '启用', value: 'enable' },
        { label: '禁用', value: 'disable' },
      ],
      type: 'radioButton',
    },
    defaultValue: 'enable',
  },
];

//文档文本表单
export const docTextSchema: FormSchema[] = [
  {
    label: 'id',
    field: 'id',
    component: 'Input',
    show: false,
  },
  {
    label: '知识库id',
    field: 'knowledgeId',
    show: false,
    component: 'Input',
  },
  {
    label: '标题',
    field: 'title',
    required: true,
    component: 'Input',
  },
  {
    label: '类型',
    field: 'type',
    required: true,
    component: 'Input',
    show: false
  },
  {
    label: '内容',
    field: 'content',
    rules: [{ required: true, message: '请输入内容' }],
    component: 'JMarkdownEditor',
    componentProps: {
      placeholder: "请输入内容",
      preview:{ mode: 'view', action: [] }
    },
    ifShow:({ values})=>{
      if(values.type === 'text'){
        return true;
      }
      return false;
    }
  },
  {
    label: '文件',
    field: 'filePath',
    rules: [{ required: true, message: '请上传文件' }],
    component: 'JUpload',
    helpMessage:'支持txt、markdown、pdf、docx、xlsx、pptx222',
    componentProps:{
      fileType: 'file',
      maxCount: 1,
      multiple: false,
      text: '上传文档',
      onChange: (info) => {
        // 提取文件名（不包含后缀）
        const fileName = info.replace(/\.[^/.]+$/, "").split('/').pop();
        // 自动填写到标题字段
        //setFieldsValue({ "title": fileName });

        // 通过 emit 通知父组件更新标题
        // 需要在父组件中处理这个事件
        //emit('fileUploaded', fileName);

        // 通过 window 事件触发文件上传完成
        const event = new CustomEvent('fileUploadCompleted', {
          detail: { fileName }
        });
        window.dispatchEvent(event);
      }
    },
    ifShow:({ values })=>{
      if(values.type === 'file'){
        return true;
      }
      return false;
    }
  },
];

