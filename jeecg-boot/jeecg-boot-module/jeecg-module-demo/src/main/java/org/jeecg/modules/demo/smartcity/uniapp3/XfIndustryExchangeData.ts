import { render } from '@/common/renderUtils';
//列表数据
export const columns = [
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