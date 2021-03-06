#                          网上购书小站文档

## 系统需求分析

**基本需求**

​	实现网上购书网站的基本功能，完成会员管理、图书管理、货品上架和订单管理等。

**用户角色**

​	 游客（匿名用户或Internet用户）、会员和系统管理员

**应用场景**

（1）   游客通过该购书平台可以浏览所有的图书信息；

（2）   游客可以注册成为会员。

 （3）会员用户可以管理其个人基本资料；

 （4）会员用户在登录状态下，可以选购图书，管理购书清单，下订单（模拟已经结帐）；

 （5）系统管理员可以进行用户管理（浏览或更新、删除用户资料）

 （6）系统管理员需要录入图书目录信息，并维护这些信息；

 （7）系统管理员可以查看所有当前订单（包括已结和未结帐）

 **数据需求**

 （1）用户数据[用户名，密码，角色]

![image](https://user-images.githubusercontent.com/56396192/116414155-cf215500-a86a-11eb-9823-fb3b44f8df86.png)


 （2）图书数据[书名，出版社，出版日期，ISBN，图书描述，图书封面，单价]

![image](https://user-images.githubusercontent.com/56396192/116414191-d8122680-a86a-11eb-806f-fe01756504e8.png)


由于书的ISBN是唯一的,故这里给ISBN添加unique约束


 （3）购书清单[图书列表(含数量)，创建日期，最后更新日期]

![image](https://user-images.githubusercontent.com/56396192/116414232-e06a6180-a86a-11eb-8bdd-8221797f9fca.png)


此处购物车给vipid,即用户的编号加上了外键,同时定义了级联删除,当此用户被删除时,他的购物车也会一并删除

此表是购物车t_cart的信息表,用于记录购物车里有什么书,id字段加上了外键,同时定义了级联删除,若此vip用户被删除,他的购物车信息表也一并删除出去。

![image](https://user-images.githubusercontent.com/56396192/116414262-e52f1580-a86a-11eb-8a2e-16abcfe1ffce.png)


 （4）订单[图书列表(含数量)，总价，下单日期，结帐日期]
![image](https://user-images.githubusercontent.com/56396192/116414489-17d90e00-a86b-11eb-95c1-59e593f2a7cf.png)

此处的vipid外键作用同上，都是为了在删除用户的同时一并删除用户的数据,这里是删掉此订单的数据

特别说明一下vipOper的作用,在会员界面可以删除订单的信息,此订单信息不展示给会员看，但管理员界面中，订单信息可不能缺失，因此vipOper这里定义为,若vipOper=del，则该行记录不展示给会员,否则为默认值NULL


## 项目设计的技术描述

利用所学的JSP Web技术，开发一个简单的小型实验性网站。实验性网站是对真实网站系统的模拟与简化，只注重基本功能的实现，不考虑真实网站中的性能等要求。

具体设计的技术包含以下:

应用了本学期所学的JSP + Servlet + Javabean + JDBC + MySQL

以及额外的ajax + jQuery + JSTL + HTML + CSS + JavaScript

采用MVC的设计模式来开发

## 项目运行环境

IntelliJ IDEA 2020.3.2 x64

MySQL 5.5

Tomcat 9.0.44

## 项目的配置

在项目导入IDEA之后，在文件webapp/WEB-INF-lib下找到![image](https://user-images.githubusercontent.com/56396192/116414669-3a6b2700-a86b-11eb-933c-2e7a59fe1224.png)


右键lib点击Add as Library..
![image](https://user-images.githubusercontent.com/56396192/116414700-43f48f00-a86b-11eb-9afd-252fce3f0c7e.png)

改名为lib点击OK。然后打开项目结构(右上角File->Project Structure)![image](https://user-images.githubusercontent.com/56396192/116414734-4d7df700-a86b-11eb-9be0-c721fa0fdace.png)

为lib打勾,点击应用然后点击OK。

这样一来项目所需要的资源已经齐全。

**重要一点:发布项目之后,要将项目改名为myWeb（游客模块 拦截游客行为的时候有用上）**

## 项目基本代码模块介绍

![image](https://user-images.githubusercontent.com/56396192/116414873-743c2d80-a86b-11eb-860f-dbeeffa76036.png)

如上图，举个例子,如com.scnu.vip包下,有四个子包controller,dao,entity,service

**controller** : 控制层对象(servlet对象)，可以读取请求包参数信息,调用Service对象处理业务,可将处理结果写入到响应体中

**service**:       业务模型对象(Service对象)，提供业务的具体解决方案,通过return将处理结果返回给控制层对象

**dao**:             dao对象提供对数据库某张表文件的操作细节,降低对表文件操作难度，避免反复开发表文件操作的代码，提高代码复用性

**entity**:         实体类,例如vip包下的实体类就是会员对象,可将数据库获取的数据存于entity中。



## 会员登陆



这是网站的登录页面

![image](https://user-images.githubusercontent.com/56396192/116414898-7b633b80-a86b-11eb-9f46-f0f97473e606.png)



通过ajax异步请求进行登录验证（根据radio的值判断是会员登陆还是管理员登陆）

![image](https://user-images.githubusercontent.com/56396192/116414930-81f1b300-a86b-11eb-96a2-5e35aa024a13.png)


控制层对象从前端传来的参数oper获得此次应该进行何种操作。

![image](https://user-images.githubusercontent.com/56396192/116414959-8918c100-a86b-11eb-80ad-10d02b6dfcb4.png)



servlet对象读取请求包参数信息,调用Service对象处理业务,将处理结果写入到响应体中

![image](https://user-images.githubusercontent.com/56396192/116414987-92099280-a86b-11eb-8ac6-0cffee465f5b.png)

此行代码

```java
int id = vipService.vipLoginService(username,password);
```

就是控制层对象调用Service对象处理业务，Service对象的vipLoginService方法如下

![image](https://user-images.githubusercontent.com/56396192/116415016-9930a080-a86b-11eb-8da6-ed327fcc64c0.png)

它调用dao对象,从数据库中查询是否存在此用户，若存在的话，则返回该用户的编号

![image](https://user-images.githubusercontent.com/56396192/116415049-a057ae80-a86b-11eb-87a6-b1c1effaf667.png)
此处的JdbcUtil对象，是我对JDBC技术进行了封装,包括注册驱动,获取连接，以及关闭资源

若登录成功,则进入网站的主页面，可通过左边的栏目在网站进行操作。

选购图书的页面用到了分页技术

![image](https://user-images.githubusercontent.com/56396192/116415086-a9488000-a86b-11eb-88bd-36346b3b58e2.png)

此项目的基本逻辑就如上述用户登录模块。前端携带请求数据包到后端,servlet对象通过Service对象处理请求，将处理结果写入响应对象中返回,由jsp页面展示给用户。



## 分页技术

鉴于图书数据，订单数据等体积庞大，全部展示在一个页面的话，会使得页面不简洁，因此采用分页技术

Java Web 分页实现设计基于 MySQL 的关键字 limit m,n 从指定下标位置 m 开始返回 n 条记录，实现查询某一页的列表数据；前端控制分页"组件"，此处组件是手写的，基本效果是支持跳转首页、尾页、以及展示出的页码。

```java
//下面这行代码就是实现分页技术的关键SQL语句,
String sql1 = "select * from t_book limit ?,?";
```

核心代码如下所示：

定义一个泛型对象PageBean用来存放分页的数据

![image](https://user-images.githubusercontent.com/56396192/116415127-b2395180-a86b-11eb-9825-d9f98799a008.png)

```java
    private int getPc(HttpServletRequest req){ //从请求包中获得当前的页码是多少
        int pc = 1;
        String parm = req.getParameter("pc");
        if(parm != null && !parm.trim().isEmpty())
            pc = Integer.valueOf(parm);
        return pc;
    }

    private String getURL(HttpServletRequest req){ //返回页码的url
        String url = req.getRequestURL() + "?" + req.getQueryString();
        int index = url.lastIndexOf("&pc=");
        if(index != -1)
            url = url.substring(0,index);
        return url;
    }

    private void findBooksServlet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        int pc = getPc(req);
        String url = getURL(req);
        PageBean<Book> pageBean = bookService.findByPage(pc);
        pageBean.setUrl(url);
        req.setAttribute("pb",pageBean);
        //选择将处理结果展示给 管理员/会员
        if("网上购书小站管理系统".equals(req.getSession(false).getAttribute("top_Title")))
            req.getRequestDispatcher("/jsps/admin/message.jsp").forward(req,resp);
        else
            req.getRequestDispatcher("/jsps/vip/message.jsp").forward(req,resp);
    }
```



在com.scnu.book.dao下的BookDao对象中,操作表t_book进行图书的分页查询

```java
    public PageBean<Book> findByPage(int pc){
        int _ps = 2;  //定义一个页面展示多少个数据
        int all = 0;  //页面总页数
        String sql = "select count(*) from t_book";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next())
                all = rs.getInt("count(*)");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn,ps,rs);
        }
        PageBean<Book> pageBean = new PageBean<Book>();
        List<Book> list = new ArrayList<Book>();
        String sql1 = "select * from t_book limit ?,?"; //分页查询的sql语句
        pageBean.setAll(all);
        pageBean.setPc(pc);
        pageBean.setPs(_ps);
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql1);
            ps.setInt(1,(pc - 1) * _ps);
            ps.setInt(2,_ps);
            rs = ps.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setBookid(rs.getInt("bookid"));
                book.setBookName(rs.getString("bookName"));
                book.setAuthor(rs.getString("author"));
                book.setPress(rs.getString("press"));
                book.setPublishDate(rs.getString("publishDate"));
                book.setIsbn(rs.getString("isbn"));
                book.setBookInfo(rs.getString("bookInfo"));
                book.setBookImg(rs.getString("bookImg"));
                book.setPrice(rs.getBigDecimal("price"));
                list.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(conn,ps,rs);
            pageBean.setBeanList(list);
            return pageBean;
        }
    }
```



jsp页面展示给用户(这里运用了JSTL技术) 

message.jsp

```jsp
<table align="center">
    <c:forEach items="${pb.beanList}" var="book">
        <tr>
            <td><img src="${book.bookImg}"/></td>
            <td>
                <table>
                    <tr>
                        <th>书名</th><td>${book.bookName}</td>
                    </tr>
                    <tr>
                        <th>作者</th><td>${book.author}</td>
                    </tr>
                    <tr>
                        <th>出版社</th> <td>${book.press}</td>
                    </tr>
                    <tr>
                        <th>出版年</th><td>${book.publishDate}</td>
                    </tr>
                    <tr>
                        <th>定价</th>
                        <td>
                            ${book.price}
                        </td>
                    </tr>
                    <tr>
                        <th>ISBN</th><td>${book.isbn}</td>
                    </tr>
                    <tr>
                        <th>简介</th>
                        <td>
                            <textarea readonly rows="3" cols="40" style="resize: 		     							none">${book.bookInfo}</textarea> 
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="button" class="addToCartBtns" 			 		    								id="vipAddToCart_btn${book.bookid}"  
                                   value="加入购物车" onclick="addToCartFunction(this)"/>
                            <a href="orderServlet?	                                                      				oper=showBuyBookInfo&comeFrom=shop&bookid=${book.bookid}">
                                <input type="button" class="buyBookBtns" value="立即购买"/>
                            </a>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </c:forEach>
</table>
<h2 align="center">
    <%@ include file="../admin/tab.jsp"%>
</h2>
```

tab.jsp(JSTL技术)

```jsp
<body>
    <c:choose>
        <c:when test="${pb.pc eq null or pb.pc eq 1}">上一页</c:when>
        <c:otherwise><a href="${pb.url}&pc=${pb.pc - 1}">上一页</a></c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${pb.tp < 6}">
            <c:set value="1" var="begin"></c:set>
            <c:set value="${pb.tp}" var="end"></c:set>
        </c:when>
        <c:otherwise>
            <c:set value="${pb.pc - 2}" var="begin"></c:set>
            <c:set value="${pb.pc + 3}" var="end"></c:set>

            <c:if test="${pb.pc + 3 > pb.tp}">
                <c:set value="${pb.tp}" var="end"></c:set>
            </c:if>
        </c:otherwise>
    </c:choose>

    <c:forEach begin="${begin}" end="${end}" var="page">
        <c:choose>
            <c:when test="${pb.pc eq page}">${page}</c:when>
            <c:otherwise>
                <a href="${pb.url}&pc=${page}">${page}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:choose>
        <c:when test="${pb.pc eq null or pb.pc eq pb.tp}">下一页</c:when>
        <c:otherwise>
            <a href="${pb.url}&pc=${pb.pc + 1}">下一页</a>
        </c:otherwise>
    </c:choose>
</body>
```

 

## 购物车
![image](https://user-images.githubusercontent.com/56396192/116415171-bcf3e680-a86b-11eb-893f-4c68136ec8be.png)

如上图,当会员点击加入购物车之后,通过ajax发送异步请求,Date是用户将该本书添加进购物车的时间

```javascript
//下面出现的此行代码是用于拦截游客的。
window.location = "filterMessage.jsp"
```
![image](https://user-images.githubusercontent.com/56396192/116415195-c4b38b00-a86b-11eb-9951-a8dd2e0e7057.png)

从前端传来的数据由cartServlet接受，执行下面的代码段

![image](https://user-images.githubusercontent.com/56396192/116415213-cb420280-a86b-11eb-885c-ae1a5d640c75.png)

cartService.addToCart方法用来模拟此次的业务(会员用户将书添加进购物车)

flag = 1 加入购物车成功,用重定向的方式将本次请求转交给orderServlet(登记订单业务)

flag = 0 说明该物品已经在购物车

否则系统出错(调试阶段可能出现的bug，会员用户不应该看到此条消息)

![image](https://user-images.githubusercontent.com/56396192/116415248-d1d07a00-a86b-11eb-8023-8bf8d0a39a7d.png)



若flag = 1,那么执行那么的代码

![image](https://user-images.githubusercontent.com/56396192/116415276-d85ef180-a86b-11eb-9f8f-7dda2b9659ea.png)

用于将本次加入购物车形成的订单(未结账)加入数据库,然后将操作结果告诉会员用户.



## 订单

![image](https://user-images.githubusercontent.com/56396192/116415312-deed6900-a86b-11eb-9d99-97c595e156f3.png)

如上图，当会员用户点击立即购买时,页面跳转如下:

![image](https://user-images.githubusercontent.com/56396192/116415486-0c3a1700-a86c-11eb-8e28-71a0a73cc1e2.png)

此处可以修改收货地址和联系电话,修改后的记录会由数据库自行保存,下次再次购买的时候，显示最后更新的地址和电话

点击**提交订单**之后,先看jsp页面的代码


![image](https://user-images.githubusercontent.com/56396192/116415525-16f4ac00-a86c-11eb-8a73-d7631cc8a67c.png)

首先要判断收获地址是否为空,以及联系电话是否合法（正则表达式判断）

![image](https://user-images.githubusercontent.com/56396192/116415561-1f4ce700-a86c-11eb-875b-f34338fc1450.png)

![image](https://user-images.githubusercontent.com/56396192/116415582-24aa3180-a86c-11eb-9f9c-296d646d5c8b.png)

若联系电话为空而提交订单，会出现如下界面

![image](https://user-images.githubusercontent.com/56396192/116415650-31c72080-a86c-11eb-9523-0b065cf70377.png)



若地址和电话无误之后，提交订单，前端数据传到后端，由orderServlet处理




## 管理员查看订单

管理员页面的左端有这些选项

![image](https://user-images.githubusercontent.com/56396192/116415858-663adc80-a86c-11eb-848e-e85678059477.png)


点击查看订单，后端执行代码

![image](https://user-images.githubusercontent.com/56396192/116415883-69ce6380-a86c-11eb-9f48-80515d2e2c36.png)


orderService.findByPage方法将第一页的订单数据返回,将pageBean传给HttpServletRequest后，通过重定向传给jsp页面，呈现如下结果


![image](https://user-images.githubusercontent.com/56396192/116415830-5d4a0b00-a86c-11eb-9cb5-70d145ed09f3.png)

如上图,若结账日期为空，说明该订单在购物车中，还未被会员购买，否则已经被会员购买

若创建日期和结账日期一致，说明该订单不是来自购物车，而是从商店直接购买的





**至此，本项目的所有关键技术已经说明完毕，由于项目模块很多，这里只说明了最关键的一些，剩余的部分请查看代码。**




