<%@ page import="com.scnu.vip.entity.Vip" %><%--
  Created by IntelliJ IDEA.
  User: xuhaoyao
  Date: 2021/4/9
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        .vipInfo_btn{
            width: 300px;
            height: 40px;
            border: 1px #3083ff solid;
            background-color:#3487ff;
            color: #fff;
            margin-top: 10px;
        }
    </style>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#vipInfo_btn").click(function(){
                $.post("adminServlet",{
                    id : $("#id").val(),
                    username : $("#username").val().trim(),
                    password : $("#password").val().trim(),
                    email : $("#email").val().trim(),
                    address : $("#address").val().trim(),
                    phone : $("#phone").val().trim(),
                    oper : "adminUpdateVipInfo"
                },vipInfo_callback,"text")
            })
            function vipInfo_callback(resp){
                alert(resp);
            }
        })
    </script>
</head>
<body>
<h1 align="center">会员编号为${vip.id}的基本信息</h1>
<table align="center">
    <tr>
        <th>编号</th>
        <td>
            <input type="text" id="id" value="${vip.id}" readonly/>
        </td>
    </tr>
    <tr>
        <th>用户名</th>
        <td>
            <input type="text" id="username" value="${vip.username}"/>
        </td>
    </tr>
    <tr>
        <th>密码</th>
        <td>
            <input type="text" id="password" value="${vip.password}"/>
        </td>
    </tr>
    <tr>
        <th>邮箱</th>
        <td>
            <input type="text" id="email" value="${vip.email}"/>
        </td>
    </tr>
    <tr>
        <th>收货地址</th>
        <td>
            <input type="text" id="address" value="${vip.address}"/>
        </td>
    </tr>
    <tr>
        <th>联系电话</th>
        <td>
            <input type="text" id="phone" value="${vip.phone}"/>
        </td>
    </tr>
    <tr>
        <th colspan="2">
            <input type="button" value="修改" id="vipInfo_btn" class="vipInfo_btn"/>
        </th>
    </tr>
    <tr>
        <th colspan="2">
            <a href="adminServlet?oper=adminQueryVips" target="frameRight">
                <input type="button" value="返回" class="vipInfo_btn"/>
            </a>
        </th>
    </tr>
</table>

</body>
</html>
