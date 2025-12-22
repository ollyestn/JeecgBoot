-- 注意：该页面对应的前台目录为views/smartcity文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


-- 主菜单
INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external)
VALUES ('176425452363901', NULL, '知识库目录', '/smartcity/airagKnowledgeTreeList', 'smartcity/AiragKnowledgeTreeList', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2025-11-27 22:42:03', NULL, NULL, 0);

-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176425452363902', '176425452363901', '添加知识库目录', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:airag_knowledge_tree:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-27 22:42:03', NULL, NULL, 0, 0, '1', 0);

-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176425452363903', '176425452363901', '编辑知识库目录', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:airag_knowledge_tree:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-27 22:42:03', NULL, NULL, 0, 0, '1', 0);

-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176425452363904', '176425452363901', '删除知识库目录', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:airag_knowledge_tree:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-27 22:42:03', NULL, NULL, 0, 0, '1', 0);

-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176425452363905', '176425452363901', '批量删除知识库目录', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:airag_knowledge_tree:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-27 22:42:03', NULL, NULL, 0, 0, '1', 0);

-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176425452363906', '176425452363901', '导出excel_知识库目录', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:airag_knowledge_tree:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-27 22:42:03', NULL, NULL, 0, 0, '1', 0);

-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('176425452363907', '176425452363901', '导入excel_知识库目录', NULL, NULL, 0, NULL, NULL, 2, 'smartcity:airag_knowledge_tree:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2025-11-27 22:42:03', NULL, NULL, 0, 0, '1', 0);

-- 角色授权（以 admin 角色为例，role_id 可替换）
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176425452363908', 'f6817f48af4fb3af11b9e8bf182f618b', '176425452363901', NULL, '2025-11-27 22:42:03', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176425452363909', 'f6817f48af4fb3af11b9e8bf182f618b', '176425452363902', NULL, '2025-11-27 22:42:03', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176425452363910', 'f6817f48af4fb3af11b9e8bf182f618b', '176425452363903', NULL, '2025-11-27 22:42:03', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176425452363911', 'f6817f48af4fb3af11b9e8bf182f618b', '176425452363904', NULL, '2025-11-27 22:42:03', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176425452363912', 'f6817f48af4fb3af11b9e8bf182f618b', '176425452363905', NULL, '2025-11-27 22:42:03', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176425452363913', 'f6817f48af4fb3af11b9e8bf182f618b', '176425452363906', NULL, '2025-11-27 22:42:03', '127.0.0.1');
INSERT INTO sys_role_permission (id, role_id, permission_id, data_rule_ids, operate_date, operate_ip) VALUES ('176425452364014', 'f6817f48af4fb3af11b9e8bf182f618b', '176425452363907', NULL, '2025-11-27 22:42:03', '127.0.0.1');