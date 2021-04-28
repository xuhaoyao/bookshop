/*
    1、用户名不能为空
    2、用户名和密码必须在6-14位之间
    3、用户名只能有数字和字母组成，不能含有其他符号(正则表达式)
    4、密码和确认密码一致，邮箱地址合法
    5、统一失去焦点验证
    6、错误提示信息统一在span标签中提示，并且要求字体12号，红色。
    7、文本框再次获得焦点后，清空错误提示信息，如果文本框中数据不合法要求清空文本框的value
    8、最终表单中所有项均合法方可提交
*/
window.onload = function(){
    //用户名
    var username = document.getElementById("username");
    var idMsg = document.getElementById("idMsg");
    username.onfocus = function(){
        if(idMsg.innerText.length > 0)
            idMsg.innerText = "";
    }
    username.onblur = function(){
        if(username.value.length == 0)
            idMsg.innerText = "用户名不能为空!";
        else if(username.value.length < 6 || username.value.length > 14)
            idMsg.innerText = "用户名必须在6-14位之间!";
        else{
            var regExp = /^[A-Z0-9a-z]+$/;
            if(regExp.test(username.value) == false)
                idMsg.innerText = "用户名只能有数字和字母组成，不能含有其他符号";
        }
    }
    //密码
    var password = document.getElementById("password");
    var checkedPwd = document.getElementById("checkedPwd");
    var pwdMsg = document.getElementById("pwdMsg");
    var passwordMsg = document.getElementById("passwordMsg");
    password.onfocus = function(){
        if(passwordMsg.innerText.length > 0)
            passwordMsg.innerText = "";
    }
    password.onblur = function(){
        if(password.value.length < 6 || password.value.length > 14)
            passwordMsg.innerText = "密码必须在6-14位之间";
    }
    checkedPwd.onfocus = function(){
        if(pwdMsg.innerText.length > 0)
            pwdMsg.innerText = "";
    }
    checkedPwd.onblur = function(){
        if(password.value != checkedPwd.value)
            pwdMsg.innerText = "确认密码与密码不一致!";
    }
    //邮箱
    var email = document.getElementById("email");
    var emailMsg = document.getElementById("emailMsg");
    email.onfocus = function(){
        if(emailMsg.innerText.length > 0)
            emailMsg.innerText = "";
    }
    email.onblur = function(){
        var regExp = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        if(regExp.test(email.value) == false){
            emailMsg.innerText = "邮箱地址不合法";
        }
    }
    //注册
    var btn = document.getElementById("btn");
    var form = document.getElementById("form");
    btn.onclick = function(){
        username.focus();
        username.blur();

        checkedPwd.focus();
        checkedPwd.blur();

        email.focus();
        email.blur();

        if(idMsg.innerText == "" && pwdMsg.innerText == "" && emailMsg.innerText == ""){
            function callback(resp){
                if("该邮箱已被注册" == resp)
                    emailMsg.innerText = resp;
                else if("该用户名已存在!" == resp)
                    idMsg.innerText = resp;
                else {
                    alert(resp);
                    window.location.replace("index.html");
                }
            }
            $.post("vipServlet",{
                username : username.value,
                password : password.value,
                email : email.value,
                oper : "vipRegister"
            },callback,"text");
        }
    }
}