insert into
    users (user_name, password, user_role)
values
    ('admin', '{noop}adminpass', 'ADMIN'),   -- 1
    ('user', '{noop}userpass', 'USER'),     -- 2
    ('viewer', '{noop}viewerpass', 'VIEWER'); -- 3
