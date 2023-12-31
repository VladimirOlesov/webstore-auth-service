CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users
(
    user_id           bigserial primary key,
    user_uuid         uuid         not null,
    username          varchar(255) not null,
    password          varchar(255) not null,
    email             varchar(255) not null,
    phone_number      varchar(255),
    registration_date timestamp,
    first_name        varchar(255),
    last_name         varchar(255),
    role              varchar(255) not null
);

CREATE INDEX idx_users_uuid ON users (user_uuid);

CREATE TABLE orders_info
(
    order_info_id bigserial primary key,
    user_uuid     uuid not null
);

CREATE TABLE order_info_books
(
    order_info_id bigint references orders_info (order_info_id),
    book_id       bigint not null,
    title         varchar(255) not null
);