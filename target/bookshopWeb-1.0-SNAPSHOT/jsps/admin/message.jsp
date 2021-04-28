<%--
  Created by IntelliJ IDEA.
  User: xuhaoyao
  Date: 2021/4/11
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>message</title>
    <style type="text/css">
        img{
            width:150px;
            height:150px;
        }
    </style>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        function bookUpdateFunction(e){
            var id = e.id.substring(13);
            $.post("bookServlet",{
                oper : "bookUpdate",
                bookid : id,
                price : $("#bookPrice"+id+"").val().trim(),
                bookInfo : $("#bookInfo"+id+"").val().trim()
            },bookUpdate_callback,"text");
            function bookUpdate_callback(resp){
                alert(resp);
            }
        }
        function bookDeleteFunction(e){
            var id = e.id.substring(13);
            $.get("bookServlet",{
                oper : "bookDelete",
                bookid : id
            },bookDelete_callback,"text");
            function bookDelete_callback(resp){
                alert(resp);
                if(resp == "删除成功!"){
                    window.location.href = "bookServlet?oper=findBooks&pc=${pb.pc}";
                }
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
                            <input type="text" id="bookPrice${book.bookid}" value="${book.price}"/>
                        </td>
                    </tr>
                    <tr>
                        <th>ISBN</th><td>${book.isbn}</td>
                    </tr>
                    <tr>
                        <th>简介</th>
                        <td>
                            <textarea id="bookInfo${book.bookid}" rows="3" cols="25" style="resize: none">${book.bookInfo}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="button" id="bookUpdateBtn${book.bookid}" value="修改" onclick="bookUpdateFunction(this)"/>
                        </td>
                        <td>
                            <input type="button" id="bookDeleteBtn${book.bookid}" value="删除" onclick="bookDeleteFunction(this)"/>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </c:forEach>
</table>
    <h2 align="center">
        <%@ include file="tab.jsp"%>
    </h2>
</body>
</html>
