import { render } from '@/common/renderUtils';
//列表数据
export const columns = [
    {
    title: '日期',
    align:"center",
    dataIndex: 'trainingTime',
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
];