drop table if exists t_vip;
create table t_vip(
    id       int primary key auto_increment,
    username varchar(14) unique,
    password varchar(14),
    email    varchar(30) unique,
    address varchar(100),
    phone varchar(15)
);