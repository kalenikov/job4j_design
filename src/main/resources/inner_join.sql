create table users
(
    id   serial primary key,
    name varchar(100)
);

create table posts
(
    id            serial primary key,
    text          text,
    creation_date timestamp,
    user_id       integer references users
);


-- select user users who created posts
select distinct u.name
from users as u
         inner join posts as p
                    on u.id = p.user_id
order by name;

-- select authors last 3 posts
select u.name
from posts as p
         inner join users u on u.id = p.user_id
order by creation_date desc
limit 3;

-- select posts that are named by the author`s name
select name
from posts as p
         inner join users u on u.name = p.name
