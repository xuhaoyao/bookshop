<%--
  Created by IntelliJ IDEA.
  User: xuhaoyao
  Date: 2021/4/16
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>myBuyBooksInfo</title>
    <style type="text/css">
        .delMyBuyBookRecordBtns{
            border: 1px darkgreen solid;
            background-color:darkgreen;
            color: #fff;
        }
    </style>
</head>
<body>
<br><br><br>
<table align="center" border="1">
    <tr>
        <th>书编号</th><th>书名</th><th>作者</th><th>出版社</th>
        <th>出版日期</th><th>ISBN</th><th>价格</th>
        <th>购书日期</th><th>操作</th>
    </tr>
    <c:forEach items="${pb.beanList}" var="book">
        <tr align="center">
            <td>${book.bookid}</td>     <td>${book.bookName}</td>
            <td>${book.author}</td>     <td>${book.press}</td>
            <td>${book.publishDate}</td>
            <td>${book.isbn}</td>       <td>${book.price}</td>
            <td>${book.buyDate}</td>
            <td>
                <a href="orderServlet?oper=vipDelBookRecord&pc=${pb.pc}&bookid=${book.bookid}&buyDate=${book.buyDate}">
                    <input type="button" class="delMyBuyBookRecordBtns" value="删除记录"/>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
    <h3 align="center">
        <%@ include file="../admin/tab.jsp"%>
    </h3>
</body>
</html>
