create database test;

use test;

create table books (
                       id INT auto_increment,
                       name varchar(255) not null unique,
                       primary key(id)

);

insert into books (name) values ('FreeBSD'), ('Debian'), ('UNIX'), ('xv6');