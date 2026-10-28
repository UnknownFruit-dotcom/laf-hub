CREATE TABLE organizations
(
    id                     BIGINT AUTO_INCREMENT NOT NULL,
    title                  VARCHAR(255)          NOT NULL,
    `description`          VARCHAR(255)          NULL,
    verification_code_hash VARCHAR(255)          NOT NULL,
    created_by_user_id     BIGINT                NOT NULL,
    created_at             datetime              NOT NULL,
    CONSTRAINT pk_organizations PRIMARY KEY (id)
);

CREATE TABLE user_organizations
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    user_id         BIGINT                NOT NULL,
    organization_id BIGINT                NOT NULL,
    joined_at       datetime              NOT NULL,
    CONSTRAINT pk_user_organizations PRIMARY KEY (id)
);

ALTER TABLE organizations
    ADD CONSTRAINT uc_organizations_verificationcodehash UNIQUE (verification_code_hash);

ALTER TABLE user_organizations
    ADD CONSTRAINT uk_user_organization UNIQUE (user_id, organization_id);

ALTER TABLE organizations
    ADD CONSTRAINT FK_ORGANIZATIONS_ON_CREATED_BY_USER FOREIGN KEY (created_by_user_id) REFERENCES users (id);

ALTER TABLE user_organizations
    ADD CONSTRAINT FK_USER_ORGANIZATIONS_ON_ORGANIZATION FOREIGN KEY (organization_id) REFERENCES organizations (id);

ALTER TABLE user_organizations
    ADD CONSTRAINT FK_USER_ORGANIZATIONS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE items
    ADD COLUMN organization_id BIGINT NOT NULL;

ALTER TABLE items
    ADD CONSTRAINT FK_ITEMS_ON_ORGANIZATION FOREIGN KEY (organization_id) REFERENCES organizations (id);