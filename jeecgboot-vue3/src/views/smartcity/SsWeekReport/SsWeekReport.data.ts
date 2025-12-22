import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import {JVxeTypes,JVxeColumn} from '/@/components/jeecg/JVxeTable/types'
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '姓名',
    align:"center",
    dataIndex: 'name'
   },
   {
    title: '日期',
    align:"center",
    dataIndex: 'day',
    customRender:({text}) =>{
      text = !text ? "" : (text.length > 10 ? text.substr(0,10) : text);
      return text;
    },
   },
   {
    title: '周',
    align:"center",
    dataIndex: 'week'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "姓名",
      field: "name",
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "日期",
      field: "day",
      component: 'DatePicker',
      componentProps: {
        valueFormat: 'YYYY-MM-DD'
      },      
      //colProps: {span: 6},
 	},
	{
      label: "周",
      field: "week",
      component: 'InputNumber',
      //colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '姓名',
    field: 'name',
    component: 'Input',
  },
  {
    label: '日期',
    field: 'day',
    component: 'DatePicker',
    componentProps:{
      valueFormat: 'YYYY-MM-DD'
    },    
  },
  {
    label: '周',
    field: 'week',
    component: 'InputNumber',
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];
//子表单数据
export const ssWeeklyRecordFormSchema: FormSchema[] = [
  {
    label: '工作内容记录',
    field: 'workContent',
    component: 'InputTextArea',
    componentProps: {
      rows: 12,
      placeholder: '请输入工作内容记录'
    },
    colProps: { span: 24 },
  },
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
  {
      field: 'aiTranslateButton',
      component: 'Input',
      label: ' ',
      slot: 'aiTranslateButton',
      colProps: { span: 24 },
  },
];
//子表表格配置
export const ssWeeklySummaryColumns: JVxeColumn[] = [
    {
      title: '本周完成主要工作',
      key: 'completeTask',
      type: JVxeTypes.input,
      width:"456px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '问题、意见反馈',
      key: 'questionFeedback',
      type: JVxeTypes.input,
      width:"320px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '建议及解决方案',
      key: 'suggestionSolutions',
      type: JVxeTypes.input,
      width:"300px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
  ]
export const ssWorklyPlanColumns: JVxeColumn[] = [
    {
      title: '星期',
      key: 'weekDay',
      type: JVxeTypes.select,
      options:[],
      dictCode:"week",
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '日期',
      key: 'workDay',
      type: JVxeTypes.date,
      width:"200px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
    {
      title: '工作计划内容',
      key: 'workPlanContent',
      type: JVxeTypes.input,
      width:"676px",
      placeholder: '请输入${title}',
      defaultValue:'',
    },
  ]


// 高级查询数据
export const superQuerySchema = {
  name: {title: '姓名',order: 0,view: 'text', type: 'string',},
  day: {title: '日期',order: 1,view: 'date', type: 'string',},
  week: {title: '周',order: 2,view: 'number', type: 'number',},
  //子表高级查询
  ssWeeklySummary: {
    title: '本周总结',
    view: 'table',
    fields: {
        completeTask: {title: '本周完成主要工作',order: 0,view: 'text', type: 'string',},
        questionFeedback: {title: '问题、意见反馈',order: 1,view: 'text', type: 'string',},
        suggestionSolutions: {title: '建议及解决方案',order: 2,view: 'text', type: 'string',},
    }
  },
  ssWeeklyRecord: {
    title: '本周工作记录',
    view: 'table',
    fields: {
        workContent: {title: '工作内容记录',order: 0,view: 'text', type: 'string',},
    }
  },
  ssWorklyPlan: {
    title: '下周工作计划',
    view: 'table',
    fields: {
        weekDay: {title: '星期',order: 0,view: 'number', type: 'number',dictCode: 'week',},
        workDay: {title: '日期',order: 1,view: 'date', type: 'string',},
        workPlanContent: {title: '工作计划内容',order: 2,view: 'text', type: 'string',},
    }
  },
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
// 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}
