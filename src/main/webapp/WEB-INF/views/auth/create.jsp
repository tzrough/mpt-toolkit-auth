<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="${ctx}/static/js/auth/create.js"></script>
</head>
<body>
<!-- 上传 Excel 功能 begin -->
<div class="row col-lg-4">
    <!-- 输入 Excel 路径 -->
    <span class="label label-primary"> 请输入 Excel 路径 example: /Users/jingshuo/Documents/rbac/template/AuthTest.xlsx</span>
    <form id="fileForm" method="get" >
        <input id="filePath" name="filePath" class="form-control top10" type="text" >
        <input id="fileUpload"  class="btn-primary btn-block top10" type="button" value="上传"  onclick="filePathHandler('upload')">
        <input id="fileConvert"  class="btn-primary btn-block top10" type="button" value="转换json" onclick="filePathHandler('convert')" >
    </form>
</div>
<!-- 上传 Excel 功能 end -->
</body>
</html>
