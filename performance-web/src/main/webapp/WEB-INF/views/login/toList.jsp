<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\5\29 0029
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="./../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工信息表</title>
</head>
<body>

<div id="head-container" class="m50">
    <a href="/userInfo/toList?loginId=${sessionScope.userLogin.loginId}">
        员工绩效列表
    </a>
</div>

<div id="body-container" class="m50">

    <c:if test="${!seeList}">
        <div class="info-container inline-block">
            <h3>
                <span>${infoMsg}</span>
            </h3>
        </div>
    </c:if>

    <c:if test="${seeList}">
        <div id="table-container">
            <table id="login-list-table" class="table">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>身份证号</th>
                    <th>用户姓名</th>
                    <th>用户生日</th>
                    <th>用户性别</th>
                    <th>用户电话</th>
                    <th>职位</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </c:if>

</div>

<script>
    var user_login_id = ${sessionScope.userLogin.loginId}
</script>
<script src="/main/js/login/toList.js"></script>
</body>
</html>
