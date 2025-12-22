-- 注意：该页面对应的前台目录为views/smartcity文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


-- 主菜单
INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external)
VALUES ('176292670670601', NULL, '投标统计', '/smartcity/tenderStatisticsList', 'smartcity/TenderStatisticsList', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2025-11-12 13:51:46', NULL, NULL, 0);

-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176292670670602', '176292670670601', '添加投标统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:tender_statistics:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-12 13:51:46', NULL, NULL, 0, 0, '1', 0);

-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176292670670603', '176292670670601', '编辑投标统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:tender_statistics:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-12 13:51:46', NULL, NULL, 0, 0, '1', 0);

-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176292670670604', '176292670670601', '删除投标统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:tender_statistics:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-12 13:51:46', NULL, NULL, 0, 0, '1', 0);

-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176292670670605', '176292670670601', '批量删除投标统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:tender_statistics:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-12 13:51:46', NULL, NULL, 0, 0, '1', 0);

-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176292670670606', '176292670670601', '导出excel_投标统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:tender_statistics:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-12 13:51:46', NULL, NULL, 0, 0, '1', 0);

-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176292670670607', '176292670670601', '导入excel_投标统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:tender_statistics:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-12 13:51:46', NULL, NULL, 0, 0, '1', 0);

-- 角色授权（以 admin 角色为例，role_id 可替换）
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176292670670608', 'f6817f48af4fb3af11b9e8bf182f618b', '176292670670601', NULL, '2025-11-12 13:51:46', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176292670670609', 'f6817f48af4fb3af11b9e8bf182f618b', '176292670670602', NULL, '2025-11-12 13:51:46', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176292670670610', 'f6817f48af4fb3af11b9e8bf182f618b', '176292670670603', NULL, '2025-11-12 13:51:46', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176292670670611', 'f6817f48af4fb3af11b9e8bf182f618b', '176292670670604', NULL, '2025-11-12 13:51:46', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176292670670612', 'f6817f48af4fb3af11b9e8bf182f618b', '176292670670605', NULL, '2025-11-12 13:51:46', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176292670670613', 'f6817f48af4fb3af11b9e8bf182f618b', '176292670670606', NULL, '2025-11-12 13:51:46', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176292670670614', 'f6817f48af4fb3af11b9e8bf182f618b', '176292670670607', NULL, '2025-11-12 13:51:46', '127.0.0.1');