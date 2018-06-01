<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\5\29 0029
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
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
                        <td><input type="text" id="user-login-login-name" name="userLogin.loginName"></td>
                    </tr>
                    <tr>
                        <td>登录密码：</td>
                        <td><input type="password" id="user-login-password" name="userLogin.password"></td>
                    </tr>

                    <tr>
                        <td>确认登录密码：</td>
                        <td><input type="password" id="user-login-repeat-password"></td>
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
                        </td>
                    </tr>
                </form>

                <tr>
                    <td colspan="2" align="center">
                        <button type="submit" id="save-btn">保存</button>
                    </td>
                </tr>

            </tbody>
        </table>
</div>
<script src="/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script>
    (function(){
        $("#save-btn").click(function () {
            var loginName = $("#user-login-login-name").val();
            var passWord = $("#user-login-password").val();
            var repeatPassWord = $("#user-login-repeat-password").val();
            var disposition = $("#user-info-disposition").val();
            if(!checkparam()){
                return;
            }
            $.extend({
                PostSubmitForm: function (url, args) {
                    var body = $(document.body),
                        form = $("<form method='post' style='display:none'></form>"),
                        input;
                    form.attr({ "action": url });
                    $.each(args, function (key, value) {
                        input = $("<input type='hidden'>");
                        input.attr({ "name": key });
                        input.val(value);
                        form.append(input);
                    });

                    //IE低版本和火狐下
                    form.appendTo(document.body);
                    form.submit();
                    document.body.removeChild(form[0]);
                }
            });

            var url = $("#do-save-form").attr("action");
            var data = {
                "userLogin.loginName":loginName,
                "userLogin.password":passWord,
                "userLogin.dispostion":disposition
            };
            $.PostSubmitForm(url , data);

            function checkparam() {
                if(!loginName){
                    alert("登陆名必填！");
                    return false;
                }
                if(!passWord){
                    alert("密码必填！");
                    return false;
                }
                if(passWord !== repeatPassWord){
                    alert("密码必须相等！");
                    return false;
                }
                if(!disposition){
                    alert("请选择职位！");
                    return false;
                }
                return true;
            }
        });
    })();
</script>

</body>
</html>
