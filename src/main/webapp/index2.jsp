<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link rel="stylesheet" type="text/css" href="jsps/css/login.css"/>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
</head>
<body>
<div id="div1">
    <table>
        <tr>
            <th>用户名</th>
            <td>
                <input type="text" id="username" class="input" placeholder="请输入用户名..."/>
            </td>
        </tr>
        <tr>
            <th>密码</th>
            <td>
                <input type="password" id="password" class="input" placeholder="请输入密码..."/>
            </td>
        </tr>
        <tr>
            <th colspan="2">
                <input type="button" value="登陆" id="btn_login"/>
            </th>
        </tr>
        <tr>
            <th colspan="2">
                <a href="register.html">
                    <input type="button" value="注册" id="btn_register"/>
                </a>
            </th>
        </tr>
        <tr>
            <th colspan="2">
                <a href="jsps/main.jsp">
                    <input type="button" id = "btn_visitor" value="游客入口"/>
                </a>
            </th>
        </tr>
    </table>
</div>
</body>
</html>