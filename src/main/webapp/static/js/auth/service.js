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
            '<button id="edit" class="btn btn-success" onclick="edit(\'' + tableId + '\', this, false)">' +
                'Enable-Edit' +
            '</button>' +
            '<button id="add" class="btn btn-info hide"  onclick="popAddModal(\'' + tableId + '\')">' +
                '<i class="glyphicon glyphicon-plus"></i> Add' +
            '</button>' +
            '<button id="save" class="btn btn-success hide" onclick="save(\'' + tableId + '\', this)">' +
                ' <i class="glyphicon glyphicon-ok"></i> Save' +
            '</button>' +
        '</div>'
    );

    var condition = {};
    condition.id = $(_this).attr("classId");
    condition.authTableName = $(_this).attr("tableName");

    // 点击状态
    if($(_this).hasClass("active")) {
        $(_this).removeClass("active");
        $tableDiv.hide();
        // 未点击状态
    } else {
        var url = "/auth-class/element";
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
                }},{
                field: 'authElement',
                title: 'Auth Point',
            }, {
                field: 'authElementName',
                title: 'Rbac Name',
                editable: {
                    disabled: true
                }
            }, {
                field: 'rbacKey',
                title: 'Rbac Key',
                editable: {
                    disabled: true
                }
            }, {
                field: 'createdAt',
                title: 'Last Updated At'
            }, {
                field: 'operate',
                title: 'Remove',
                align: 'center',
                visible: false,
                events: {
                    'click .remove': function (e, value, row, index) {
                        $table.bootstrapTable('remove', {
                            field: 'id',
                            values: [row.id]
                        })
                    }
                },
                formatter: operateFormatter,
            }],
            height: 430,
            onEditableSave: function(field, row, rowIndex, oldValue, $el) {
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

    var authElement = $("#authElement").val();
    var authElementName = $("#authElementName").val();
    var rbacKey = $("#rbacKey").val();

    if(authElement == "" || authElementName == "" || rbacKey == "") {
        alert("no empty filed allowed");
        return;
    }

    $table.bootstrapTable('insertRow', {
        index: 0,
        row: {
            authElementName: authElementName,
            rbacKey: rbacKey,
            authElement: authElement,
            createdAt: ""
        }
    })

    $("#addModal").modal('hide');
}

/** 启用编辑 **/
function edit(tableId, _this, save) {
    var content = $(_this).html().trim();
    // 编辑按钮状态切换
    if(content == "Enable-Edit") {
        $(_this).html("Disable-Edit");
        $(_this).removeClass("btn-success").addClass("btn-danger");
        $(_this).siblings().removeClass("hide");
        $("#" + tableId).bootstrapTable('showColumn', 'operate');
        $('#' + tableId + ' .editable').editable('enable');
    }else {
        // 若不是通过保存来关闭编辑
        if(!save) {
            bootbox.confirm({
                size: "small",
                message: "Are you sure not to save?",
                /* result is a boolean; true = OK, false = Cancel*/
                callback: function(yes){
                    if(yes) {
                        $(_this).html("Enable-Edit");
                        $(_this).removeClass("btn-danger").addClass("btn-success");
                        $(_this).siblings().addClass("hide");
                        $("#" + tableId).bootstrapTable('hideColumn', 'operate');
                    }
                }
            })
        }else {
            $(_this).html("Enable-Edit");
            $(_this).removeClass("btn-danger").addClass("btn-success");
            $(_this).siblings().addClass("hide");
            $("#" + tableId).bootstrapTable('hideColumn', 'operate');
            $('#' + tableId + ' .editable').editable('toggleDisabled');
        }
    }
}

/** 保存修改的内容 **/
function save(tableId, _this) {
    var data = $('#' + tableId).bootstrapTable('getData');
    var url = "/auth-class/save";
    // 从 authClass 后开始截取
    var authClassId = tableId.substring(9, tableId.indexOf("Table"));

    /** 添加权限模块id **/
    $.each(data,function (index, item) {
        item.authClassId = authClassId;
    })

    if($.isEmptyObject(data)) {
        alert("please add content");
        return;
    }
    // 保存表格数据
    $.ajax({
        url: url,
        type: "post",
        contentType : "application/json",
        data: JSON.stringify(data),
        dataType:"json",
        success:function(newAuthClass){
            var condition = {};
            condition.id = newAuthClass.id;
            condition.authTableName = newAuthClass.authTableName;

            toastr.success('data save succeed');
            edit(tableId, $(_this).siblings("#edit"), true);
            // 重新加载表格数据
            $.ajax({
                url: "/auth-class/element",
                type: "post",
                contentType : "application/json",
                data: JSON.stringify(condition),
                success:function(data){
                    $('#' + tableId).bootstrapTable("load",data);
                },
                error:function(e){
                }});
        },
        error:function(e){
            toastr.error('data save failed');
        }});
}


/** 表格行操作 **/
function operateFormatter(value, row, index) {
    return [
        '<a class="remove" href="javascript:void(0)" title="Remove" >',
        '<i class="fa fa-trash"></i>',
        '</a>'
    ].join('')
}



