-- Роли
create table roles
(
    id   serial primary key,
    name varchar(255) not null
);

-- Права
create table rules
(
    id   serial primary key,
    name varchar(255) not null
);

-- Права ролей
create table roles_rules
(
    id      serial primary key,
    role_id integer references roles,
    rule_id integer references rules
);

-- Состояния заявок
create table states
(
    id   serial primary key,
    name varchar(255) not null
);

-- Категории заявок
create table category
(
    id   serial primary key,
    name varchar(255) not null
);

-- Пользователи
create table users
(
    id      serial primary key,
    name    varchar(255) not null,
    role_id integer references roles
);

-- Заявки
create table items
(
    id          serial primary key,
    name        varchar(255) not null,
    user_id     integer references users,
    category_id integer references category,
    state_id    integer references states
);

-- Комментарии Заявок
create table comments
(
    id      serial primary key,
    name    varchar(255),
    item_id integer references items
);

-- Приложенные Файлы
create table attachments
(
    id      serial primary key,
    name    varchar(255),
    item_id integer references items
);