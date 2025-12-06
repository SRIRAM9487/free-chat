INSERT INTO chatters (name,user_id,banned,created_by,updated_by)
VALUES ('CHATTER_1',1, FALSE, 'system', 'system');
INSERT INTO chatters (name,user_id,banned,created_by,updated_by)
VALUES ('CHATTER_2',2, TRUE, 'system', 'system') ;
INSERT INTO chatters (name,user_id,banned,created_by,updated_by)
VALUES ('CHATTER_3',3, FALSE, 'system', 'system');
INSERT INTO chatters (name,user_id,banned,created_by,updated_by)
VALUES ('CHATTER_4',4, TRUE, 'system', 'system') ;
INSERT INTO chatters (name,user_id,banned,created_by,updated_by)
VALUES ('CHATTER_5',5, FALSE, 'system', 'system');
INSERT INTO chatters (name,user_id,banned,created_by,updated_by)
VALUES ('CHATTER_6',6, TRUE, 'system', 'system') ;
INSERT INTO chatters (name,user_id,banned,created_by,updated_by)
VALUES ('CHATTER_7',7, FALSE, 'system', 'system');
INSERT INTO chatters (name,user_id,banned,created_by,updated_by)
VALUES ('CHATTER_8',8, TRUE, 'system', 'system') ;
INSERT INTO chatters (name,user_id,banned,created_by,updated_by)
VALUES ('CHATTER_9',9, FALSE, 'system', 'system');
INSERT INTO chatters (name,user_id,banned,created_by,updated_by)
VALUES ('CHATTER_10',10, TRUE, 'system', 'system');



INSERT INTO groups (name,description,admin_only,created_by,updated_by)
VALUES ('GROUP_1','THIS IS AN TEST GROUP 1', FALSE, 'system', 'system');
INSERT INTO groups (name,description,admin_only,created_by,updated_by)
VALUES ('GROUP_2','THIS IS AN TEST GROUP 2', FALSE, 'system', 'system');
INSERT INTO groups (name,description,admin_only,created_by,updated_by)
VALUES ('GROUP_3','THIS IS AN TEST GROUP 3', FALSE, 'system', 'system');
INSERT INTO groups (name,description,admin_only,created_by,updated_by)
VALUES ('GROUP_4','THIS IS AN TEST GROUP 4', FALSE, 'system', 'system');
INSERT INTO groups (name,description,admin_only,created_by,updated_by)
VALUES ('GROUP_5','THIS IS AN TEST GROUP 5', FALSE, 'system', 'system');
INSERT INTO groups (name,description,admin_only,created_by,updated_by)
VALUES ('GROUP_6','THIS IS AN TEST GROUP 6', FALSE, 'system', 'system');
INSERT INTO groups (name,description,admin_only,created_by,updated_by)
VALUES ('GROUP_7','THIS IS AN TEST GROUP 7', FALSE, 'system', 'system');
INSERT INTO groups (name,description,admin_only,created_by,updated_by)
VALUES ('GROUP_8','THIS IS AN TEST GROUP 8', FALSE, 'system', 'system');
INSERT INTO groups (name,description,admin_only,created_by,updated_by)
VALUES ('GROUP_9','THIS IS AN TEST GROUP 9', FALSE, 'system', 'system');
INSERT INTO groups (name,description,admin_only,created_by,updated_by)
VALUES ('GROUP_10','THIS IS AN TEST GROUP 10', FALSE, 'system', 'system');



-- GROUP 1 
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',6, 1, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',7, 1, 'system', 'system');

-- GROUP 2
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('ADMIN',8, 2, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('ADMIN',9, 2, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',10, 2, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',1, 2, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',2, 2, 'system', 'system');

-- GROUP 3
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('ADMIN',3, 3, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('ADMIN',4, 3, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',5, 3, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',6, 3, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',7, 3, 'system', 'system');

-- GROUP 4
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('ADMIN',8, 4, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',9, 4, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',10, 4, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',1, 4, 'system', 'system');

-- GROUP 5
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('ADMIN',2, 5, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('ADMIN',3, 5, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',4, 5, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',5, 5, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',6, 5, 'system', 'system');

-- GROUP 6
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('ADMIN',7, 6, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('ADMIN',8, 6, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',9, 6, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',10, 6, 'system', 'system');

-- GROUP 7
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('ADMIN',1, 7, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',2, 7, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',3, 7, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',4, 7, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',5, 7, 'system', 'system');

-- GROUP 8
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('ADMIN',6, 8, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('ADMIN',7, 8, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('ADMIN',8, 8, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',9, 8, 'system', 'system');

-- GROUP 9
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('ADMIN',10, 9, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',1, 9, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',2, 9, 'system', 'system');

-- GROUP 10
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('ADMIN',3, 10, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',4, 10, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',5, 10, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',6, 10, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',7, 10, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',8, 10, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',9, 10, 'system', 'system');
INSERT INTO group_member (access_level,chatters_id,group_id,created_by,updated_by)
VALUES ('MEMBER',10, 10, 'system', 'system');
