import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '公司名称',
    align:"center",
    dataIndex: 'otherCompany'
   },
   {
    title: '行业',
    align:"center",
    dataIndex: 'industry'
   },
   {
    title: '文件类型',
    align:"center",
    dataIndex: 'fileType_dictText'
   },
   {
    title: '附件',
    align:"center",
    dataIndex: 'files',
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '公司名称',
    field: 'otherCompany',
    component: 'Input',
  },
  {
    label: '行业',
    field: 'industry',
    component: 'Input',
  },
  {
    label: '文件类型',
    field: 'fileType',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"file_type"
     },
  },
  {
    label: '附件',
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
  otherCompany: {title: '公司名称',order: 0,view: 'text', type: 'string',},
  industry: {title: '行业',order: 1,view: 'text', type: 'string',},
  fileType: {title: '文件类型',order: 2,view: 'list', type: 'string',dictCode: 'file_type',},
  files: {title: '附件',order: 3,view: 'file', type: 'string',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}