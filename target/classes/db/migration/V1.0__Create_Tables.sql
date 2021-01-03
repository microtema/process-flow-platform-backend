CREATE TABLE PROCESS_DEFINITION
(
    UUID                     CHAR(36)      NOT NULL,
    DEFINITION_KEY           VARCHAR(256)  NOT NULL,
    DEFINITION_NAME          VARCHAR(256)  NOT NULL,
    DEFINITION_DISPLAY_NAME  VARCHAR(256)  NOT NULL,
    DEFINITION_DESCRIPTION   VARCHAR(2000) NOT NULL,
    DEFINITION_VERSION       INTEGER       NOT NULL,
    DEFINITION_MAJOR_VERSION INTEGER       NOT NULL,
    DEFINITION_DEPLOY_TIME   TIMESTAMP     NOT NULL,
    DEFINITION_DIAGRAM       TEXT          NOT NULL
);

ALTER TABLE PROCESS_DEFINITION
    ADD CONSTRAINT PROCESS_DEFINITION_PK PRIMARY KEY ("uuid");
ALTER TABLE PROCESS_DEFINITION
    ADD CONSTRAINT PROCESS_DEFINITION_IDX UNIQUE ("definition_key", "definition_version", "definition_major_version");
