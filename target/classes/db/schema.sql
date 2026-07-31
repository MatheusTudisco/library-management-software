create table genres (
id BIGSERIAL primary key,
name VARCHAR(50) not null unique
);

create table books (
id BIGSERIAL primary key,
title VARCHAR(255) not null,
author VARCHAR(100) not null,
genre_id BIGINT not null,
year smallint not null,
volume smallint not null,
quantity smallint not null,

constraint fk_books_genres
foreign key (genre_id)
references genres(id)
on delete restrict
);

create table users(
id BIGSERIAL primary key,
name VARCHAR(100) not null,
last_name VARCHAR (100) not null,
cpf VARCHAR(14) not null unique,
date_of_birth DATE not null,
cellphone VARCHAR(20) not null,
email VARCHAR(150) not null unique
);

