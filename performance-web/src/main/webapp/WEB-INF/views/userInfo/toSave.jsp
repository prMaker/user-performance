<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\5\29 0029
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户基本信息</title>
</head>
<style>
    .padding_20{padding: 50px 50px 50px 50px }
</style>
<body>


<c:choose>
    <%--新增--%>
    <c:when test="${save}">
        <div class="padding_20 pd50">
            <form id="do-save-form" action="/userInfo/doSave?loginId=${sessionScope.userLogin.loginId}" method="post">

                <input type="hidden" name="loginId" value="${sessionScope.userLogin.loginId}">
                <table>
                    <thead>
                    <th>
                    <td colspan="2" align="center">用户基本信息</td>
                    </th>
                    </thead>
                        <%--
                            身份证号
                            用户姓名
                            用户生日
                            用户性别
                            用户电话
                            职位
                        --%>
                    <tbody>
                    <tr>
                        <td>身份证号：</td>
                        <td><input type="text" name="userInfo.idCard"></td>
                    </tr>
                    <tr>
                        <td>用户姓名</td>
                        <td><input type="text" name="userInfo.userName"></td>
                    </tr>
                    <tr>
                        <td>用户生日</td>
                        <td>
                            <input type="text" readonly id="user-info-birthday" name="userInfo.birthday">
                        </td>
                    </tr>
                    <tr>
                        <td>用户性别</td>
                        <td>
                            <input type="radio" value="1" name="userInfo.sex"> 男
                            <input type="radio" value="0" name="userInfo.sex"> 女
                        </td>
                    </tr>
                    <tr>
                        <td>用户电话</td>
                        <td>
                            <input type="text" name="userInfo.phone">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <button type="submit">保存</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </c:when>
    <c:when test="${update}">
        <%-- 编辑 --%>

    <div class="pd50">
            <form id="do-save-form" action="/userInfo/doSave?loginId=${sessionScope.userLogin.loginId}" method="post">

        <input type="hidden" name="userInfoId" value="${sessionScope.userInfo.userInfoId}">
        <input type="hidden" name="loginId" value="${sessionScope.userLogin.loginId}">
        <input type="hidden" name="pid" value="${sessionScope.userInfo.userInfoId}">
        <input type="hidden" name="createdUserInfoId" value="${sessionScope.userInfo.userInfoId}">
        <input type="hidden" name="modifiedUserInfoId" value="${sessionScope.userInfo.userInfoId}">
        <table>
            <thead>
            <th>
            <td colspan="2" align="center">用户基本信息</td>
            </th>
            </thead>
            <tbody>
            <tr>
                <td>身份证号：</td>
                <td><input type="text" name="userInfo.idCard" value="${sessionScope.userInfo.idCard}"></td>
            </tr>
            <tr>
                <td>用户姓名</td>
                <td><input type="text" name="userInfo.userName" value="${sessionScope.userInfo.userName}"></td>
            </tr>
            <tr>
                <td>用户生日</td>
                <td>
                    <input type="text" readonly id="user-info-birthday" name="userInfo.birthday" value="${sessionScope.userInfo.birthday}">
                </td>
            </tr>
            <tr>
                <td>用户性别</td>
                <td>
                    <input type="radio" value="1" <c:if test="${sessionScope.userInfo.sex == 1}">checked</c:if> name="userInfo.sex"> 男
                    <input type="radio" value="0" <c:if test="${sessionScope.userInfo.sex == 0}">checked</c:if> name="userInfo.sex"> 女
                </td>
            </tr>
            <tr>
                <td>用户电话</td>
                <td>
                    <input type="text" name="userInfo.phone" value="${sessionScope.userInfo.phone}">
                </td>
            </tr>
            <tr>
                <td></td>
                <td align="center">
                    <button type="submit">保存</button>
                </td>
            </tr>
            </tbody>
        </table>
        </form>
    </div>





    </c:when>
</c:choose>


<script src="/main/js/userInfo/toSave.js"></script>
</body>
</html>
