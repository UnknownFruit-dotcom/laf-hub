ALTER TABLE users
    ADD is_active BIT(1) NULL;

ALTER TABLE users
    MODIFY is_active BIT(1) NOT NULL;