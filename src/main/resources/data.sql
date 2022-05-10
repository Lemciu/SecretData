INSERT INTO user
    (id, username, password)
VALUES
    (1, 'admin', '{noop}asd'),
    (2, 'ola', '{noop}asd'),
    (3, 'jan', '{noop}asd');

INSERT INTO user_role
    (user_id, role)
VALUES
    (1, 'ROLE_ADMIN'),
    (1, 'ROLE_USER'),
    (2, 'ROLE_USER'),
    (3, 'ROLE_USER');