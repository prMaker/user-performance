<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\5\29 0029
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="./../header.jsp"%>

<html>
<head>
    <title>新增用户信息</title>
</head>
<style>
    .padding_20{padding: 50px 50px 50px 50px }
</style>
<body>

<div class="padding_20">
        <%--
        protected Long loginId;
        protected Long userInfoId;
        protected Integer isDeleted;
        protected String loginName;
        protected String password;
        protected Integer dispostion;
        protected Timestamp createdTime;
        protected Timestamp modifiedTime;
        protected String createdUser;
        protected String modifiedUser;
        //TODO 是否添加
        protected Long createdUserId;// 创建人用户ID
        protected Long modifiedUserId;// 修改人用户ID
        --%>
        <table>
            <thead>
            <th>
            <td colspan="2" align="center">新增用户账号信息</td>
            </th>
            </thead>
            <tbody>
                <form id="do-save-form" action="/userLogin/doSave?loginId=${sessionScope.userLogin.loginId}" method="post">
                    <%--
                protected String loginName;
                protected String password;
                protected Integer dispostion;
                    --%>
                    <tr>
                        <td>登录用户名：</td>
                        <td>
                            <input type="text" id="user-login-login-name" name="userLogin.loginName">
                            <span id="loginName-errMsg"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>登录密码：</td>
                        <td>
                            <input type="password" id="user-login-password" name="userLogin.password">
                            <span id="password-errMsg"></span>
                        </td>
                    </tr>

                    <tr>
                        <td>确认登录密码：</td>
                        <td>
                            <input type="password" name="repeatPassword" id="user-login-repeat-password">
                            <span id="repeatPassword-errMsg"></span>
                        </td>
                    </tr>

                    <tr>
                        <td>用户职位</td>
                        <td>
                            <select name="userLogin.dispostion" id="user-info-disposition">
                                <option value="">请选择</option>
                                <c:if test="${4 < sessionScope.userLogin.dispostion}">
                                    <option value="4">部门经理</option>
                                </c:if>
                                <c:if test="${3 < sessionScope.userLogin.dispostion}">
                                    <option value="3">中心经理</option>
                                </c:if>
                                <c:if test="${2 < sessionScope.userLogin.dispostion}">
                                    <option value="2">负责人</option>
                                </c:if>
                                <c:if test="${1 < sessionScope.userLogin.dispostion}">
                                    <option value="1">普通员工</option>
                                </c:if>
                            </select>
                            <span class="text-danger" id="dispostion-errMsg"></span>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td align="center">
                            <input type="button" id="save-btn" value="保存">
                        </td>
                    </tr>
                </form>


            </tbody>
        </table>
</div>
<%--<script src="/main/js/login/toSave.js"></script>--%>
<script>
    (function(){
        $("#user-login-login-name").blur(checkUserNameIsRepeat);

        $("#save-btn").click(formSave);

        function checkUserNameIsRepeat() {
            $.ajax({
                url:"/userLogin/checkUserNameIsRepeat",
                type:"POST",
                dataType:"json",
                data:{loginName:$("#user-login-name").val()},
                success:function (data) {
                    if(!data.success()){
                        $("#loginName-errMsg").text(data.msg);
                    }
                }
            });
        }
        function formSave() {

            var loginName = $("#user-login-login-name").val();
            var passWord = $("#user-login-password").val();
            var repeatPassWord = $("#user-login-repeat-password").val();
            var disposition = $("#user-info-disposition").val();
            if(!checkparam()){
                return;
            }
            $("#do-save-form").submit();
            function checkparam() {
                if(!loginName){
                    $("#loginName-errMsg").text("登陆名必填！");
                    return false;
                }
                if(loginName.length > 2 || loginName.length < 20){
                    $("#loginName-errMsg").text("登录名长度不能小于2不能大于20！");
                    return false;
                }
                if(!passWord){
                    $("#password-errMsg").text("密码必填！");
                    return false;
                }
                if(passWord.length > 2 || passWord.length < 20){
                    $("#password-errMsg").text("密码长度不能小于2不能大于20！");
                    return false;
                }
                if(passWord !== repeatPassWord){
                    $("#repeatPassword-errMsg").text("两次密码请相同！");
                    return false;
                }
                if(!disposition){
                    $("#dispostion-errMsg").text("请选择职位！");
                    return false;
                }
                return true;
            }
        }
    })();
</script>

</body>
</html>
