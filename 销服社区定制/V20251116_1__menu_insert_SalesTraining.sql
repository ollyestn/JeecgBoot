-- 注意：该页面对应的前台目录为views/smartcity文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


-- 主菜单
INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external)
VALUES ('176330303521601', NULL, '培训统计', '/smartcity/salesTrainingList', 'smartcity/SalesTrainingList', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2025-11-16 22:23:55', NULL, NULL, 0);

-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176330303521602', '176330303521601', '添加培训统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:sales_training:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-16 22:23:55', NULL, NULL, 0, 0, '1', 0);

-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176330303521603', '176330303521601', '编辑培训统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:sales_training:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-16 22:23:55', NULL, NULL, 0, 0, '1', 0);

-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176330303521604', '176330303521601', '删除培训统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:sales_training:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-16 22:23:55', NULL, NULL, 0, 0, '1', 0);

-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176330303521605', '176330303521601', '批量删除培训统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:sales_training:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-16 22:23:55', NULL, NULL, 0, 0, '1', 0);

-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176330303521606', '176330303521601', '导出excel_培训统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:sales_training:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-16 22:23:55', NULL, NULL, 0, 0, '1', 0);

-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176330303521607', '176330303521601', '导入excel_培训统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:sales_training:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-16 22:23:55', NULL, NULL, 0, 0, '1', 0);

-- 角色授权（以 admin 角色为例，role_id 可替换）
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176330303521608', 'f6817f48af4fb3af11b9e8bf182f618b', '176330303521601', NULL, '2025-11-16 22:23:55', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176330303521609', 'f6817f48af4fb3af11b9e8bf182f618b', '176330303521602', NULL, '2025-11-16 22:23:55', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176330303521610', 'f6817f48af4fb3af11b9e8bf182f618b', '176330303521603', NULL, '2025-11-16 22:23:55', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176330303521611', 'f6817f48af4fb3af11b9e8bf182f618b', '176330303521604', NULL, '2025-11-16 22:23:55', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176330303521612', 'f6817f48af4fb3af11b9e8bf182f618b', '176330303521605', NULL, '2025-11-16 22:23:55', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176330303521613', 'f6817f48af4fb3af11b9e8bf182f618b', '176330303521606', NULL, '2025-11-16 22:23:55', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176330303521614', 'f6817f48af4fb3af11b9e8bf182f618b', '176330303521607', NULL, '2025-11-16 22:23:55', '127.0.0.1');