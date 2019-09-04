<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="common/common.jsp" />

<html>
<head>
    <script src="${ctx}/static/js/index.js"></script>
</head>
<body>
<%--<div class="container left10 top10 col-lg-3 hide" >
    <div class="row">
        <input id="fileLoader" name="fileLoader" type="file" class="file" >
    </div>
</div>--%>

<div class="container left10 top10 col-lg-12" >
    <div class="row ">
        <div class="col-lg-4">
        <!-- 上传 Excel 功能 begin -->
            <!-- 输入 Excel 路径 -->
            <span class="label label-primary"> 请输入 Excel 路径 example: /Users/jingshuo/Documents/rbac/template/AuthTest.xlsx</span>
            <form id="fileForm" method="get" >
                <input id="filePath" name="filePath" class="form-control top10" type="text" >
                <input id="fileUpload"  class="btn-primary btn-block top10" type="button" value="上传"  onclick="filePathHandler('upload')">
                <input id="fileConvert"  class="btn-primary btn-block top10" type="button" value="转换json" onclick="filePathHandler('convert')" >
            </form>
        </div>
    </div>
    <!-- 上传 Excel 功能 end -->

    <!-- 查看权限列表 begin -->
    <div class="row">
        <ul id="serviceList" class="col-lg-4 nav nav-pills nav-stacked"></ul>
    </div>
    <!-- 查看权限列表 end -->

    <!-- 权限模块列表 begin -->
    <div class="panel-group" id="panelParent">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h4 id="funcAuthHeader" class="panel-title" data-toggle="collapse" data-parent="#panelParent" href="#funcAuthDiv">
                    <a href="#">功能权限 </a>
                </h4>
            </div>
            <div id="funcAuthDiv" class="panel-collapse collapse in">
 <%--               <div>
                    <a class="list-group-item" href="#" onclick="getAuthClassElement(this)">SVN</a>
                    <div id="test">
                        <table id="funcAuthTable"></table>
                    </div>
                </div>
                <div class="list-group-item">
                    <a class="active" href="#" onclick="getAuthClassElement(this)">GIT</a>
                    <table id="funcAuthTable1"></table>
                </div>--%>
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



<script>



</script>
</body>
</html>
