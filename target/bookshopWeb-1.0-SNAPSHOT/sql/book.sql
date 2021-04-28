drop table if exists t_book;
create table t_book(
    bookid int primary key auto_increment,
    bookName varchar(200),
    author varchar(100),
    press varchar(50),
    publishDate char(10),
    isbn varchar(30) unique,
    bookInfo varchar(500),
    bookImg varchar(100),
    price decimal(6,2)
);

insert into t_book(bookName,author,press,publishDate,isbn,bookInfo,bookImg ,price)values('MySQL技术内幕',' 姜承尧','机械工业出版社','2013-05-11','9787111422068','《MySQL技术内幕:InnoDB存储引擎(第2版)》从源代码的角度深度解析了InnoDB的体系结构、实现原理、工作机制，并给出了大量最佳实践，能帮助你系统而深入地掌握InnoDB，更重要的是，它能为你设计管理高性能、高可用的数据库系统提供绝佳的指导。','bookImages/s29731159.jpg',79.00);
insert into t_book(bookName,author,press,publishDate,isbn,bookInfo,bookImg ,price)values('数据库系统概念','（美）Abraham Silberschatz / （美）Henry F.Korth / （美）S.Sudarshan','机械工业出版社','2012-03-11','9787111375296','本书是数据库系统方面的经典教材之一，其内容由浅入深，既包含数据库系统基本概念，又反映数据库技术新进展。它被国际上许多著名大学所采用，包括斯坦福大学、耶鲁大学、得克萨斯大学、康奈尔大学、伊利诺伊大学等。我国也有多所大学采用本书作为本科生和研究生数据库课程的教材和主要教学参考书，收到了良好的效果。','bookImages/s8958042.jpg',99.00);
insert into t_book(bookName,author,press,publishDate,isbn,bookInfo,bookImg ,price)values('简爱','[英] 夏洛蒂·勃朗特','上海文艺出版社','2007-08-01','9787532131990','《简爱》讲述了这样一个故事：简?爱自幼父母双亡，投靠冷酷的舅母，但舅母无情地抛弃了她。她在一所慈善学校做了六年的学生和两年的教师。十八岁时，简?爱受聘到桑菲尔德府学当家庭教师，认识了主人罗切斯特。两人都被对方独特的气质和丰富的感情所吸引，于是不顾身份和地位的巨大差距深深相爱了。正当他们举行婚礼时，有人证明罗切斯特的前妻还活着。简?爱知道他们不可能有平等的婚姻，于是选择了离开。后来，简?爱意外遇见了她的表兄妹们，并从叔叔那里继承了一笔遗产。但她无法抵御对罗切斯特的刻骨思念，于是便回到了已经失去了财富、身体也遭到火灾严重摧残的罗切斯特身边，毅然跟他结婚。在爱的沐浴下，罗切斯特找回了幸福和健康。','bookImages/s33473798.jpg',27.00);
