<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\5\29 0029
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="./../header.jsp"%>
<html>
<head>
    <title>员工信息表</title>
</head>
<body>

<div id="head-container" class="m50">
    <a href="/userInfo/toList?loginId=${sessionScope.userLogin.loginId}">
        员工列表
    </a>
</div>

<div id="body-container" class="m50">

    <div class="info-container inline-block">
        <h3>
            <span>${infoMsg}</span>
        </h3>
    </div>

    <div id="table-container" class="pd50">

        <%--
编号
身份证号
用户姓名
用户生日
用户性别
用户电话
职位
创建时间
操作
        --%>
        <table id="login-list-table">
            <thead>
                <th>
                    <td>编号</td>
                    <td>身份证号</td>
                    <td>用户姓名</td>
                    <td>用户生日</td>
                    <td>用户性别</td>
                    <td>用户电话</td>
                    <td>职位</td>
                    <td>创建时间</td>
                    <td>操作</td>
                </th>
            </thead>
            <tbody>

            </tbody>
        </table>
        ${count}
        ----------------------------
        ${logins}

    </div>

</div>

<script src="/main/js/login/toList.js"></script>
</body>
</html>
