-- SUDO User (God-mode for devs)
INSERT INTO users (name, user_name, password, email, email_verified, gender, account_non_expired, account_non_locked, enabled, created_by, updated_by)
VALUES ('Sudo Master', 'sudo', '$2a$12$ONjnFEA96ZSc3c8OyYkCyeI5rFnY59A/EIX8.AagTQ3o3TIbkXelW', 'sudo@devops.com', TRUE, 'MALE', TRUE, TRUE, TRUE, 'system', 'system')
ON CONFLICT (user_name) DO NOTHING;
-- ADMIN Users (System admins; multiple for team simulation)
INSERT INTO users (name, user_name, password, email, email_verified, gender, account_non_expired, account_non_locked, enabled, created_by, updated_by)
VALUES ('Admin Lead', 'admin', '$2a$12$ONjnFEA96ZSc3c8OyYkCyeI5rFnY59A/EIX8.AagTQ3o3TIbkXelW', 'admin@company.com', TRUE, 'MALE', TRUE, TRUE, TRUE, 'system', 'system')
ON CONFLICT (user_name) DO NOTHING;
INSERT INTO users (name, user_name, password, email, email_verified, gender, account_non_expired, account_non_locked, enabled, created_by, updated_by)
VALUES ('Admin Assistant', 'admin2', '$2a$12$ABcdEFgHijKlMnOpQrStUvWxYz1234567890AbCdEfGhIjKlMn', 'admin2@company.com', TRUE, 'FEMALE', TRUE, TRUE, TRUE, 'system', 'system')
ON CONFLICT (user_name) DO NOTHING;

-- MANAGER Users (Team leads)
INSERT INTO users (name, user_name, password, email, email_verified, gender, account_non_expired, account_non_locked, enabled, created_by, updated_by)
VALUES ('Manager One', 'manager', '$2a$12$ONjnFEA96ZSc3c8OyYkCyeI5rFnY59A/EIX8.AagTQ3o3TIbkXelW', 'manager@team.com', FALSE, 'MALE', TRUE, TRUE, TRUE, 'system', 'system')
ON CONFLICT (user_name) DO NOTHING;

INSERT INTO users (name, user_name, password, email, email_verified, gender, account_non_expired, account_non_locked, enabled, created_by, updated_by)
VALUES ('Manager Two', 'manager2', '$2b$12$Y8nE0RA62LTufclPbIQooeGli1mXma8l1FWXi/Rr0HPLLP2q18OE2', 'manager2@team.com', TRUE, 'FEMALE', TRUE, TRUE, TRUE, 'system', 'system')
ON CONFLICT (user_name) DO NOTHING;

-- MODERATOR Users (Chat overseers)
INSERT INTO users (name, user_name, password, email, email_verified, gender, account_non_expired, account_non_locked, enabled, created_by, updated_by)
VALUES ('Moderator Pro', 'moderator', '$2b$12$99aNhkJqKpRBIu1GdDZRNuxaF0hdzxYwd/aLn6JPlpHPItz2lzvVe', 'mod@chatapp.com', TRUE, 'MALE', TRUE, TRUE, TRUE, 'system', 'system')
ON CONFLICT (user_name) DO NOTHING;

INSERT INTO users (name, user_name, password, email, email_verified, gender, account_non_expired, account_non_locked, enabled, created_by, updated_by)
VALUES ('Junior Moderator', 'mod_junior', '$2b$12$Y9UHQm81cSmjqRQ89d6wfuLvbIktiDLE2OGw0U6D7nwG3NcUxu3uq', 'junior@chatapp.com', FALSE, 'MALE', TRUE, TRUE, TRUE, 'system', 'system')
ON CONFLICT (user_name) DO NOTHING;

-- CHATTER Users (Standard chat participants; multiple for activity simulation)
INSERT INTO users (name, user_name, password, email, email_verified, gender, account_non_expired, account_non_locked, enabled, created_by, updated_by)
VALUES ('Active Chatter', 'chatter', '$2b$12$99aNhkJqKpRBIu1GdDZRNuxaF0hdzxYwd/aLn6JPlpHPItz2lzvVe', 'chatter1@users.com', FALSE, 'MALE', TRUE, TRUE, TRUE, 'system', 'system')
ON CONFLICT (user_name) DO NOTHING;

INSERT INTO users (name, user_name, password, email, email_verified, gender, account_non_expired, account_non_locked, enabled, created_by, updated_by)
VALUES ('Casual Chatter', 'chatter2', '$2a$12$ABcdEFgHijKlMnOpQrStUvWxYz1234567890AbCdEfGhIjKlMn', 'chatter2@users.com', TRUE, 'FEMALE', TRUE, TRUE, TRUE, 'system', 'system')
ON CONFLICT (user_name) DO NOTHING;

INSERT INTO users (name, user_name, password, email, email_verified, gender, account_non_expired, account_non_locked, enabled, created_by, updated_by)
VALUES ('New Chatter', 'chatter_new', '$2b$12$Y8nE0RA62LTufclPbIQooeGli1mXma8l1FWXi/Rr0HPLLP2q18OE2', 'new@users.com', FALSE, 'MALE', TRUE, TRUE, FALSE, 'system', 'system')  -- Disabled for pending approval
ON CONFLICT (user_name) DO NOTHING;

-- CUSTOMER Users (External/read-only; varied statuses for realism, e.g., one locked for simulation)
INSERT INTO users (name, user_name, password, email, email_verified, gender, account_non_expired, account_non_locked, enabled, created_by, updated_by)
VALUES ('Premium Customer', 'customer', '$2b$12$Y8nE0RA62LTufclPbIQooeGli1mXma8l1FWXi/Rr0HPLLP2q18OE2', 'premium@customers.com', FALSE, 'FEMALE', TRUE, TRUE, TRUE, 'system', 'system')
ON CONFLICT (user_name) DO NOTHING;

INSERT INTO users (name, user_name, password, email, email_verified, gender, account_non_expired, account_non_locked, enabled, created_by, updated_by)
VALUES ('Standard Customer', 'customer2', '$2a$12$ONjnFEA96ZSc3c8OyYkCyeI5rFnY59A/EIX8.AagTQ3o3TIbkXelW', 'standard@customers.com', TRUE, 'MALE', TRUE, FALSE, TRUE, 'system', 'system')  -- Locked account simulation
ON CONFLICT (user_name) DO NOTHING;

INSERT INTO users (name, user_name, password, email, email_verified, gender, account_non_expired, account_non_locked, enabled, created_by, updated_by)
VALUES ('Trial Customer', 'customer_trial', '$2b$12$99aNhkJqKpRBIu1GdDZRNuxaF0hdzxYwd/aLn6JPlpHPItz2lzvVe', 'trial@customers.com', FALSE, 'FEMALE', FALSE, TRUE, TRUE, 'system', 'system')  -- Expiring soon
ON CONFLICT (user_name) DO NOTHING;

-- SUDO User: All roles (god-mode)
INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'sudo'), (SELECT id FROM roles WHERE title = 'SUDO'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'sudo'), (SELECT id FROM roles WHERE title = 'ADMIN'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'sudo'), (SELECT id FROM roles WHERE title = 'MANAGER'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'sudo'), (SELECT id FROM roles WHERE title = 'MODERATOR'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'sudo'), (SELECT id FROM roles WHERE title = 'CHATTER'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'sudo'), (SELECT id FROM roles WHERE title = 'CUSTOMER'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

-- ADMIN Users: ADMIN + MANAGER + MODERATOR (hierarchical access)
INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'admin'), (SELECT id FROM roles WHERE title = 'ADMIN'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'admin'), (SELECT id FROM roles WHERE title = 'MANAGER'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'admin'), (SELECT id FROM roles WHERE title = 'MODERATOR'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'admin2'), (SELECT id FROM roles WHERE title = 'ADMIN'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'admin2'), (SELECT id FROM roles WHERE title = 'MANAGER'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'admin2'), (SELECT id FROM roles WHERE title = 'MODERATOR'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

-- MANAGER Users: MANAGER + CHATTER (user management + basic chat)
INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'manager'), (SELECT id FROM roles WHERE title = 'MANAGER'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'manager'), (SELECT id FROM roles WHERE title = 'CHATTER'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'manager2'), (SELECT id FROM roles WHERE title = 'MANAGER'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'manager2'), (SELECT id FROM roles WHERE title = 'CHATTER'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

-- MODERATOR Users: MODERATOR + CHATTER
INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'moderator'), (SELECT id FROM roles WHERE title = 'MODERATOR'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'moderator'), (SELECT id FROM roles WHERE title = 'CHATTER'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'mod_junior'), (SELECT id FROM roles WHERE title = 'MODERATOR'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'mod_junior'), (SELECT id FROM roles WHERE title = 'CHATTER'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

-- CHATTER Users: CHATTER only
INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'chatter'), (SELECT id FROM roles WHERE title = 'CHATTER'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'chatter2'), (SELECT id FROM roles WHERE title = 'CHATTER'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'chatter_new'), (SELECT id FROM roles WHERE title = 'CHATTER'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

-- CUSTOMER Users: CUSTOMER only
INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'customer'), (SELECT id FROM roles WHERE title = 'CUSTOMER'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'customer2'), (SELECT id FROM roles WHERE title = 'CUSTOMER'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

INSERT INTO user_role (user_id, role_id, created_by, updated_by)
VALUES ((SELECT id FROM users WHERE user_name = 'customer_trial'), (SELECT id FROM roles WHERE title = 'CUSTOMER'), 'SYSTEM', 'SYSTEM') ON CONFLICT DO NOTHING;

