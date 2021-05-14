create table body
(
    id   serial primary key,
    name varchar(100) not null
);

create table engine
(
    id   serial primary key,
    name varchar(100) not null
);

create table transmission
(
    id   serial primary key,
    name varchar(100) not null
);

create table car
(
    id      serial primary key,
    name    varchar(100)                not null,
    body_id int references body         not null,
    eng_id  int references engine       not null,
    tm_id   int references transmission not null
);


-- 1. Вывести список всех машин и все привязанные к ним детали.
select car.name, b.name, e.name, t.name
from car
         left join body b on car.body_id = b.id
         left join engine e on e.id = car.eng_id
         left join transmission t on t.id = car.tm_id;

-- 2. Вывести отдельно детали (1 деталь - 1 запрос), которые не используются в машине, кузова, двигатели, коробки передач.
select *
from body
         left join car c on body.id = c.body_id
where c.id is null;

select *
from engine e
         left join car c on e.id = c.eng_id
where c.id is null;

select *
from transmission t
         left join car c on t.id = c.tm_id
where c.id is null;


