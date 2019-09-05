<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <script src="${ctx}/static/js/auth/service.js"></script>
</head>
<body>
    <!-- 权限模块列表 begin -->
    <div class="panel-group" id="panelParent">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h4 id="funcAuthHeader" class="panel-title" data-toggle="collapse" data-parent="#panelParent" href="#funcAuthDiv">
                    <a href="#">功能权限 </a>
                </h4>
            </div>
            <div id="funcAuthDiv" class="panel-collapse collapse in">
            </div>
        </div>

        <div class="panel panel-info">
            <div class="panel-heading">
                <h4 id="dataAuthHeader" class="panel-title" data-toggle="collapse" data-parent="#panelParent" href="#dataAuthDiv">
                    <a href="#">数据权限 </a>
                </h4>
            </div>
            <div id="dataAuthDiv" class="panel-collapse collapse">
            </div>
        </div>
    </div>
    <!-- 权限模块列表 end -->
    </body>
</html>
