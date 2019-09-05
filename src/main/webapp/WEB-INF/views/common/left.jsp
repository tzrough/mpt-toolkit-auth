<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
    <!-- 导航栏 begin -->
    <div id="nav-left">
        <ul class="nav nav-pills nav-stacked ">
            <li><a class="h4" href="#">权限管理</a></li>
            <li id="authCreate"><a onclick="loadCreatePage()" >权限创建</a></li>
            <li><a onclick="loadAuthServiceList()" >权限列表</a></li>
        </ul>
        <!-- 权限 service list -->
        <ul id="serviceList" class="nav nav-pills nav-stacked "></ul>

        <ul class="nav nav-pills nav-stacked ">
            <li><a class="active h4" href="#">权限管理</a></li>
            <li><a class="active h4" href="#">权限管理</a></li>
        </ul>
    </div>
    <!-- 导航栏 end -->
</body>
</html>
