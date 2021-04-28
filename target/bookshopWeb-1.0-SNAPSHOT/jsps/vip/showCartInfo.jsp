<%--
  Created by IntelliJ IDEA.
  User: xuhaoyao
  Date: 2021/4/15
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>cartInfo</title>
    <link rel="stylesheet" type="text/css" href="jsps/vip/css/showCartInfoCss.css"/>
</head>
<body>
<h2 align="center">我的购物车</h2>
<table align="center">
    <tr>
        <th>创建时间</th>
        <td>${cart.buildDate}</td>
    </tr>
    <tr>
        <th>最后更新时间</th>
        <td>${cart.lastDate}</td>
    </tr>
</table>
<table align="center" border="1">
    <tr>
        <th>书编号</th><th>书名</th><th>价格</th><th colspan="2">操作</th>
    </tr>
    <c:forEach items="${cart.bookList}" var="book">
        <tr>
            <th>${book.bookid}</th><th>${book.bookName}</th>
            <th>${book.price}</th>
            <td>
                <a href="orderServlet?oper=showBuyBookInfo&comeFrom=cart&bookid=${book.bookid}">
                    <input type="button" value="购买" class="buyBookBtns"/>
                </a>
            </td>
            <td>
                <a href="cartServlet?oper=deleteCartInfo&bookid=${book.bookid}">
                    <input type="button" value="移除" class="deleteTocartBtns"/>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
