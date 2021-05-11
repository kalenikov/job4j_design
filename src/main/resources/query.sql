create table users
(
    id      serial primary key,
    name    varchar(255),
    comment text
);
--
insert into users (name, comment)
values ('user1', 'test1');
--
select *
from users;
--
update users
set name = 'user2';
--
delete
from users;