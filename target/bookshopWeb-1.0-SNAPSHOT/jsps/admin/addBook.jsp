<%--
  Created by IntelliJ IDEA.
  User: xuhaoyao
  Date: 2021/4/10
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addBook</title>
    <style type="text/css">
        #addBookBtn{
            width: 300px;
            height: 40px;
            border: 1px #3083ff solid;
            background-color:#3487ff;
            color: #fff;
            margin-top: 10px;
        }
    </style>
    <script type="text/javascript" src="../../js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(function() {
            $("#addBookBtn").click(function () {
                $.post("../../bookServlet",{
                    oper : "addBook",
                    bookName : $("#bookName").val().trim(),
                    author : $("#author").val().trim(),
                    press : $("#press").val().trim(),
                    publishDate : $("#publishDate").val(),
                    isbn : $("#isbn").val().trim(),
                    bookInfo : $("#bookInfo").val().trim(),
                    bookImg : $("#bookImg").val(),
                    price : $("#price").val().trim()
                },addBook_callback,"text");
            })
            function addBook_callback(resp){
                alert(resp);
            }

        })
    </script>
</head>
<body>
<h1 align="center">图书录入表</h1>
<table align="center">
    <tr>
        <th>书名:</th>
        <td><input type="text" id="bookName"/></td>
    </tr>
    <tr>
        <th>作者:</th>
        <td><input type="text" id="author"/></td>
    </tr>
    <tr>
        <th>出版社:</th>
        <td><input type="text" id="press"/></td>
    </tr>
    <tr>
        <th>出版日期:</th>
        <td><input type="date" id="publishDate"/></td>
    </tr>
    <tr>
        <th>ISBN:</th>
        <td><input type="text" id="isbn"/></td>
    </tr>
    <tr>
        <th>图书描述:</th>
        <td>
            <textarea id="bookInfo" rows="3" cols="25" style="resize: none"></textarea>
        </td>
    </tr>
    <tr>
        <th>图书封面:</th>
        <td>
            <input type="file" id="bookImg"/>
        </td>
    </tr>
    <tr>
        <th>单价:</th>
        <td>
            <input type="text" id="price"/>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="button" value="录入" id="addBookBtn"/>
        </td>
    </tr>
</table>
</body>
</html>
