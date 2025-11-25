-- PERMISSIONS
INSERT INTO permissions ( title, active, created_by, updated_by) VALUES ('ROLE_CREATE', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;
INSERT INTO permissions ( title, active, created_by, updated_by) VALUES ('ROLE_READ', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;
INSERT INTO permissions ( title, active, created_by, updated_by) VALUES ('ROLE_DELETE', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;
INSERT INTO permissions ( title, active, created_by, updated_by) VALUES ('ROLE_UPDATE', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;
INSERT INTO permissions ( title, active, created_by, updated_by) VALUES ('PERMISSION_CREATE', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;
INSERT INTO permissions ( title, active, created_by, updated_by) VALUES ('PERMISSION_READ', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;
INSERT INTO permissions ( title, active, created_by, updated_by) VALUES ('PERMISSION_DELETE', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;
INSERT INTO permissions ( title, active, created_by, updated_by) VALUES ('PERMISSION_UPDATE', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;
INSERT INTO permissions ( title, active, created_by, updated_by) VALUES ('USER_CREATE', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;
INSERT INTO permissions ( title, active, created_by, updated_by) VALUES ( 'USER_READ', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;
INSERT INTO permissions ( title, active, created_by, updated_by) VALUES ( 'USER_DELETE', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;
INSERT INTO permissions ( title, active, created_by, updated_by) VALUES ( 'USER_UPDATE', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

INSERT INTO roles (title, active, created_by, updated_by) VALUES ('ADMIN', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;
INSERT INTO roles (title, active, created_by, updated_by) VALUES ('MANAGER', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;
INSERT INTO roles (title, active, created_by, updated_by) VALUES ('CUSTOMER', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;
INSERT INTO roles (title, active, created_by, updated_by) VALUES ('CHATTER', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;
INSERT INTO roles (title, active, created_by, updated_by) VALUES ('SUDO', TRUE, 'system', 'system') ON CONFLICT (title) DO NOTHING;

INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES (TRUE, TRUE, 1, 1, 'system', 'system') ON CONFLICT DO NOTHING; -- ROLE_CREATE
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES (TRUE, TRUE, 1, 2, 'system', 'system') ON CONFLICT DO NOTHING; -- ROLE_READ
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES (TRUE, TRUE, 1, 3, 'system', 'system') ON CONFLICT DO NOTHING; -- ROLE_DELETE
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES (TRUE, TRUE, 1, 4, 'system', 'system') ON CONFLICT DO NOTHING; -- ROLE_UPDATE
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES (TRUE, TRUE, 1, 5, 'system', 'system') ON CONFLICT DO NOTHING; -- PERMISSION_CREATE
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES (TRUE, TRUE, 1, 6, 'system', 'system') ON CONFLICT DO NOTHING; -- PERMISSION_READ
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES (TRUE, TRUE, 1, 7, 'system', 'system') ON CONFLICT DO NOTHING; -- PERMISSION_DELETE
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES (TRUE, TRUE, 1, 8, 'system', 'system') ON CONFLICT DO NOTHING; -- PERMISSION_UPDATE
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES (TRUE, TRUE, 1, 9, 'system', 'system') ON CONFLICT DO NOTHING; -- USER_CREATE
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 1, 10, 'system', 'system') ON CONFLICT DO NOTHING; -- USER_READ
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 1, 11, 'system', 'system') ON CONFLICT DO NOTHING; -- USER_DELETE
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 1, 12, 'system', 'system') ON CONFLICT DO NOTHING; -- USER_UPDATE
-- MANAGER gets role/permissimanagement + full user ops (8 entries)
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 2, 1, 'system', 'system') ON CONFLICT DO NOTHING; -- ROLE_CREATE
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 2, 2, 'system', 'system') ON CONFLICT DO NOTHING; -- ROLE_READ
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 2, 5, 'system', 'system') ON CONFLICT DO NOTHING; -- PERMISSION_CREATE
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 2, 6, 'system', 'system') ON CONFLICT DO NOTHING; -- PERMISSION_READ
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 2, 9, 'system', 'system') ON CONFLICT DO NOTHING; -- USER_CREATE
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 2, 10, 'system', 'system') ON CONFLICT DO NOTHING; -- USER_READ
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 2, 11, 'system', 'system') ON CONFLICT DO NOTHING; -- USER_DELETE
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 2, 12, 'system', 'system') ON CONFLICT DO NOTHING; -- USER_UPDATE
-- CUSTOMER gets basic user r (1 entry)
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 3, 10, 'system', 'system') ON CONFLICT DO NOTHING; -- USER_READ
-- CHATTER gets basic user re(1 entry)
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 4, 10, 'system', 'system') ON CONFLICT DO NOTHING; -- USER_READ
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 5, 1, 'system', 'system') ON CONFLICT DO NOTHING; -- ROLE_CREATE
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 5, 2, 'system', 'system') ON CONFLICT DO NOTHING; -- ROLE_READ
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 5, 3, 'system', 'system') ON CONFLICT DO NOTHING; -- ROLE_DELETE
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 5, 4, 'system', 'system') ON CONFLICT DO NOTHING; -- ROLE_UPDATE
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 5, 5, 'system', 'system') ON CONFLICT DO NOTHING; -- PERMISSION_CREATE
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 5, 6, 'system', 'system') ON CONFLICT DO NOTHING; -- PERMISSION_READ
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 5, 7, 'system', 'system') ON CONFLICT DO NOTHING; -- PERMISSION_DELETE
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 5, 8, 'system', 'system') ON CONFLICT DO NOTHING; -- PERMISSION_UPDATE
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 5, 9, 'system', 'system') ON CONFLICT DO NOTHING; -- USER_CREATE
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 5, 10, 'system', 'system') ON CONFLICT DO NOTHING; -- USER_READ
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 5, 11, 'system', 'system') ON CONFLICT DO NOTHING; -- USER_DELETE
INSERT INTO role_permission ( active, active_status, role_id, permission_id, created_by, updated_by) VALUES ( TRUE, TRUE, 5, 12, 'system', 'system') ON CONFLICT DO NOTHING; -- USER_UPDATE

-- USERS
INSERT INTO users ( name, user_name, password, email, email_verified, gender, account_non_expired,account_non_locked,enabled,created_by, updated_by) VALUES ( 'Admin User', 'admin', '$2a$12$ONjnFEA96ZSc3c8OyYkCyeI5rFnY59A/EIX8.AagTQ3o3TIbkXelW', 'admin@example.com', TRUE, 'MALE',TRUE,TRUE,TRUE, 'system', 'system') ON CONFLICT (user_name) DO NOTHING;
INSERT INTO users ( name, user_name, password, email, email_verified, gender, account_non_expired,account_non_locked,enabled,created_by, updated_by) VALUES ( 'Manager User', 'manager', '$2a$12$ONjnFEA96ZSc3c8OyYkCyeI5rFnY59A/EIX8.AagTQ3o3TIbkXelW', 'manager@example.com', FALSE, 'MALE',TRUE,TRUE,TRUE, 'system', 'system') ON CONFLICT (user_name) DO NOTHING;
INSERT INTO users ( name, user_name, password, email, email_verified, gender, account_non_expired,account_non_locked,enabled,created_by, updated_by) VALUES ( 'Customer User', 'customer', '$2b$12$Y8nE0RA62LTufclPbIQooeGli1mXma8l1FWXi/Rr0HPLLP2q18OE2', 'customer@example.com', FALSE, 'FEMALE',TRUE,TRUE,TRUE, 'system', 'system') ON CONFLICT (user_name) DO NOTHING;
INSERT INTO users ( name, user_name, password, email, email_verified, gender, account_non_expired,account_non_locked,enabled,created_by, updated_by) VALUES ( 'Chatter User', 'chatter', '$2b$12$99aNhkJqKpRBIu1GdDZRNuxaF0hdzxYwd/aLn6JPlpHPItz2lzvVe', 'chatter@example.com', FALSE, 'MALE',TRUE,TRUE,TRUE, 'system', 'system') ON CONFLICT (user_name) DO NOTHING;
INSERT INTO users ( name, user_name, password, email, email_verified, gender, account_non_expired,account_non_locked,enabled,created_by, updated_by) VALUES ( 'Sudo User', 'sudo', '$2b$12$Y9UHQm81cSmjqRQ89d6wfuLvbIktiDLE2OGw0U6D7nwG3NcUxu3uq', 'sudo@example.com', TRUE, 'MALE',TRUE,TRUE,TRUE, 'system', 'system') ON CONFLICT (user_name) DO NOTHING;
