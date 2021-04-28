drop table if exists t_admin;
create table t_admin(
    id int primary key auto_increment,
    username varchar(14),
    password varchar(14),
    unique(username,password)
);