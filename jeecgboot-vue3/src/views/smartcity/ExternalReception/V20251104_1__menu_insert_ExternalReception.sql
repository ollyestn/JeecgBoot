-- 注意：该页面对应的前台目录为views/smartcity文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


-- 主菜单
INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external)
VALUES ('176223991831901', NULL, '对外接待', '/smartcity/externalReceptionList', 'smartcity/ExternalReceptionList', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2025-11-04 15:05:18', NULL, NULL, 0);

-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176223991831902', '176223991831901', '添加对外接待', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:external_reception:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-04 15:05:18', NULL, NULL, 0, 0, '1', 0);

-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176223991831903', '176223991831901', '编辑对外接待', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:external_reception:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-04 15:05:18', NULL, NULL, 0, 0, '1', 0);

-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176223991831904', '176223991831901', '删除对外接待', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:external_reception:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-04 15:05:18', NULL, NULL, 0, 0, '1', 0);

-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176223991831905', '176223991831901', '批量删除对外接待', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:external_reception:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-04 15:05:18', NULL, NULL, 0, 0, '1', 0);

-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176223991831906', '176223991831901', '导出excel_对外接待', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:external_reception:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-04 15:05:18', NULL, NULL, 0, 0, '1', 0);

-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176223991831907', '176223991831901', '导入excel_对外接待', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:external_reception:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-04 15:05:18', NULL, NULL, 0, 0, '1', 0);

-- 角色授权（以 admin 角色为例，role_id 可替换）
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176223991831908', 'f6817f48af4fb3af11b9e8bf182f618b', '176223991831901', NULL, '2025-11-04 15:05:18', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176223991831909', 'f6817f48af4fb3af11b9e8bf182f618b', '176223991831902', NULL, '2025-11-04 15:05:18', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176223991831910', 'f6817f48af4fb3af11b9e8bf182f618b', '176223991831903', NULL, '2025-11-04 15:05:18', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176223991831911', 'f6817f48af4fb3af11b9e8bf182f618b', '176223991831904', NULL, '2025-11-04 15:05:18', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176223991831912', 'f6817f48af4fb3af11b9e8bf182f618b', '176223991831905', NULL, '2025-11-04 15:05:18', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176223991831913', 'f6817f48af4fb3af11b9e8bf182f618b', '176223991831906', NULL, '2025-11-04 15:05:18', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176223991831914', 'f6817f48af4fb3af11b9e8bf182f618b', '176223991831907', NULL, '2025-11-04 15:05:18', '127.0.0.1');