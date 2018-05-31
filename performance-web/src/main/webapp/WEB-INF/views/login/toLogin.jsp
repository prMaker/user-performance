<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\5\29 0029
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>loginPage</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/plugins/fontawesome/css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="/static/plugins/iCheck/square/blue.css">
</head>
<body>

<button id="login">登录</button>

<form action="/login/doLogin" method="post">

    <span id="infoMsg">
        <h3>
            ${infoMsg}
        </h3>
    </span>
    <input type="text" name="loginName" id="user-login-name">
    <input type="password" name="password" id="user-login-password">
    <button type="submit">登录</button>

</form>

<button id="saveUserInfo">UserInfo保存</button>
<button id="userInfoList">UserInfo数据列表</button>


<%--<a href="/login/loginIn?loginId=1">登录</a>--%>


<!-- jQuery 2.2.0 -->
<script src="../../static/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<%--<script src="/static/bootstrap/js/bootstrap.min.js"></script>--%>
<%--<!-- AdminLTE App -->--%>
<%--<script src="/static/dist/js/app.min.js"></script>--%>

<script>

    (function(){

        console.log("123123");
        $(function () {
            console.log("12332");
        });

        $("#saveUserInfo").delegate("click",function () {
            $.ajax({
                url:"/userInfo/doSave",
                type:"POST",
                data:{
                    loginId:67,
                    idCard:410,
                    userName:"测试添加",
                    birthday:"20170905",
                    sex:1,
                    phone:110,
                    pid:33,
                    dispostion:1,
                    createdUserInfoId:33,
                    modifiedUserInfoId:33
                },
                success:function (data) {
                    alert(data);
                },
                error:function () {
                    alert("请求异常！");
                }
            });
        });

        $("#userInfoList").delegate("click", function () {
            $.ajax({
                url:"/userInfo/doSave",
                type:"POST",
                data:{},
                success:function (data) {
                    alert(data);
                },
                error:function () {
                    alert("请求异常！");
                }
            });
        })
    })();
</script>
</body>
</html>
