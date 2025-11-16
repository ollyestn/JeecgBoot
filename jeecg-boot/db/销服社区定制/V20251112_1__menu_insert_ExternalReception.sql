-- 注意：该页面对应的前台目录为views/smartcity文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


-- 主菜单
INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external)
VALUES ('176292577659601', NULL, '对外接待', '/smartcity/externalReceptionList', 'smartcity/ExternalReceptionList', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2025-11-12 13:36:16', NULL, NULL, 0);

-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176292577659602', '176292577659601', '添加对外接待', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:external_reception:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-12 13:36:16', NULL, NULL, 0, 0, '1', 0);

-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176292577659603', '176292577659601', '编辑对外接待', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:external_reception:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-12 13:36:16', NULL, NULL, 0, 0, '1', 0);

-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176292577659604', '176292577659601', '删除对外接待', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:external_reception:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-12 13:36:16', NULL, NULL, 0, 0, '1', 0);

-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176292577659605', '176292577659601', '批量删除对外接待', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:external_reception:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-12 13:36:16', NULL, NULL, 0, 0, '1', 0);

-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176292577659606', '176292577659601', '导出excel_对外接待', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:external_reception:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-12 13:36:16', NULL, NULL, 0, 0, '1', 0);

-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176292577659607', '176292577659601', '导入excel_对外接待', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:external_reception:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-12 13:36:16', NULL, NULL, 0, 0, '1', 0);

-- 角色授权（以 admin 角色为例，role_id 可替换）
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176292577659608', 'f6817f48af4fb3af11b9e8bf182f618b', '176292577659601', NULL, '2025-11-12 13:36:16', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176292577659609', 'f6817f48af4fb3af11b9e8bf182f618b', '176292577659602', NULL, '2025-11-12 13:36:16', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176292577659610', 'f6817f48af4fb3af11b9e8bf182f618b', '176292577659603', NULL, '2025-11-12 13:36:16', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176292577659611', 'f6817f48af4fb3af11b9e8bf182f618b', '176292577659604', NULL, '2025-11-12 13:36:16', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176292577659612', 'f6817f48af4fb3af11b9e8bf182f618b', '176292577659605', NULL, '2025-11-12 13:36:16', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176292577659613', 'f6817f48af4fb3af11b9e8bf182f618b', '176292577659606', NULL, '2025-11-12 13:36:16', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176292577659614', 'f6817f48af4fb3af11b9e8bf182f618b', '176292577659607', NULL, '2025-11-12 13:36:16', '127.0.0.1');