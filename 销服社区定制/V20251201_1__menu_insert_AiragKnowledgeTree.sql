-- 注意：该页面对应的前台目录为views/smartcity文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


-- 主菜单
INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external)
VALUES ('176453283543701', NULL, '知识库目录', '/smartcity/airagKnowledgeTreeList', 'smartcity/AiragKnowledgeTreeList', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2025-12-01 04:00:35', NULL, NULL, 0);

-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176453283543702', '176453283543701', '添加知识库目录', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:airag_knowledge_tree:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-12-01 04:00:35', NULL, NULL, 0, 0, '1', 0);

-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176453283543903', '176453283543701', '编辑知识库目录', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:airag_knowledge_tree:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-12-01 04:00:35', NULL, NULL, 0, 0, '1', 0);

-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176453283543904', '176453283543701', '删除知识库目录', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:airag_knowledge_tree:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-12-01 04:00:35', NULL, NULL, 0, 0, '1', 0);

-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176453283543905', '176453283543701', '批量删除知识库目录', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:airag_knowledge_tree:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-12-01 04:00:35', NULL, NULL, 0, 0, '1', 0);

-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176453283543906', '176453283543701', '导出excel_知识库目录', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:airag_knowledge_tree:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-12-01 04:00:35', NULL, NULL, 0, 0, '1', 0);

-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176453283543907', '176453283543701', '导入excel_知识库目录', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:airag_knowledge_tree:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-12-01 04:00:35', NULL, NULL, 0, 0, '1', 0);

-- 角色授权（以 admin 角色为例，role_id 可替换）
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176453283544008', 'f6817f48af4fb3af11b9e8bf182f618b', '176453283543701', NULL, '2025-12-01 04:00:35', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176453283544009', 'f6817f48af4fb3af11b9e8bf182f618b', '176453283543702', NULL, '2025-12-01 04:00:35', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176453283544010', 'f6817f48af4fb3af11b9e8bf182f618b', '176453283543903', NULL, '2025-12-01 04:00:35', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176453283544011', 'f6817f48af4fb3af11b9e8bf182f618b', '176453283543904', NULL, '2025-12-01 04:00:35', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176453283544012', 'f6817f48af4fb3af11b9e8bf182f618b', '176453283543905', NULL, '2025-12-01 04:00:35', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176453283544013', 'f6817f48af4fb3af11b9e8bf182f618b', '176453283543906', NULL, '2025-12-01 04:00:35', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176453283544014', 'f6817f48af4fb3af11b9e8bf182f618b', '176453283543907', NULL, '2025-12-01 04:00:35', '127.0.0.1');