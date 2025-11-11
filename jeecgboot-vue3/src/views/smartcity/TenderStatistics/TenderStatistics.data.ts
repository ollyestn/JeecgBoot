import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '项目名称',
    align:"center",
    dataIndex: 'projectName'
   },
   {
    title: '项目类型',
    align:"center",
    dataIndex: 'projectType'
   },
   {
    title: '项目分类',
    align:"center",
    dataIndex: 'projectCategory'
   },
   {
    title: '投标时间',
    align:"center",
    dataIndex: 'biddingDate'
   },
   {
    title: '开标时间',
    align:"center",
    dataIndex: 'bidOpeningDate'
   },
   {
    title: '客户信息',
    align:"center",
    dataIndex: 'customerInfo'
   },
   {
    title: '竞争对手信息',
    align:"center",
    dataIndex: 'competitorInfo'
   },
   {
    title: '报价',
    align:"center",
    dataIndex: 'quotation'
   },
   {
    title: '技术方案得分',
    align:"center",
    dataIndex: 'technicalScore'
   },
   {
    title: '商务条款响应度',
    align:"center",
    dataIndex: 'responsiveBusinessTerms'
   },
   {
    title: '中标情况',
    align:"center",
    dataIndex: 'biddingSituation'
   },
   {
    title: '中标金额',
    align:"center",
    dataIndex: 'winBidAmount'
   },
   {
    title: '未中标原因',
    align:"center",
    dataIndex: 'reasonNotWinning'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
	{
      label: "项目名称",
      field: 'projectName',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "项目类型",
      field: 'projectType',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "项目分类",
      field: 'projectCategory',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "投标时间",
      field: 'biddingDate',
      component: 'DatePicker',
      componentProps: {
         showTime:true,
         valueFormat: 'YYYY-MM-DD HH:mm:ss'
       },
      //colProps: {span: 6},
 	},
	{
      label: "客户信息",
      field: 'customerInfo',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "竞争对手信息",
      field: 'competitorInfo',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "中标金额",
      field: 'winBidAmount',
      component: 'Input',
      //colProps: {span: 6},
 	},
	{
      label: "未中标原因",
      field: 'reasonNotWinning',
      component: 'Input',
      //colProps: {span: 6},
 	},
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '项目名称',
    field: 'projectName',
    component: 'Input',
  },
  {
    label: '项目类型',
    field: 'projectType',
    component: 'Input',
  },
  {
    label: '项目分类',
    field: 'projectCategory',
    component: 'Input',
  },
  {
    label: '投标时间',
    field: 'biddingDate',
    component: 'DatePicker',
    componentProps: {
       showTime: true,
       valueFormat: 'YYYY-MM-DD HH:mm:ss'
     },
  },
  {
    label: '开标时间',
    field: 'bidOpeningDate',
    component: 'DatePicker',
    componentProps: {
       showTime: true,
       valueFormat: 'YYYY-MM-DD HH:mm:ss'
     },
  },
  {
    label: '客户信息',
    field: 'customerInfo',
    component: 'Input',
  },
  {
    label: '竞争对手信息',
    field: 'competitorInfo',
    component: 'Input',
  },
  {
    label: '报价',
    field: 'quotation',
    component: 'InputNumber',
  },
  {
    label: '技术方案得分',
    field: 'technicalScore',
    component: 'InputNumber',
  },
  {
    label: '商务条款响应度',
    field: 'responsiveBusinessTerms',
    component: 'InputNumber',
  },
  {
    label: '中标情况',
    field: 'biddingSituation',
    component: 'Input',
  },
  {
    label: '中标金额',
    field: 'winBidAmount',
    component: 'Input',
  },
  {
    label: '未中标原因',
    field: 'reasonNotWinning',
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
  projectName: {title: '项目名称',order: 0,view: 'text', type: 'string',},
  projectType: {title: '项目类型',order: 1,view: 'text', type: 'string',},
  projectCategory: {title: '项目分类',order: 2,view: 'text', type: 'string',},
  biddingDate: {title: '投标时间',order: 3,view: 'datetime', type: 'string',},
  bidOpeningDate: {title: '开标时间',order: 4,view: 'datetime', type: 'string',},
  customerInfo: {title: '客户信息',order: 5,view: 'text', type: 'string',},
  competitorInfo: {title: '竞争对手信息',order: 6,view: 'text', type: 'string',},
  quotation: {title: '报价',order: 7,view: 'number', type: 'number',},
  technicalScore: {title: '技术方案得分',order: 8,view: 'number', type: 'number',},
  responsiveBusinessTerms: {title: '商务条款响应度',order: 9,view: 'number', type: 'number',},
  biddingSituation: {title: '中标情况',order: 10,view: 'text', type: 'string',},
  winBidAmount: {title: '中标金额',order: 11,view: 'text', type: 'string',},
  reasonNotWinning: {title: '未中标原因',order: 12,view: 'text', type: 'string',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}