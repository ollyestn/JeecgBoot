import { render } from '@/common/renderUtils';
//列表数据
export const columns = [
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