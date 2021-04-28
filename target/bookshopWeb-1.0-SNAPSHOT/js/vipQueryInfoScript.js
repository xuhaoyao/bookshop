window.onload = function(){
    //用户名
    var username = document.getElementById("username");
    var idMsg = document.getElementById("idMsg");
    username.onfocus = function(){
        if(idMsg.innerText.length > 0)
            idMsg.innerText = "";
    }
    username.onblur = function(){
        var uname = username.value.trim();
        if(uname.length == 0)
            idMsg.innerText = "用户名不能为空!";
        else if(uname.length < 6 || uname.length > 14)
            idMsg.innerText = "用户名必须在6-14位之间!";
        else{
            var regExp = /^[A-Z0-9a-z]+$/;
            if(regExp.test(username.value) == false)
                idMsg.innerText = "用户名只能有数字和字母组成，不能含有其他符号";
        }
    }
    //密码
    var password = document.getElementById("password");
    var passwordMsg = document.getElementById("passwordMsg");
    password.onfocus = function(){
        if(passwordMsg.innerText.length > 0)
            passwordMsg.innerText = "";
    }
    password.onblur = function(){
        var pwd = password.value.trim();
        if(pwd.length < 6 || pwd.length > 14)
            passwordMsg.innerText = "密码必须在6-14位之间";
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
        if(this.value.trim() == "")
            emailMsg.innerText = "邮箱地址不能为空"
        if(regExp.test(email.value) == false){
            emailMsg.innerText = "邮箱地址不合法";
        }
    }
    //收货地址
    var vipAddress = document.getElementById("vipAddress");
    var addressMsg = document.getElementById("addressMsg");
    vipAddress.onfocus = function(){
        addressMsg.innerText = "";
    }
    vipAddress.onblur = function(){
        if(this.value.trim() == "")
            addressMsg.innerText = "收获地址不能为空!";
    }
    //联系电话
    //11位手机号码正则
    var regPhone = /^[1][3,4,5,7,8][0-9]{9}$/;
    var vipPhone = document.getElementById("vipPhone");
    var phoneMsg = document.getElementById("phoneMsg");
    vipPhone.onfocus = function(){
        phoneMsg.innerText = "";
    }
    vipPhone.onblur = function(){
        if(this.value.trim() == "")
            phoneMsg.innerText = "联系电话不能为空!";
        if(!regPhone.test(this.value))
            phoneMsg.innerText = "联系电话格式有误!";
    }
    //注册
    var btn = document.getElementById("vipQueryInfo_updateBtn");
    btn.onclick = function(){
        username.focus();
        username.blur();

        password.focus();
        password.blur();

        email.focus();
        email.blur();

        vipAddress.focus();
        vipAddress.blur();

        vipPhone.focus();
        vipPhone.blur();

        if(idMsg.innerText == "" && passwordMsg.innerText == "" && emailMsg.innerText == ""
        && vipAddress.innerText == "" && vipPhone.innerText == ""){
            $.post("vipServlet",{
                username : username.value.trim(),
                password : password.value.trim(),
                email : email.value.trim(),
                address : vipAddress.value.trim(),
                phone : vipPhone.value.trim(),
                oper : "vipUpdateInfo"
            },callback,"text");
            function callback(resp){
                if("该邮箱已被注册" == resp)
                    emailMsg.innerText = resp;
                else {
                    alert(resp);
                }
            }
        }
    }
}