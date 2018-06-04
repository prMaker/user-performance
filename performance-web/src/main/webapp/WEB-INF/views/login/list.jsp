<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\5\29 0029
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="pd50">

    <a href="/userInfo/toList?loginId=${sessionScope.userLogin.loginId}">
        员工列表
    </a>

</div>

<h3>
    ${infoMsg}
</h3>
${count}
----------------------------
${logins}

<script src="/plugins/jQuery/jQuery-2.2.0.min.js"></script>
</body>
</html>
