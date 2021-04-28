drop table if exists t_cart;
create table t_cart(
    vipid int primary key,
    buildDate char(19),
    lastDate char(19),
    foreign key(vipid) references t_vip(id) on delete cascade
);

drop table if exists t_cartInfo;
create table t_cartInfo(
    infoid int primary key auto_increment,
    id int,
    bookid int,
    bookName varchar(200),
    price decimal(6,2),
    unique(id,bookid),
    foreign key(id) references t_cart(vipid) on delete cascade,
    foreign key(bookid) references t_book(bookid)
);