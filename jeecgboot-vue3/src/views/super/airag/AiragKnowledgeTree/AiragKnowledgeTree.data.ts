import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '节点名称',
    align:"center",
    dataIndex: 'name'
   },
   {
    title: '父节点',
    align:"center",
    dataIndex: 'pid'
   },
   {
    title: '层级',
    align:"center",
    dataIndex: 'level'
   },
   {
    title: '知识库id',
    align:"center",
    dataIndex: 'knowledgeId'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "节点名称",
      field: 'name',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "知识库id",
      field: 'knowledgeId',
      component: 'Input',
      //colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '节点名称',
    field: 'name',
    component: 'Input',
  },
  {
    label: '父节点',
    field: 'pid',
    component: 'Input',
  },
  {
    label: '层级',
    field: 'level',
    component: 'InputNumber',
  },
  {
    label: '知识库id',
    field: 'knowledgeId',
    component: 'Input',
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
  name: {title: '节点名称',order: 0,view: 'text', type: 'string',},
  pid: {title: '父节点',order: 1,view: 'text', type: 'string',},
  level: {title: '层级',order: 2,view: 'number', type: 'number',},
  knowledgeId: {title: '知识库id',order: 3,view: 'text', type: 'string',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}