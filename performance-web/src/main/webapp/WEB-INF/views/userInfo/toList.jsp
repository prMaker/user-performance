<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\5\29 0029
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../header.jsp"%>
<html>
<head>
    <title>列表信息</title>
</head>
<style>
    .pd50{padding: 50px;}
    .pd20{padding: 20px;}
    .inline{display: inline;}
    .inline-block{display: inline-block}
    .pd-lr20{padding: 0px 20px 0px 20px}
    .float-left{float: left}
    .float-right{float: right}
    .postionA{position: absolute}
</style>
<body>

    <div class="pd50 postionA">

        <c:if test="${sessionScope.userInfo.dispostion > 1}">
            <a href="/userLogin/toSave?loginId=${sessionScope.userInfo.loginId}">注册新用户</a>
        </c:if>
        <a href="/userLogin/toList?loginId=${sessionScope.userInfo.loginId}">用户列表</a>
        <a href="/userLogin/toLogin">注销登录</a>

        <%--新用户--%>
                <c:if test="${noUserInfo}">
                    <div class="inline-block">
                            <span>
                                只有更新完个人信息才能查看此项功能！
                            </span>
                    </div>
                    <div class="inline-block">
                        <a href="/userInfo/toSave?loginId=${sessionScope.userLogin.loginId}">新增个人信息</a>
                    </div>
                </c:if>

        <%--老用户--%>
                <c:if test="${!noUserInfo}">
                    <div>
                        <a href="/userInfo/toSave?loginId=${sessionScope.userInfo.loginId}">修改个人信息</a>
                    </div>
                    <div id="user-info-search">
                        <div class="inline-block">
                            <div class="inline">
                                <span>选择绩效月份</span>
                            </div>
                            <div class="inline">
                                <span><input type="text" id="user-info-check-performance-time"></span>
                                <input type="hidden" id="user-info-performance-time">
                            </div>
                            <button id="user-info-btn-search">搜索</button>
                            <c:if test="${sessionScope.userInfo.dispostion == 4}">
                                <%--解锁数据--%>
                                <div class="inline-block pd-lr20" id="user-info-lock-div">
                                    <button id="user-info-lock">锁定数据</button>
                                </div>
                            </c:if>
                            <div class="inline" id="user-performance-add-div" style="display: none;">
                                <button id="user-performance-add">添加用户审核数据</button>
                            </div>
                        </div>
                    </div>

                    <!-- 内容区域 -->
                    <div class="app-content pd50">
                        <div ui-butterbar></div>
                        <a href class="off-screen-toggle hide" data-toggle="class:off-screen" data-target=".app-aside"></a>
                        <div class="app-content-body fade-in-up">

                            <div>
                                <h3><span id="info-msg">${null eq infoMsg ? '' : infoMsg}</span></h3>
                            </div>

                            <div id="screen_content">
                                <table class="table" id="user-info-data-table">
                                    <thead>
                                    <tr>
                                        <th>编号</th>
                                        <th>用户姓名</th>
                                        <th>用户性别</th>
                                        <%--<th>用户生日</th>--%>
                                        <th>用户身份证号</th>
                                        <th>用户电话</th>
                                        <th>用户职位</th>
                                        <th>用户创建时间</th>
                                        <th>用户当月评分</th>
                                        <th>用户当月绩效评价</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>


                        </div>
                    </div>
                </c:if>
    </div>




<!-- /内容区域 -->
<script>
    var user_login_id = ${sessionScope.userLogin.loginId};
    var performToAdd = ${null eq performToAdd ? false : performToAdd};
</script>
<script src="/main/js/userInfo/list.js"></script>
</body>
</html>
