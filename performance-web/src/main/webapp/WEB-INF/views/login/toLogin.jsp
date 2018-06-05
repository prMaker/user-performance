<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\5\29 0029
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>loginPage</title>
    <!-- Tell the browser to be responsive to screen width -->
</head>
<style>
    .pd50{padding: 50px;}
    .pd20{padding: 20px;}
</style>
<body>


<div class="pd50">


    <form action="/userLogin/doLogin" id="do-login-form" method="post">

        <div class="pd20">
            <span id="infoMsg">
                <h3>
                    ${infoMsg}
                </h3>
            </span>
        </div>
        <table>
            <tr>
                <td>用户名：</td>
                <td>
                    <input type="text" name="loginName" id="user-login-name">
                </td>
            </tr>
            <tr>
                <td>密码：</td>
                <td>
                    <input type="password" name="password" id="user-login-password">
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <button type="submit" id="login-button">登录</button>
                </td>
            </tr>
        </table>
    </form>

</div>

<script>
    (function(){
        $("#login-button").click(function (e) {
            var loginName = $("#user-login-name").val();
            var password = $("#user-login-password").val();
            if(!checkParam()){
                e.preventDefault();
                return;
            }
            console.log("提交表单")

            function checkParam() {
                if(!loginName){
                    alert("用户名必填");
                    return false;
                }
                if(!password){
                    alert("密码必填！")
                    return false;
                }
                if(loginName.length > 20 || loginName.length < 2){
                    alert("用户名长度2~20位");
                    return false;
                }
                if(password.length > 20 || password.length < 2){
                    alert("密码长度2~20位");
                    return false;
                }
                return true;
            }

        });
    })();
</script>
</body>
</html>
