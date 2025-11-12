import { render } from '@/common/renderUtils';
//列表数据
export const columns = [
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
    dataIndex: 'biddingDate',
   },
   {
    title: '开标时间',
    align:"center",
    dataIndex: 'bidOpeningDate',
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