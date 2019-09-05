/**
 * 文件操作
 **/
function filePathHandler(type) {
    var url = "/auth-file/";

    switch (type) {
        case "upload":
            url = url + "excel-upload";
            break;
        case "convert":
            url = url + "excel-json";
            break;
    }

    $("#fileForm").attr("action", url);
    $("#fileForm").submit();
}