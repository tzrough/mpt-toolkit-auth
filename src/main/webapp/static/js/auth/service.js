$(function(){
    // 初始化权限模块折叠功能
    initAuthClassList();

    $('#addModal').on('hidden.bs.modal', function () {
        $("#authElementName").val(null);
        $("#rbacKey").val(null);
    });
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
    var tableId = $table.attr("id");

    var toolbar = $(
        '<div id="toolbar" >' +
            '<button id="add" class="btn btn-info"  onclick="popAddModal(\'' + tableId + '\')">' +
                '<i class="glyphicon glyphicon-plus"></i> ADD' +
            '</button>' +
            '<button id="edit" class="btn btn-info" onclick="edit(\'' + tableId + '\')">' +
                '<i class="glyphicon glyphicon-pencil"></i> EDIT(Enable/Disable)' +
            '</button>' +
            '<button id="del" class="btn btn-danger" >' +
                '<i class="glyphicon glyphicon-minus"></i> DELETE' +
            '</button>' +
            '<button id="save" class="btn btn-success" >' +
                ' <i class="glyphicon glyphicon-ok"></i> SAVE' +
            '</button>' +
        '</div>'
    );

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
            toolbar: toolbar,
            pagination: false,
            showFullscreen: true,
            columns: [{
                title: 'Number',
                formatter: function (value, row, index) {
                    return index+1;
                }}, {
                field: 'authElementName',
                title: 'Rbac Name',
                editable: {
                    disabled:true
                }
            }, {
                field: 'rbacKey',
                title: 'Rbac Key',
                editable: {
                    disabled:true
                }
            }, {
                field: 'createdAt',
                title: 'Last Updated At'
            } ],
            height: 430,
            onEditableSave: function (field, row, oldValue, $el) {
                //$table.class(".editable").editable('toggleDisabled');
            }

        });

        $(_this).addClass("active");
        $tableDiv.show();
    }
}

/** 导出全部功能点的rbac json **/
function exportAll() {
    var serviceName = $("#serviceList").children(".active").find("a").html();
    window.location.href = "/auth-file/all-json/" + serviceName;
}

/** 弹出新增框 **/
function popAddModal(tableId) {
    $("#addModal").modal('show');
    $("#addModal").attr("tableId", tableId);

}


function addRow() {
    var tableId =  $("#addModal").attr("tableId");
    $table = $("#" + tableId);

    var authElementName = $("#authElementName").val();
    var rbacKey = $("#rbacKey").val();

    if(authElementName == "" || rbacKey == "") {
        alert("no empty filed allowed");
        return;
    }

    $table.bootstrapTable('insertRow', {
        index: 0,
        row: {
            authElementName: authElementName,
            rbacKey: rbacKey,
            createdAt: ""
        }
    })

    $("#addModal").modal('hide');
}


function edit(tableId) {
    $('#' + tableId + ' .editable').editable('toggleDisabled');
}


