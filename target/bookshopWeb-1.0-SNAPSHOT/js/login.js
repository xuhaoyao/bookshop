$(function(){
    $("#btn_login").click(function(){
        $.post("loginServlet",{
            "username" : $("#username").val(),
            "password" : $("#password").val()
        },callback);
    })
    function callback(resp){
        if("登陆成功" == resp)
            location.replace("jsps/main.html");
        else
            alert(resp);
    }
})