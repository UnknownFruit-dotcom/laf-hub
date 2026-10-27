CREATE TABLE items
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    title            VARCHAR(255)          NOT NULL,
    description      TEXT                  NOT NULL,
    found_at         datetime              NOT NULL,
    created_at       datetime              NOT NULL,
    found_by_user_id BIGINT                NOT NULL,
    status           VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_items PRIMARY KEY (id)
);

CREATE TABLE users
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    login         VARCHAR(255)          NOT NULL,
    password_hash VARCHAR(255)          NOT NULL,
    created_at    datetime              NOT NULL,
    role          VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_login UNIQUE (login);

ALTER TABLE items
    ADD CONSTRAINT FK_ITEMS_ON_FOUND_BY_USER FOREIGN KEY (found_by_user_id) REFERENCES users (id);