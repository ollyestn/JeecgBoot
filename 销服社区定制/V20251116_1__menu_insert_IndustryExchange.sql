-- 注意：该页面对应的前台目录为views/smartcity文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


-- 主菜单
INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external)
VALUES ('176325937500301', NULL, '同业交流统计', '/smartcity/industryExchangeList', 'smartcity/IndustryExchangeList', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2025-11-16 10:16:15', NULL, NULL, 0);

-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176325937500302', '176325937500301', '添加同业交流统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:industry_exchange:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-16 10:16:15', NULL, NULL, 0, 0, '1', 0);

-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176325937500303', '176325937500301', '编辑同业交流统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:industry_exchange:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-16 10:16:15', NULL, NULL, 0, 0, '1', 0);

-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176325937500304', '176325937500301', '删除同业交流统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:industry_exchange:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-16 10:16:15', NULL, NULL, 0, 0, '1', 0);

-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176325937500305', '176325937500301', '批量删除同业交流统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:industry_exchange:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-16 10:16:15', NULL, NULL, 0, 0, '1', 0);

-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176325937500306', '176325937500301', '导出excel_同业交流统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:industry_exchange:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-16 10:16:15', NULL, NULL, 0, 0, '1', 0);

-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176325937500307', '176325937500301', '导入excel_同业交流统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:industry_exchange:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-16 10:16:15', NULL, NULL, 0, 0, '1', 0);

-- 角色授权（以 admin 角色为例，role_id 可替换）
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176325937500408', 'f6817f48af4fb3af11b9e8bf182f618b', '176325937500301', NULL, '2025-11-16 10:16:15', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176325937500409', 'f6817f48af4fb3af11b9e8bf182f618b', '176325937500302', NULL, '2025-11-16 10:16:15', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176325937500410', 'f6817f48af4fb3af11b9e8bf182f618b', '176325937500303', NULL, '2025-11-16 10:16:15', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176325937500411', 'f6817f48af4fb3af11b9e8bf182f618b', '176325937500304', NULL, '2025-11-16 10:16:15', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176325937500412', 'f6817f48af4fb3af11b9e8bf182f618b', '176325937500305', NULL, '2025-11-16 10:16:15', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176325937500413', 'f6817f48af4fb3af11b9e8bf182f618b', '176325937500306', NULL, '2025-11-16 10:16:15', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176325937500414', 'f6817f48af4fb3af11b9e8bf182f618b', '176325937500307', NULL, '2025-11-16 10:16:15', '127.0.0.1');