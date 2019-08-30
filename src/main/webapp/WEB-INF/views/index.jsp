<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="common/common.jsp" />
<jsp:include page="plugins/fileinput.jsp"/>

<html>
<head>
</head>

<body>
<div class="container left10 top10 col-lg-3" >
    <div class="row">
        <input id="fileLoader" name="fileLoader" type="file" class="file" >
    </div>
</div>

<script>
    $("#fileLoader").fileinput({
        language : 'zh',
        uploadUrl : "",
        showUpload: true, //是否显示上传按钮
        showRemove : true, //显示移除按钮
        showPreview : true, //是否显示预览
        showCaption: false,//是否显示标题
        autoReplace : true,
        minFileCount: 0,
        uploadAsync: true,
        maxFileCount: 10,//最大上传数量
        browseOnZoneClick: true,
        msgFilesTooMany: "选择上传的文件数量 超过允许的最大数值！",
        enctype: 'multipart/form-data',
        // overwriteInitial: false,//不覆盖已上传的图片
        allowedFileExtensions : [ "jpg", "png", "gif" ],
        browseClass : "btn btn-primary", //按钮样式
        previewFileIcon : "<i class='glyphicon glyphicon-king'></i>"
    }).on("fileuploaded", function(e, data) {//文件上传成功的回调函数，还有其他的一些回调函数，比如上传之前...
    });


</script>
</body>
</html>
