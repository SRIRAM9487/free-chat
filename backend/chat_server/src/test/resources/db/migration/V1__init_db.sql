CREATE TABLE chat_group(
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  created_by VARCHAR(255),
  updated_at TIMESTAMP ,
  updated_by VARCHAR(255),
  deleted_at TIMESTAMP,
  deleted_by VARCHAR(255)
);

CREATE TABLE chatters(
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  name VARCHAR(50),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(255),
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_by VARCHAR(255),
  deleted_at TIMESTAMP,
  deleted_by VARCHAR(255)
);


CREATE TABLE group_member(
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  chatter_id BIGINT NOT NULL,
  group_id BIGINT NOT NULL,
  access_level VARCHAR(50) NOT NULL,
  restricted BOOLEAN DEFAULT FALSE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(255),
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_by VARCHAR(255),
  deleted_at TIMESTAMP,
  deleted_by VARCHAR(255),
  CONSTRAINT fk_chatter FOREIGN KEY (chatter_id) REFERENCES chatters(id),
  CONSTRAINT fk_group FOREIGN KEY (group_id) REFERENCES chat_group(id)
);


CREATE TABLE simple_chat(
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  sender_id BIGINT NOT NULL,
  reciever_id BIGINT NOT NULL,
  read BOOLEAN DEFAULT FALSE,
  message VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(255),
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_by VARCHAR(255),
  deleted_at TIMESTAMP,
  deleted_by VARCHAR(255),
  CONSTRAINT fk_sender FOREIGN KEY (sender_id) REFERENCES chatters(id),
  CONSTRAINT fk_receiver FOREIGN KEY (reciever_id) REFERENCES chatters(id)
);

CREATE TABLE group_chat(
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  group_member_id BIGINT NOT NULL,
  message VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(255),
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_by VARCHAR(255),
  deleted_at TIMESTAMP,
  deleted_by VARCHAR(255),
  CONSTRAINT fk_group_member_id FOREIGN KEY(group_member_id) REFERENCES group_member(id)
)

