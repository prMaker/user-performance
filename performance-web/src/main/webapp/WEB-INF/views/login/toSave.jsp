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
        <table>
            <thead>
            <th>
            <td colspan="2" align="center">新增用户账号信息</td>
            </th>
            </thead>
            <tbody>
                <form id="do-save-form" action="/userLogin/doSave?loginId=${sessionScope.userLogin.loginId}" method="post">

                    <tr>
                        <td>登录用户名：</td>
                        <td>
                            <input type="text" id="user-login-login-name" name="userLogin.loginName">
                        </td>
                        <td width="150px">
                            <span id="loginName-errMsg"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>登录密码：</td>
                        <td>
                            <input type="password" id="user-login-password" name="userLogin.password">
                        </td>
                        <td width="150px">
                            <span id="password-errMsg"></span>
                        </td>
                    </tr>

                    <tr>
                        <td>确认登录密码：</td>
                        <td>
                            <input type="password" name="repeatPassword" id="user-login-repeat-password">
                        </td>
                        <td width="150px">
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
                        </td>
                        <td width="150px">
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
<script src="/main/js/login/toSave.js"></script>
</body>
</html>
