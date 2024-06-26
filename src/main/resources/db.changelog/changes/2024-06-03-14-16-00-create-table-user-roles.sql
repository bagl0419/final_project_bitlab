CREATE TABLE IF NOT EXISTS USER_ROLES
(
    USER_ID BIGINT,
    ROLE_ID BIGINT,
    FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
    FOREIGN KEY (ROLE_ID) REFERENCES ROLES(ID)
);