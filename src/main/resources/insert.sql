delete
from category;
delete
from states;
delete
from users;
delete
from roles;
delete
from roles_rules;

insert into category (name)
values ('ASAP'),
       ('not urgent');

insert into states (name)
values ('done'),
       ('in progress'),
       ('new');

insert into roles (id, name)
values (100, 'user'),
       (500, 'admin');

insert into rules (id, name)
values (744, 'read all'),
       (777, 'delete all');

insert into roles_rules (role_id, rule_id)
values (100, 777),
       (100, 744);

insert into users(name, role_id)
values ('root', 100)




