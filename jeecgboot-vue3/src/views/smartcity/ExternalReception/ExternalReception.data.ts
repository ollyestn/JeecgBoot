import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '来访日期',
    align:"center",
    dataIndex: 'visitDate',
    customRender:({text}) =>{
      text = !text ? "" : (text.length > 10 ? text.substr(0,10) : text);
      return text;
    },
   },
   {
    title: '来访单位全称',
    align:"center",
    dataIndex: 'visitOrgName'
   },
   {
    title: '来访主要领导及职务',
    align:"center",
    dataIndex: 'leadersAndPositions'
   },
   {
    title: '我司参加接待领导及职务',
    align:"center",
    dataIndex: 'ourLeadersAndPositions'
   },
   {
    title: '来访目的',
    align:"center",
    dataIndex: 'visitPurpose'
   },
   {
    title: '实际效果（简述）',
    align:"center",
    dataIndex: 'actualEffect'
   },
   {
    title: '备注',
    align:"center",
    dataIndex: 'note'
   },
   {
    title: '接待照片1',
    align:"center",
    dataIndex: 'img1',
    customRender:render.renderImage,
   },
   {
    title: '接待照片2',
    align:"center",
    dataIndex: 'img2',
    customRender:render.renderImage,
   },
   {
    title: '接待照片3',
    align:"center",
    dataIndex: 'img3',
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "来访日期",
      field: 'visitDate',
      component: 'DatePicker',
      componentProps: {
        valueFormat: 'YYYY-MM-DD'
      },
      //colProps: {span: 6},
 	},
	{
      label: "来访单位全称",
      field: 'visitOrgName',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "来访主要领导及职务",
      field: 'leadersAndPositions',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "我司参加接待领导及职务",
      field: 'ourLeadersAndPositions',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "来访目的",
      field: 'visitPurpose',
      component: 'Input',
      //colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '来访日期',
    field: 'visitDate',
    component: 'DatePicker',
    componentProps: {
      valueFormat: 'YYYY-MM-DD'
    },
  },
  {
    label: '来访单位全称',
    field: 'visitOrgName',
    component: 'Input',
  },
  {
    label: '来访主要领导及职务',
    field: 'leadersAndPositions',
    component: 'Input',
  },
  {
    label: '我司参加接待领导及职务',
    field: 'ourLeadersAndPositions',
    component: 'Input',
  },
  {
    label: '来访目的',
    field: 'visitPurpose',
    component: 'Input',
  },
  {
    label: '实际效果（简述）',
    field: 'actualEffect',
    component: 'Input',
  },
  {
    label: '备注',
    field: 'note',
    component: 'Input',
  },
  {
    label: '接待照片1',
    field: 'img1',
     component: 'JImageUpload',
     componentProps:{
        fileMax: 0
      },
  },
  {
    label: '接待照片2',
    field: 'img2',
     component: 'JImageUpload',
     componentProps:{
        fileMax: 0
      },
  },
  {
    label: '接待照片3',
    field: 'img3',
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
  visitDate: {title: '来访日期',order: 0,view: 'date', type: 'string',},
  visitOrgName: {title: '来访单位全称',order: 1,view: 'text', type: 'string',},
  leadersAndPositions: {title: '来访主要领导及职务',order: 2,view: 'text', type: 'string',},
  ourLeadersAndPositions: {title: '我司参加接待领导及职务',order: 3,view: 'text', type: 'string',},
  visitPurpose: {title: '来访目的',order: 4,view: 'text', type: 'string',},
  actualEffect: {title: '实际效果（简述）',order: 5,view: 'text', type: 'string',},
  note: {title: '备注',order: 6,view: 'text', type: 'string',},
  img1: {title: '接待照片1',order: 7,view: 'image', type: 'string',},
  img2: {title: '接待照片2',order: 8,view: 'image', type: 'string',},
  img3: {title: '接待照片3',order: 9,view: 'file', type: 'string',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}