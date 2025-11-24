-- 注意：该页面对应的前台目录为views/smartcity文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


-- 主菜单
INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external)
VALUES ('176321685616701', NULL, '投标统计', '/smartcity/tenderStatisticsList', 'smartcity/TenderStatisticsList', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2025-11-15 22:27:36', NULL, NULL, 0);

-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176321685616702', '176321685616701', '添加投标统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:tender_statistics:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-15 22:27:36', NULL, NULL, 0, 0, '1', 0);

-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176321685616703', '176321685616701', '编辑投标统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:tender_statistics:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-15 22:27:36', NULL, NULL, 0, 0, '1', 0);

-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176321685616704', '176321685616701', '删除投标统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:tender_statistics:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-15 22:27:36', NULL, NULL, 0, 0, '1', 0);

-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176321685616705', '176321685616701', '批量删除投标统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:tender_statistics:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-15 22:27:36', NULL, NULL, 0, 0, '1', 0);

-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176321685616706', '176321685616701', '导出excel_投标统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:tender_statistics:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-15 22:27:36', NULL, NULL, 0, 0, '1', 0);

-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176321685616707', '176321685616701', '导入excel_投标统计', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:tender_statistics:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-15 22:27:36', NULL, NULL, 0, 0, '1', 0);

-- 角色授权（以 admin 角色为例，role_id 可替换）
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176321685616808', 'f6817f48af4fb3af11b9e8bf182f618b', '176321685616701', NULL, '2025-11-15 22:27:36', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176321685616809', 'f6817f48af4fb3af11b9e8bf182f618b', '176321685616702', NULL, '2025-11-15 22:27:36', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176321685616810', 'f6817f48af4fb3af11b9e8bf182f618b', '176321685616703', NULL, '2025-11-15 22:27:36', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176321685616811', 'f6817f48af4fb3af11b9e8bf182f618b', '176321685616704', NULL, '2025-11-15 22:27:36', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176321685616812', 'f6817f48af4fb3af11b9e8bf182f618b', '176321685616705', NULL, '2025-11-15 22:27:36', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176321685616813', 'f6817f48af4fb3af11b9e8bf182f618b', '176321685616706', NULL, '2025-11-15 22:27:36', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176321685616814', 'f6817f48af4fb3af11b9e8bf182f618b', '176321685616707', NULL, '2025-11-15 22:27:36', '127.0.0.1');