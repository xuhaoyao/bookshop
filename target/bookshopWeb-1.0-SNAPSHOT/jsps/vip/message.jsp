<%--
  Created by IntelliJ IDEA.
  User: xuhaoyao
  Date: 2021/4/12
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>message</title>
    <link rel="stylesheet" type="text/css" href="jsps/vip/css/bookInfoCss.css"/>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        function addToCartFunction(e){
            var id = e.id.substring(16);
            var time = (new Date()).getTime();
            $.post("cartServlet",{
                oper : "addToCart",
                bookid : id,
                Date : time
            },addToCart_callback,"text");
            function addToCart_callback(resp){
                if(resp == "加入购物车成功!" || resp == "该物品已在购物车中!") {
                    alert(resp);
                }
                else
                    window.location = "filterMessage.jsp"
            }
        }
    </script>
</head>
<body>
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
                            <textarea readonly rows="3" cols="40" style="resize: none">${book.bookInfo}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="button" class="addToCartBtns" id="vipAddToCart_btn${book.bookid}" value="加入购物车" onclick="addToCartFunction(this)"/>
                            <a href="orderServlet?oper=showBuyBookInfo&comeFrom=shop&bookid=${book.bookid}">
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
</body>
</html>
