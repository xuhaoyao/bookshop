<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>main</title>
    <link rel="stylesheet" type="text/css" href="css/page.css"/>
    <script type="text/javascript">
        window.onload = function(){
            <%
                String title = null;
                if(session != null)
                    title = (String)session.getAttribute("top_Title");
                if("网上购书小站管理系统" == title){
            %>
                    document.getElementById("frameLeft").src = "admin_left.html";
            <%
                }
                else{
            %>
                    document.getElementById("frameLeft").src = "vip_left.html";
            <%
                    }
            %>
        }
    </script>
</head>
<frameset rows="15%,85%">
    <frame name="frameTop" src="top.jsp">
    <frameset cols="15%,85%">
        <frame id = "frameLeft" name="frameLeft" src="">
        <frame id="frameRight" name="frameRight">
    </frameset>
</frameset>
</html>
