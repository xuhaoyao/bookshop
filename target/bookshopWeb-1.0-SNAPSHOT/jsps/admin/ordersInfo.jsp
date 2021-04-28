<%--
  Created by IntelliJ IDEA.
  User: xuhaoyao
  Date: 2021/4/16
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>ordersInfo</title>
</head>
<body>
<br><br><br>
<table align="center" border="1">
    <tr>
        <th>订单编号</th><th>会员编号</th><th>书编号</th><th>书名</th>
        <th>价格</th><th>订单状态</th><th>创建日期</th><th>结账日期</th>
    </tr>
    <c:forEach items="${pb.beanList}" var="order">
        <tr align="center">
            <td>${order.ordersid}</td>  <td>${order.vipid}</td>
            <td>${order.bookid}</td>    <td>${order.bookName}</td>
            <td>${order.price}</td>     <td>${order.ischeckout}</td>
            <td>${order.startDate}</td> <td>${order.endDate}</td>
        </tr>
    </c:forEach>
</table>
<h3 align="center">
    <%@ include file="tab.jsp"%>
</h3>
</body>
</html>
