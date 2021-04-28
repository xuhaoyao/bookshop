<%--
  Created by IntelliJ IDEA.
  User: xuhaoyao
  Date: 2021/4/11
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>tab</title>
</head>
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
</html>
