ALTER TABLE organizations
    ADD is_active BIT(1) NULL;

ALTER TABLE organizations
    ADD last_activity_at datetime NULL;

ALTER TABLE organizations
    MODIFY is_active BIT(1) NOT NULL;

ALTER TABLE organizations
    MODIFY last_activity_at datetime NOT NULL;