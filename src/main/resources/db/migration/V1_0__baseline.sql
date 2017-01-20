CREATE SEQUENCE todo_id_seq START WITH 1;

CREATE TABLE todos
(
  id      BIGINT       NOT NULL DEFAULT nextval('todo_id_seq') PRIMARY KEY,
  title   VARCHAR(255) NOT NULL,
  due     TIMESTAMP,
  done    BOOLEAN      NOT NULL DEFAULT FALSE,
  created TIMESTAMP    NOT NULL,
  updated TIMESTAMP    NOT NULL
);