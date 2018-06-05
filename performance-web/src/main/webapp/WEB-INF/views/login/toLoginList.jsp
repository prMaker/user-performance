<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\6\5 0005
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="./../header.jsp"%>
<html>
<head>
    <title>员工信息列表</title>
</head>
<body>

<div class="pd50">
    <a href="/userInfo/toList?loginId=${sessionScope.userLogin.loginId}">
        员工绩效列表
    </a>

    <div id="table-container">
        <table id="login-list-table" class="table">
            <thead>
            <tr>
                <th>用户登录信息编号</th>
                <th>用户ID</th>
                <th>用户密码</th>
                <th>用户职位</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
</div>


<script>
    var user_login_id = ${sessionScope.userLogin.loginId};
</script>
<script src="/main/js/login/toLoginList.js"></script>
</body>
</html>
