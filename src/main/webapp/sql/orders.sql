drop table if exists t_orders;
create table t_orders(
    ordersid int primary key auto_increment,
    vipid int,
    bookid int,
    bookName varchar(200),
    price decimal(6,2),
    ischeckout char(3),
    startDate char(19),
    endDate char(19),
    vipOper char(3),
    index(vipid),
    foreign key(vipid) references t_vip(id) on delete cascade
);