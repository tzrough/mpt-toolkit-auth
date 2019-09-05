$(function(){
    // 初始化页面
    initPage();
});

/**
 * 初始化页面
 **/
function initPage() {
    // 加载头部
    loadHeader();
    // 加载左侧导航
    loadLeftNav();
}

/**
 * 加载头部
 **/
function loadHeader() {
    $("#header").load("/view/common/head");
}

/**
 * 加载左侧导航
 **/
function loadLeftNav() {
    $("#leftNav").load("/view/common/left");
}


/** 加载权限创建页面 **/
function loadCreatePage() {
    emptyContent();
    $("#nav-left li").removeClass("active");
    $("#authCreate").addClass("active");
    $("#contentHeader").load("/view/auth/create");
}


/** 加载权限模块列表 **/
function loadAuthServiceList() {
    var html = "";
    $.ajax({
        url: "/auth-service",
        dataType:"json",
        success:function(authClassList){
            $.each(authClassList, function(i,item){
                // 所属服务名
                var serviceName = item.authServiceName;

                html += "<li><a onclick='loadAuthServicePage(this)'>" + serviceName + "</a></li>";
            });
        },
        error:function(){

        }
    });

    // 慢进慢出效果
    $("#serviceList").fadeOut(100, function(){
        $("#serviceList").html(html).fadeIn().delay(500);
    });
}

/** 加载权限模块页面 **/
function loadAuthServicePage(_this) {
    emptyContent();
    $("#nav-left li").removeClass("active");
    $(_this).parents().addClass("active");
    $("#contentBody").load("/view/auth/service");
}