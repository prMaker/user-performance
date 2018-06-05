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
                    <button type="submit">登录</button>
                </td>
            </tr>
        </table>


    </form>

</div>


<%--<a href="/login/loginIn?loginId=1">登录</a>--%>


<!-- jQuery 2.2.0 -->





<%--<script src="/plugins/jQuery/jquery-3.1.1.min.js"></script>--%>

<script>
    (function(){


        //校验
        $("#do-login-form").validate({
            errorClass:"text-danger",
            errorElement:"span",
            rules:{
                loginName:{
                    required:true,
                    rangelength:[1,20]
                },
                password:{
                    required:true,
                    rangelength:[1,20]
                }
            },
            messages:{
                loginName:{
                    required:"请输入用户名",
                    rangelength:"用户名长度2~20位"
                },
                password:{
                    required:"请输入密码",
                    rangelength:"密码长度2~20位"
                }
            },
            submitHandler:function(form){
                $("#do-login-form").submit();
            }
        });




        $("#userInfoList").click(function () {
            console.log("点击时间");
        });

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
