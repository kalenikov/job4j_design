create table product
(
    id           serial primary key,
    name         varchar(255) not null,
    type_id      integer references type,
    expired_date timestamptz,
    price        numeric(15, 2) default 0
);

create table type
(
    id   serial primary key,
    name varchar(50) not null
);

-- 1. Написать запрос получение всех продуктов с типом "СЫР"
select p.*
from product p
         join type t on t.id = p.type_id
    and t.name = 'СЫР';

-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select *
from product p
where p.name like '%мороженное%';

-- 3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
-- (от текущей даты вычисляем начало и конец следующего месяца)
select *
from product
where date_part('month', expired_date) = date_part('month', now() + inverval '1 month')

-- 4. Написать запрос, который выводит самый дорогой продукт.
select *
from product p
where price = (select max(price) from product);

-- 5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество
select t.name, count(p.id)
from type t
         join product p on t.id = p.type_id
group by t.name;

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select p.*
from product p
         join type t on t.id = p.type_id
    and t.name in ('СЫР', 'МОЛОКО');

-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select t.name
from product p
         join type t on t.id = p.type_id
group by t.name
having count(t.name) < 10;

-- 8. Вывести все продукты и их тип.
select *
from product
         join type t on t.id = product.type_id;