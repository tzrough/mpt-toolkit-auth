<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <script src="${ctx}/static/js/auth/service.js"></script>
</head>
<body>
    <div id="service-content">
        <!-- 内容 头部 -->
        <div id="contentHeader" class="row" style="height: 100px">
            <button id="exportAll" class="btn btn-primary" onclick="exportAll()" >
                <i class="glyphicon glyphicon-export"></i> 导出
            </button>
        </div>
        <!-- 内容 数据 -->
        <div id="contentBody" class="row">
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
        </div>
    </div>

    <div id="addModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title"></h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Rbac Name</label>
                        <input id="authElementName" type="text" class="form-control" placeholder="">
                    </div>
                    <div class="form-group">
                        <label>Rbac Key</label>
                        <input id="rbacKey" type="text" class="form-control" name="" placeholder="">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary submit" onclick="addRow()">Submit</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</body>
</html>

