insert into
    users (user_name, password)
values
    ('admin', '{noop}adminpass'),   -- 1
    ('user', '{noop}userpass'),     -- 2
    ('viewer', '{noop}viewerpass'); -- 3

insert into
    user_role (name, description)
values
    ('ADMIN', 'full permisions'),   -- 1
    ('USER', 'can add products and documents'),   -- 2
    ('VIEWER', 'can only view the stock levels');   -- 3

insert into
    user_roles (user_id, role_id)
values
    (1, 1),
    (2, 2),
    (3, 3);