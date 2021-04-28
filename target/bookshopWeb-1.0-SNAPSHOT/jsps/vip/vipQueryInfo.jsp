<%--
  Created by IntelliJ IDEA.
  User: xuhaoyao
  Date: 2021/4/11
  Time: 23:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>vipInfo</title>
    <link rel="stylesheet" type="text/css" href="css/vipQueryInfoCss.css"/>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="js/vipQueryInfoScript.js"></script>
</head>
<body>
<h1 align="center">我的个人信息</h1>
<table align="center">
    <tr>
        <th>用户名</th>
        <td>
            <input type="text" id="username" value="${vipInfo.username}"/>
            <span id="idMsg"></span>
        </td>
    </tr>
    <tr></tr>
    <tr>
        <th>密码</th>
        <td>
            <input type="text" id="password" value="${vipInfo.password}"/>
            <span id="passwordMsg"></span>
        </td>
    </tr>
    <tr></tr>
    <tr>
        <th>邮箱</th>
        <td>
            <input type="text" id="email" value="${vipInfo.email}"/>
            <span id="emailMsg"></span>
        </td>
    </tr>
    <tr>
        <th>收获地址</th>
        <td>
            <textarea id="vipAddress" placeholder="请填写收获地址" style="resize: none">${vipInfo.address}</textarea>
            <span id="addressMsg"></span>
        </td>
    </tr>
    <tr></tr>
    <tr>
        <th>联系电话</th>
        <td>
            <input type="text" id="vipPhone" value="${vipInfo.phone}" placeholder="请填写联系电话"/>
            <span id="phoneMsg"></span>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="button" id="vipQueryInfo_updateBtn" value="修改"/>
        </td>
    </tr>
</table>
</body>
</html>
