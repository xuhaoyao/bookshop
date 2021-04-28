<%--
  Created by IntelliJ IDEA.
  User: xuhaoyao
  Date: 2021/4/14
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>showBuyBookInfo</title>
    <link rel="stylesheet" type="text/css" href="jsps/vip/css/showBuyBookInfoCss.css"/>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(function(){
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
            //提交订单
            $("#buyBookBtn").click(function(){
                vipAddress.focus();
                vipAddress.blur();
                vipPhone.focus();
                vipPhone.blur();
                if(addressMsg.innerText == "" && phoneMsg.innerText == ""){
                    var nowTime = new Date();
                    $.post("orderServlet",{
                        oper : "buyBook",
                        bookid : ${book.bookid},
                        Date : nowTime.getTime(),
                        address : vipAddress.value.trim(),
                        phone : vipPhone.value.trim(),
                        comeFrom : "${comeFrom}"
                    },buyBook_callback,"text");
                    function buyBook_callback(resp){
                        alert(resp);
                    }
                }
            })
        })
    </script>
</head>
<body>
<br><br><br>
<table align="center">
    <tr>
        <th>收获地址:</th>
        <td>
            <textarea id="vipAddress" placeholder="请填写收获地址" style="resize: none">${vipInfo.address}</textarea>
            <span id="addressMsg"></span>
        </td>
    </tr>
    <tr>
        <th>联系电话:</th>
        <td>
            <input type="text" id="vipPhone" value="${vipInfo.phone}" placeholder="请填写联系电话"/>
            <span id="phoneMsg"></span>
        </td>
    </tr>
    <tr>
        <td><img src="${book.bookImg}" width="150px" height="180px"/></td>
        <td>
            <table>
                <tr>
                    <th>书名</th><td>${book.bookName}</td>
                </tr>
                <tr>
                    <th>作者</th>
                    <td>
                        <textarea class="bookInfoTextArea" readonly>${book.author}</textarea>
                    </td>
                </tr>
                <tr>
                    <th>出版社</th> <td>${book.press}</td>
                </tr>
                <tr>
                    <th>出版年</th><td>${book.publishDate}</td>
                </tr>
                <tr>
                    <th>定价</th>
                    <td>
                        ${book.price}
                    </td>
                </tr>
                <tr>
                    <th>ISBN</th><td>${book.isbn}</td>
                </tr>
                <tr>
                    <th>简介</th>
                    <td>
                        <textarea class="bookInfoTextArea" readonly>${book.bookInfo}</textarea>
                    </td>
                </tr>
                <tr>
                    <th>支付方式</th>
                    <td>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <img src="bookImages/WeChat.jpg" class="payMoney"/>
                        <input type="radio" name="payMoney" value="WeChat" checked/>
                        <img src="bookImages/zfb.jpg" class="payMoney"/>
                        <input type="radio" name="payMoney" value="zfb"/>
                    </td>
                </tr>
            </table>
        </td>
    <tr>
        <th colspan="2">
            <input type="button" id="buyBookBtn" value="提交订单"/>
            <input type="button" id="buyBookBackBtn" value="返回" onclick="window.history.back()"/>
        </th>
    </tr>
    </tr>
</table>
</body>
</html>
