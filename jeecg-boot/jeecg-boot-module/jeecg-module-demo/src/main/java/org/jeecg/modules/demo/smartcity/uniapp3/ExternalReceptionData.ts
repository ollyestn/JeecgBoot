import { render } from '@/common/renderUtils';
//列表数据
export const columns = [
    {
    title: '来访日期',
    align:"center",
    dataIndex: 'visitDate',
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