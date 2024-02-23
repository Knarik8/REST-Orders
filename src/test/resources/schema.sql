create table if not exists customers
(
    id         int auto_increment
    primary key,
    email      varchar(255) null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null
    );

create table if not exists items
(
    id    int auto_increment
    primary key,
    name  varchar(255)   null,
    price decimal(38, 2) null
    );

create table if not exists orders
(
    order_id    int auto_increment
    primary key,
    customer_id int                      null,
    order_date  date default (curdate()) null
    );

create table if not exists orders_items
(
    order_id int not null,
    item_id  int not null,
    quantity int null,
    primary key (order_id, item_id),
    constraint orders_items_ibfk_1
    foreign key (order_id) references orders (order_id)
    on update cascade on delete cascade,
    constraint orders_items_ibfk_2
    foreign key (item_id) references items (id)
    on update cascade on delete cascade
    );

INSERT INTO customers (id, first_name, last_name, email)
VALUES
    (1, 'Ivan', 'Ivanov', 'ivan@mail.ru'),
    (2, 'Anna', 'Petrova', 'ann@mail.ru'),
    (3, 'Mariya', 'Sidorova', 'mariya@mail.ru');

INSERT INTO items (id, name, price)
VALUES
    (1, 'chocolate', 7),
    (2, 'milk', 8),
    (3, 'nuts', 3);

INSERT INTO orders (order_id, customer_id, order_date)
VALUES
    (1, 2, '2024-02-18'),
    (2, 1, '2024-02-18'),
    (3, 2, '2024-02-18'),
    (4, 3, '2024-02-18');


INSERT INTO orders_items (order_id, item_id, quantity)
VALUES
    (1, 1, 2),
    (1, 2, 1),
    (1, 3, 3),
    (3, 2, 4),
    (4, 2, 1),
    (2, 3, 1);

