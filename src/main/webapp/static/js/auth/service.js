$(function(){
    // 初始化权限模块折叠功能
    initAuthClassList();
});


/**
 * 初始化权限模块折叠功能
 */
function initAuthClassList() {
    // 初始化展开功能权限
    getAuthClassList(1);

    $('#funcAuthDiv').on('show.bs.collapse', function () {
        getAuthClassList(1);
    });

    $('#dataAuthDiv').on('show.bs.collapse', function () {
        getAuthClassList(2);
    });
}

/**
 * 获取指定 service 下权限模块
 *
 * authType     权限类型  1: 功能权限  2: 数据权限
 *
 **/
function getAuthClassList(authType)
{
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
                title: 'Rbac Name'
            }, {
                field: 'rbacKey',
                title: 'Rbac Key'
            }, {
                field: 'createdAt',
                title: 'Last Updated At'
            } ],
            height: 400,
        });

        $(_this).addClass("active");
        $tableDiv.show();
    }
}