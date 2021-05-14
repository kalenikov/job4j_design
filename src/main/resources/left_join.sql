create table departments
(
    id   serial primary key,
    name varchar(50) not null
);

create table employees
(
    id     serial primary key,
    name   varchar(50) not null,
    dep_id int references departments
);

-- 2. Выполнить запросы с left, right, full, cross соединениями
select *
from departments d
         left join employees e on d.id = e.dep_id;

select *
from departments d
         right join employees e on d.id = e.dep_id;

select *
from departments d
         full join employees e on d.id = e.dep_id;

select *
from departments
         cross join employees;

-- 3. Используя left join найти департаменты, у которых нет работников

select *
from departments d
         left join employees e on d.id = e.dep_id
where e.id is null;

-- 4. Используя left и right join написать запросы, которые давали бы одинаковый результат.
select *
from departments d
         left join employees e on d.id = e.dep_id;

select *
from employees e
         right join departments d on e.dep_id = d.id;

-- 5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары
create table teens
(
    id     serial primary key,
    name   varchar(20),
    gender char(1)
);

select t1.name, t2.name
from teens t1
         cross join teens t2
where t1.gender <> t2.gender;