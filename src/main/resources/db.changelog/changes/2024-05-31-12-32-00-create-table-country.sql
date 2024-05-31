-- liquibase formatted sql

-- changeset b.imanbekov:1
CREATE TABLE COUNTRIES
(
    ID   BIGSERIAL PRIMARY KEY NOT NULL,
    NAME VARCHAR(255) NOT NULL,
    CODE VARCHAR(3)   NOT NULL
);