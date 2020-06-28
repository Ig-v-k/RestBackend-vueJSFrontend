drop table if exists message cascade;
drop table if exists usr cascade;
drop sequence if exists hibernate_sequence;

create sequence hibernate_sequence start 1 increment 1;

create table message (
    id int8 not null,
    text varchar(255),
    primary key (id)
);

create table usr (
    id varchar(255) not null,
    email varchar(255),
    gender varchar(255),
    last_visit timestamp,
    locale varchar(255),
    name varchar(255),
    userpic varchar(255),
    primary key (id)
);