import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '日期',
    align:"center",
    dataIndex: 'trainingTime',
    customRender:({text}) =>{
      text = !text ? "" : (text.length > 10 ? text.substr(0,10) : text);
      return text;
    },
   },
   {
    title: '课题',
    align:"center",
    dataIndex: 'trainingTopic'
   },
   {
    title: '主要内容',
    align:"center",
    dataIndex: 'mainContent'
   },
   {
    title: '组织形式',
    align:"center",
    dataIndex: 'traingType'
   },
   {
    title: '主讲人',
    align:"center",
    dataIndex: 'presenter'
   },
   {
    title: '参加人员',
    align:"center",
    dataIndex: 'participants'
   },
   {
    title: '培训教案',
    align:"center",
    dataIndex: 'files',
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "日期",
      field: 'trainingTime',
      component: 'DatePicker',
      componentProps: {
        valueFormat: 'YYYY-MM-DD'
      },
      //colProps: {span: 6},
 	},
	{
      label: "课题",
      field: 'trainingTopic',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "主要内容",
      field: 'mainContent',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "组织形式",
      field: 'traingType',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "主讲人",
      field: 'presenter',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "参加人员",
      field: 'participants',
      component: 'Input',
      //colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '日期',
    field: 'trainingTime',
    component: 'DatePicker',
    componentProps: {
      valueFormat: 'YYYY-MM-DD'
    },
  },
  {
    label: '课题',
    field: 'trainingTopic',
    component: 'Input',
  },
  {
    label: '主要内容',
    field: 'mainContent',
    component: 'Input',
  },
  {
    label: '组织形式',
    field: 'traingType',
    component: 'Input',
  },
  {
    label: '主讲人',
    field: 'presenter',
    component: 'Input',
  },
  {
    label: '参加人员',
    field: 'participants',
    component: 'Input',
  },
  {
    label: '培训教案',
    field: 'files',
    component: 'JUpload',
    componentProps:{
     },
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];

// 高级查询数据
export const superQuerySchema = {
  trainingTime: {title: '日期',order: 0,view: 'date', type: 'string',},
  trainingTopic: {title: '课题',order: 1,view: 'text', type: 'string',},
  mainContent: {title: '主要内容',order: 2,view: 'text', type: 'string',},
  traingType: {title: '组织形式',order: 3,view: 'text', type: 'string',},
  presenter: {title: '主讲人',order: 4,view: 'text', type: 'string',},
  participants: {title: '参加人员',order: 5,view: 'text', type: 'string',},
  files: {title: '培训教案',order: 6,view: 'file', type: 'string',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}