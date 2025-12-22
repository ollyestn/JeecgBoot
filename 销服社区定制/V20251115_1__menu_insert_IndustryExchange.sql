-- 注意：该页面对应的前台目录为views/smartcity文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


-- 主菜单
INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external)
VALUES ('176321853586001', NULL, '同业交流统计', '/smartcity/industryExchangeList', 'smartcity/IndustryExchangeList', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2025-11-15 22:55:35', NULL, NULL, 0);

-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176321853586102', '176321853586001', '添加同业交流统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:industry_exchange:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-15 22:55:35', NULL, NULL, 0, 0, '1', 0);

-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176321853586103', '176321853586001', '编辑同业交流统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:industry_exchange:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-15 22:55:35', NULL, NULL, 0, 0, '1', 0);

-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176321853586104', '176321853586001', '删除同业交流统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:industry_exchange:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-15 22:55:35', NULL, NULL, 0, 0, '1', 0);

-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176321853586105', '176321853586001', '批量删除同业交流统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:industry_exchange:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-15 22:55:35', NULL, NULL, 0, 0, '1', 0);

-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176321853586106', '176321853586001', '导出excel_同业交流统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:industry_exchange:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-15 22:55:35', NULL, NULL, 0, 0, '1', 0);

-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176321853586107', '176321853586001', '导入excel_同业交流统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:industry_exchange:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-15 22:55:35', NULL, NULL, 0, 0, '1', 0);

-- 角色授权（以 admin 角色为例，role_id 可替换）
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176321853586108', 'f6817f48af4fb3af11b9e8bf182f618b', '176321853586001', NULL, '2025-11-15 22:55:35', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176321853586109', 'f6817f48af4fb3af11b9e8bf182f618b', '176321853586102', NULL, '2025-11-15 22:55:35', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176321853586110', 'f6817f48af4fb3af11b9e8bf182f618b', '176321853586103', NULL, '2025-11-15 22:55:35', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176321853586111', 'f6817f48af4fb3af11b9e8bf182f618b', '176321853586104', NULL, '2025-11-15 22:55:35', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176321853586112', 'f6817f48af4fb3af11b9e8bf182f618b', '176321853586105', NULL, '2025-11-15 22:55:35', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176321853586113', 'f6817f48af4fb3af11b9e8bf182f618b', '176321853586106', NULL, '2025-11-15 22:55:35', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176321853586114', 'f6817f48af4fb3af11b9e8bf182f618b', '176321853586107', NULL, '2025-11-15 22:55:35', '127.0.0.1');