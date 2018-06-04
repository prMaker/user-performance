<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\6\4 0004
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- JSP 使用EL 中如果可能为空： 则使用以下方案 --%>

${null eq infoMsg ? '' : infoMsg}


</body>
</html>
