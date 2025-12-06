INSERT INTO permissions (title, active, created_by, updated_by)
VALUES ('PERMISSION_CREATE', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

INSERT INTO permissions (title, active, created_by, updated_by)
VALUES ('PERMISSION_READ', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

INSERT INTO permissions (title, active, created_by, updated_by)
VALUES ('PERMISSION_UPDATE', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

INSERT INTO permissions (title, active, created_by, updated_by)
VALUES ('PERMISSION_DELETE', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

INSERT INTO permissions (title, active, created_by, updated_by)
VALUES ('ROLE_CREATE', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

INSERT INTO permissions (title, active, created_by, updated_by)
VALUES ('ROLE_READ', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

INSERT INTO permissions (title, active, created_by, updated_by)
VALUES ('ROLE_UPDATE', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

INSERT INTO permissions (title, active, created_by, updated_by)
VALUES ('ROLE_DELETE', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

INSERT INTO permissions (title, active, created_by, updated_by)
VALUES ('USER_CREATE', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

INSERT INTO permissions (title, active, created_by, updated_by)
VALUES ('USER_READ', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

INSERT INTO permissions (title, active, created_by, updated_by)
VALUES ('USER_UPDATE', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

INSERT INTO permissions (title, active, created_by, updated_by)
VALUES ('USER_DELETE', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

-- Extended permissions for Chat module (simulating real app features)
INSERT INTO permissions (title, active, created_by, updated_by)
VALUES ('CHAT_CREATE', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

INSERT INTO permissions (title, active, created_by, updated_by)
VALUES ('CHAT_READ', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

INSERT INTO permissions (title, active, created_by, updated_by)
VALUES ('CHAT_UPDATE', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

INSERT INTO permissions (title, active, created_by, updated_by)
VALUES ('CHAT_DELETE', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

INSERT INTO permissions (title, active, created_by, updated_by)
VALUES ('MESSAGE_SEND', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

INSERT INTO permissions (title, active, created_by, updated_by)
VALUES ('MESSAGE_READ', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

INSERT INTO permissions (title, active, created_by, updated_by)
VALUES ('AUDIT_LOG_READ', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;  -- For compliance in real systems


INSERT INTO roles (title, active, created_by, updated_by)
VALUES ('SUDO', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

INSERT INTO roles (title, active, created_by, updated_by)
VALUES ('ADMIN', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

INSERT INTO roles (title, active, created_by, updated_by)
VALUES ('MANAGER', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

INSERT INTO roles (title, active, created_by, updated_by)
VALUES ('MODERATOR', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

INSERT INTO roles (title, active, created_by, updated_by)
VALUES ('CHATTER', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

INSERT INTO roles (title, active, created_by, updated_by)
VALUES ('CUSTOMER', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;


-- SUDO Role (ID: 1) - Full access to everything (god-mode for simulations)
INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 1, (SELECT id FROM permissions WHERE title = 'PERMISSION_CREATE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 1, (SELECT id FROM permissions WHERE title = 'PERMISSION_READ'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 1, (SELECT id FROM permissions WHERE title = 'PERMISSION_UPDATE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 1, (SELECT id FROM permissions WHERE title = 'PERMISSION_DELETE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 1, (SELECT id FROM permissions WHERE title = 'ROLE_CREATE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 1, (SELECT id FROM permissions WHERE title = 'ROLE_READ'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 1, (SELECT id FROM permissions WHERE title = 'ROLE_UPDATE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 1, (SELECT id FROM permissions WHERE title = 'ROLE_DELETE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 1, (SELECT id FROM permissions WHERE title = 'USER_CREATE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 1, (SELECT id FROM permissions WHERE title = 'USER_READ'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 1, (SELECT id FROM permissions WHERE title = 'USER_UPDATE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 1, (SELECT id FROM permissions WHERE title = 'USER_DELETE'), 'system', 'system') ON CONFLICT DO NOTHING;

-- Chat extensions for SUDO
INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 1, (SELECT id FROM permissions WHERE title = 'CHAT_CREATE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 1, (SELECT id FROM permissions WHERE title = 'CHAT_READ'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 1, (SELECT id FROM permissions WHERE title = 'CHAT_UPDATE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 1, (SELECT id FROM permissions WHERE title = 'CHAT_DELETE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 1, (SELECT id FROM permissions WHERE title = 'MESSAGE_SEND'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 1, (SELECT id FROM permissions WHERE title = 'MESSAGE_READ'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 1, (SELECT id FROM permissions WHERE title = 'AUDIT_LOG_READ'), 'system', 'system') ON CONFLICT DO NOTHING;

-- ADMIN Role (ID: 2) - Full RBAC management, limited chat moderation
INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 2, (SELECT id FROM permissions WHERE title = 'PERMISSION_CREATE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 2, (SELECT id FROM permissions WHERE title = 'PERMISSION_READ'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 2, (SELECT id FROM permissions WHERE title = 'PERMISSION_UPDATE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 2, (SELECT id FROM permissions WHERE title = 'PERMISSION_DELETE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 2, (SELECT id FROM permissions WHERE title = 'ROLE_CREATE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 2, (SELECT id FROM permissions WHERE title = 'ROLE_READ'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 2, (SELECT id FROM permissions WHERE title = 'ROLE_UPDATE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 2, (SELECT id FROM permissions WHERE title = 'ROLE_DELETE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 2, (SELECT id FROM permissions WHERE title = 'USER_CREATE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 2, (SELECT id FROM permissions WHERE title = 'USER_READ'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 2, (SELECT id FROM permissions WHERE title = 'USER_UPDATE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 2, (SELECT id FROM permissions WHERE title = 'USER_DELETE'), 'system', 'system') ON CONFLICT DO NOTHING;

-- Chat moderation for ADMIN
INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 2, (SELECT id FROM permissions WHERE title = 'CHAT_UPDATE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 2, (SELECT id FROM permissions WHERE title = 'CHAT_DELETE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 2, (SELECT id FROM permissions WHERE title = 'MESSAGE_READ'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 2, (SELECT id FROM permissions WHERE title = 'AUDIT_LOG_READ'), 'system', 'system') ON CONFLICT DO NOTHING;

-- MANAGER Role (ID: 3) - User management only, basic chat access
INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 3, (SELECT id FROM permissions WHERE title = 'ROLE_READ'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 3, (SELECT id FROM permissions WHERE title = 'USER_CREATE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 3, (SELECT id FROM permissions WHERE title = 'USER_READ'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 3, (SELECT id FROM permissions WHERE title = 'USER_UPDATE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 3, (SELECT id FROM permissions WHERE title = 'USER_DELETE'), 'system', 'system') ON CONFLICT DO NOTHING;

-- Chat for MANAGER
INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 3, (SELECT id FROM permissions WHERE title = 'CHAT_CREATE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 3, (SELECT id FROM permissions WHERE title = 'CHAT_READ'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 3, (SELECT id FROM permissions WHERE title = 'MESSAGE_SEND'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 3, (SELECT id FROM permissions WHERE title = 'MESSAGE_READ'), 'system', 'system') ON CONFLICT DO NOTHING;

-- MODERATOR Role (ID: 4) - Chat moderation focus
INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 4, (SELECT id FROM permissions WHERE title = 'USER_READ'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 4, (SELECT id FROM permissions WHERE title = 'CHAT_READ'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 4, (SELECT id FROM permissions WHERE title = 'CHAT_UPDATE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 4, (SELECT id FROM permissions WHERE title = 'CHAT_DELETE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 4, (SELECT id FROM permissions WHERE title = 'MESSAGE_READ'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 4, (SELECT id FROM permissions WHERE title = 'AUDIT_LOG_READ'), 'system', 'system') ON CONFLICT DO NOTHING;

-- CHATTER Role (ID: 5) - Basic chat user (no admin powers)
INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 5, (SELECT id FROM permissions WHERE title = 'USER_READ'), 'system', 'system') ON CONFLICT DO NOTHING;  -- Self-read

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 5, (SELECT id FROM permissions WHERE title = 'CHAT_CREATE'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 5, (SELECT id FROM permissions WHERE title = 'CHAT_READ'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 5, (SELECT id FROM permissions WHERE title = 'MESSAGE_SEND'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 5, (SELECT id FROM permissions WHERE title = 'MESSAGE_READ'), 'system', 'system') ON CONFLICT DO NOTHING;

-- CUSTOMER Role (ID: 6) - Read-only access (e.g., guest viewers)
INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 6, (SELECT id FROM permissions WHERE title = 'USER_READ'), 'system', 'system') ON CONFLICT DO NOTHING;  -- Self-read

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 6, (SELECT id FROM permissions WHERE title = 'CHAT_READ'), 'system', 'system') ON CONFLICT DO NOTHING;

INSERT INTO role_permission (active, active_status, role_id, permission_id, created_by, updated_by)
VALUES (TRUE, TRUE, 6, (SELECT id FROM permissions WHERE title = 'MESSAGE_READ'), 'system', 'system') ON CONFLICT DO NOTHING;

