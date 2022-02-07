/*
create table city
(
    id serial PRIMARY KEY,
    name varchar(50) unique NOT NULL
);

 */
insert into city (name) values ('TOMSK');
/*
create table PERSONS
(
    name varchar(30) NOT NULL,
    surname varchar(60) NOT NULL,
    age int NOT NULL,
    phone_number varchar(20),
    city_of_living int NOT NULL,
    PRIMARY KEY (name,surname,age),
    FOREIGN KEY (city_of_living) REFERENCES city(id)
);

 */