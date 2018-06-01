<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\5\29 0029
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>列表信息</title>
    <link rel="stylesheet" href="/css/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/plugins/datatables/css/dataTables.bootstrap.min.css">
</head>
<style>
    .pd50{padding: 50px;}
    .pd20{padding: 20px;}
</style>
<body>

<div class="pd50">
    <div id="user-info-search">
        <div style="display: inline-block;">
            <h2><input type="text" id="user-info-performance-time"></h2>
        </div>
        <div><button id="user-info-btn-search">搜索</button></div>
    </div>
</div>

<!-- 内容区域 -->
<div class="app-content pd50">
    <div ui-butterbar></div>
    <a href class="off-screen-toggle hide" data-toggle="class:off-screen" data-target=".app-aside"></a>
    <div class="app-content-body fade-in-up">

        <div>
            <h3><span id="info-msg"></span></h3>
        </div>

        <div id="screen_content">
            <table class="table" id="user-info-data-table">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>用户姓名</th>
                    <th>用户性别</th>
                    <th>用户生日</th>
                    <th>用户身份证号</th>
                    <th>用户电话</th>
                    <th>用户职位</th>
                    <th>用户创建时间</th>
                    <th>用户当月评分</th>
                    <th>用户当月绩效评价</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>


    </div>
</div>
<!-- /内容区域 -->
<script src="/plugins/jQuery/jQuery-2.2.0.min.js"></script>

<script src="/plugins/datatables/js/jquery.dataTables.min.js"></script>
<script src="/plugins/datatables/js/dataTables.bootstrap.min.js"></script>
<script src="/plugins/xdate/xdate.js"></script>
<script>
    var user_login_id = ${sessionScope.userLogin.loginId};
</script>
<script src="/main/js/userInfo/list.js"></script>
</body>
</html>
