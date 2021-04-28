window.onload = function(){
    var i3 = document.getElementsByClassName('input_3');
    for(var i=0;i<i3.length;i++){
        i3[i].onmouseover = function(){
            this.style.backgroundColor = "#23271F";
            this.style.color = "#fff";
        }
        i3[i].onmouseout = function(){
            this.style.backgroundColor = "#fff";
            this.style.color = "#23271F";
        }
    }
    var pass = document.getElementById("password");
    pass.onfocus = function(){
        pass.type = "password";
    }
    $("#btn_login").click(function(){
        if(document.getElementById("radio_1").checked){
            $.post("vipServlet",{
                username : $("#username").val(),
                password : $("#password").val(),
                oper : "vipLogin"
            },vip_callback,"text");
        }
        else{
            $.post("adminServlet",{
                username : $("#username").val(),
                password : $("#password").val(),
                oper : "adminLogin"
            },admin_callback,"text");
        }
    })
    function vip_callback(resp){
        if("登陆成功" == resp)
            window.location.replace("jsps/main.jsp");
        else
            alert(resp);  //返回登录失败信息(用户名或密码错误)
    }
    function admin_callback(resp){
        if("登陆成功" == resp)
            window.location.replace("jsps/main.jsp");
        else
            alert(resp);
    }
}