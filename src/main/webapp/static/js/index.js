$(function(){
    // 获取所有权限列表
    getAuthServiceList();
    // 初始化权限模块折叠功能
    initAuthClassList();
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

                // 默认第一个选中
                if(i == 0)
                    //$("#serviceList").append("<li class='active'><a onclick='getAuthClassList(\""+serviceName+"\","+authType+")'>" + serviceName + "</a></li>");
                    $("#serviceList").append("<li class='active'><a onclick='getAuthClassList(this," + authType + ")'>" + serviceName + "</a></li>");
                else
                    $("#serviceList").append("<li><a onclick='getAuthClassList(this," + authType + ")'>" + serviceName + "</a></li>");
            });
        },
        error:function(){

        }
    });

}


/**
 * 初始化权限模块折叠功能
 */
function initAuthClassList() {

    $('#funcAuthDiv').on('show.bs.collapse', function () {
        getAuthClassList(null, 1);
    });

    $('#dataAuthDiv').on('show.bs.collapse', function () {
        getAuthClassList(null, 2);
    });

}


/**
 * 获取指定 service 下权限模块
 *
 * authType     权限类型  1: 功能权限  2: 数据权限
 *
 **/
function getAuthClassList(_this, authType)
{
    if(_this != null) {
        $("#serviceList").children().removeClass("active");
        $(_this).parents().addClass("active");
    }

    var serviceName = $("#serviceList").children(".active").find("a").html();
    var url = "/auth-service/auth-class";

    var condition = {};
    condition.authServiceName = serviceName;
    condition.authType = authType;
    condition.isDeleted = 0;

    $.ajax({
        url: url,
        type: "post",
        data: JSON.stringify(condition),
        contentType : "application/json",
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
                    '<a class="list-group-item" href="#" onclick="getAuthClassElement(this)" classId=' + authClassId + ' tableName=' + item.authTableName + ' >' + item.authChName + '</a>' +
                    '<div id=' + divId + '>' +
                        '<table id=' + tableId + '> </table>' +
                    '</div>' +
                '</div>';

            });

            switch (authType) {
                // 功能权限
                case 1 :
                    $('#funcAuthDiv').empty();
                    $('#dataAuthDiv').empty();
                    $('#funcAuthDiv').append(authClassListHtml);
                    $('#funcAuthDiv').collapse('show');
                    break;
                // 数据权限
                case 2 :
                    $('#funcAuthDiv').empty();
                    $('#dataAuthDiv').empty();
                    $('#dataAuthDiv').append(authClassListHtml);
                    $('#dataAuthDiv').collapse('show');
                    break;
            }
        },
        error:function(){

        }
    });
}


/**
 * 获取指定权限模块下权限点
 **/
function getAuthClassElement(_this) {
 /*   var data = [
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
    ]*/

    var $tableDiv = $($(_this).next());
    var $table = $($(_this).next().children("table"));

    var url = "/auth-class/element"
    var condition = {};
    condition.id = $(_this).attr("classId");
    condition.authTableName = $(_this).attr("tableName");

    // 点击状态
    if($(_this).hasClass("active")) {
        $(_this).removeClass("active");
        $tableDiv.hide();
    // 未点击状态
    } else {
        $table.bootstrapTable({
            url: url,
            method: "post",
            queryParams: function (params) {
                return condition;
            },
            pagination: false,
            columns: [{
            title: 'Number',
            formatter: function (value, row, index) {
                return index+1;
            }}, {
                field: 'authElementName',
                title: 'Auth Element Name'
            }, {
                field: 'authElement',
                title: 'Auth Element'
            }, {
                field: 'createdAt',
                title: 'Last Updated At'
            } ],
            height: 400,
        });

        $(_this).addClass("active");
        $tableDiv.show();

    /*    $.ajax({
            url: url,
            type: "post",
            data: JSON.stringify(condition),
            contentType : "application/json",
            dataType:"json",
            success:function(authClassElementList){
                $table.bootstrapTable({
                    data: authClassElementList,
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

            },
            error:function(){

            }
        });*/


    }
}