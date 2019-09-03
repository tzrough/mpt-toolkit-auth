$(function(){
    getAuthServiceList();
});

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

/**
 * 获取所有权限列表
 **/
function getAuthServiceList() {
    $.ajax({
        url: "/auth-service",
        dataType:"json",
        success:function(authClassList){
            $.each(authClassList, function(i,item){
                // 所属服务名
                var serviceName = item.authServiceName;
                // 权限类型  1: 功能权限  2: 数据权限
                var authType = 1;

                $("#serviceList").append("<li><a onclick='getAuthClassList(\""+serviceName+"\","+authType+")'>" + serviceName + "</a></li>");
            });
        },
        error:function(){

        }
    });
}

/**
 * 获取指定 service 下权限模块
 *
 * serviceName  所属服务名
 * authType     权限类型  1: 功能权限  2: 数据权限
 *
 **/
function getAuthClassList(serviceName, authType)
{
    var url =  "/auth-service/" + serviceName + "/" + authType;

    $.ajax({
        url: url,
        dataType:"json",
        success:function(authClassList){
            var authClassListHtml = "";
            $.each(authClassList, function(i,item){
                // 权限模块 ID
                var authClassId = item.id;
                var divId = "authClass" + authClassId;
                var tableId = divId + "Table";

                authClassListHtml +=
                '<div>' +
                    '<a class="list-group-item" href="#" onclick="getAuthClassElement(this,' + divId + ')">' + item.authChName + '</a>' +
                    '<div id=' + divId + '>' +
                        '<table id=' + tableId + '> </table>' +
                    '</div>' +
                '</div>';

            });

            switch (authType) {
                // 功能权限
                case 1 :
                    $('#funcAuthDiv').empty();
                    $('#funcAuthDiv').append(authClassListHtml);
                // 数据权限
                case 2 :
                    $('#dataAuthDiv').empty();
                    $('#dataAuthDiv').append(authClassListHtml);
            }
        },
        error:function(){

        }
    });
}



/**
 * 获取指定权限模块下权限点
 **/
function getAuthClassElement(_this, divId) {
    var data = [
        {
            'id': 0,
            'name': 'Item 0',
            'price': '$0'
        },
        {
            'id': 1,
            'name': 'Item 1',
            'price': '$1'
        },
        {
            'id': 2,
            'name': 'Item 2',
            'price': '$2'
        },
        {
            'id': 3,
            'name': 'Item 3',
            'price': '$3'
        },
        {
            'id': 4,
            'name': 'Item 4',
            'price': '$4'
        },
        {
            'id': 5,
            'name': 'Item 5',
            'price': '$5'
        }
    ]

    var $tableDiv = $($(_this).next());
    var $table = $($(_this).next().children("table"));

    // 未点击状态
    if($(_this).hasClass("active")) {
        $(_this).removeClass("active");
        $tableDiv.hide();
        // 点击状态
    } else {
        $(_this).addClass("active");
        $table.bootstrapTable({
            data: data,
            pagination: true,
            height: 500,
            columns: [{
                field: 'id',
                title: 'Item ID'
            }, {
                field: 'name',
                title: 'Item Name'
            }, {
                field: 'price',
                title: 'Item Price'
            }, ]
        });
        $tableDiv.show();
    }
}