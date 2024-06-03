-- liquibase formatted sql

-- changeset b.imanbekov:1
CREATE TABLE IF NOT EXISTS ROLES
(
    ID BIGSERIAL PRIMARY KEY NOT NULL,
    NAME VARCHAR(255) NOT NULL
);