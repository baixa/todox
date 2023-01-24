CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE task
(
    id           BIGINT       NOT NULL,
    content      VARCHAR(255) NOT NULL,
    is_removed   BOOLEAN      NOT NULL,
    is_completed BOOLEAN      NOT NULL,
    CONSTRAINT pk_task PRIMARY KEY (id)
);