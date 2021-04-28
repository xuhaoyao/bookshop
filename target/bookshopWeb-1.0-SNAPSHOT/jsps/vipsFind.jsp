<%@ page import="java.util.List" %>
<%@ page import="com.scnu.vip.entity.Vip" %><%--
  Created by IntelliJ IDEA.
  User: xuhaoyao
  Date: 2021/4/9
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>vipsFind</title>
    <style type="text/css">
        .vipsFind_btn{
            border: 1px #3083ff solid;
            background-color:#3487ff;
            color: #fff;
        }
    </style>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        function del(e){
            $.get("adminServlet",{
                id : e.id,
                oper : "adminDelVip"
            },function(resp){if(resp == "删除成功") e.parentNode.parentNode.remove(); },"text")
        }
    </script>
</head>
<body>
<%
    List<Vip> vips = (List)request.getAttribute("vips");
%>
<h1 align="center">会员基本信息表</h1>
<table border="1" align="center">
    <tr>
        <th>编号</th><th>用户名</th><th>密码</th><th>邮箱</th>
        <th>收货地址</th><th>联系电话</th>
        <th colspan="2">操作</th>
    </tr>
    <%
        for(Vip vip : vips){
    %>
    <tr>
        <td><%=vip.getId()%></td>
        <td><%=vip.getUsername()%></td>
        <td><%=vip.getPassword()%></td>
        <td><%=vip.getEmail()%></td>
        <td><%=vip.getAddress()%></td>
        <td><%=vip.getPhone()%></td>
        <td>
            <a href="adminServlet?oper=adminFindVipInfo&id=<%=vip.getId()%>">
                <input type="button" value="修改" class="vipsFind_btn"/>
            </a>
        </td>
        <td>
            <input type="button" value="删除" id="<%=vip.getId()%>" class="vipsFind_btn" onclick="del(this)"/>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
