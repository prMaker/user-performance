<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\5\29 0029
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增用户信息</title>
</head>
<style>
    .padding_20{padding: 50px 50px 50px 50px }
</style>
<body>

<div class="padding_20">
    <form action="/userLogin/doSave?loginId=${sessionScope.userLogin.loginId}" method="post">
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
            <%--
        protected String loginName;
        protected String password;
        protected Integer dispostion;
            --%>
            <tr>
                <td>登录用户名：</td>
                <td><input type="text" name="userLogin.loginName"></td>
            </tr>
            <tr>
                <td>登录密码：</td>
                <td><input type="password" name="userLogin.password"></td>
            </tr>

            <tr>
                <td>确认登录密码：</td>
                <td><input type="password"></td>
            </tr>

            <tr>
                <td>用户职位</td>
                <td>
                    <select name="userLogin.dispostion" id="user-info-disposition">
                        <option value="">请选择</option>
                        <option value="4">部门经理</option>
                        <option value="3">中心经理</option>
                        <option value="2">负责人</option>
                        <option value="1">普通员工</option>
                    </select>
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
<script>
    // FIXME 校验密码是否是同一个密码
</script>

</body>
</html>
