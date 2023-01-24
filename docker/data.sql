INSERT INTO task (id, content, is_removed, is_completed)
VALUES (nextval('hibernate_sequence'), 'Task 1', FALSE, FALSE);

INSERT INTO task (id, content, is_removed, is_completed)
VALUES (nextval('hibernate_sequence'), 'Task 2', FALSE, TRUE);

INSERT INTO task (id, content, is_removed, is_completed)
VALUES (nextval('hibernate_sequence'), 'Task 3', TRUE, FALSE);

INSERT INTO task (id, content, is_removed, is_completed)
VALUES (nextval('hibernate_sequence'), 'Task 4', TRUE, TRUE);

INSERT INTO task (id, content, is_removed, is_completed)
VALUES (nextval('hibernate_sequence'), 'Task 5', FALSE, FALSE);

INSERT INTO task (id, content, is_removed, is_completed)
VALUES (nextval('hibernate_sequence'), 'Task 6', FALSE, TRUE);