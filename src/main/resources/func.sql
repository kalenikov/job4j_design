create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

-- select average device price
select avg(price)
from devices;

-- select average device price by people
select p.name,
       avg(d.price)
from devices_people as dev
         inner join people p on p.id = dev.people_id
         inner join devices d on d.id = dev.device_id
group by p.name;

-- select average device price (over 4000) by people over
select p.name,
       avg(d.price)
from devices_people as dev
         inner join people p on p.id = dev.people_id
         inner join devices d on d.id = dev.device_id
    and d.price > 5000
group by p.name;
