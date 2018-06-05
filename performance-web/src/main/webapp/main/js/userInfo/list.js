
/**
 * dataTables 生成对象
 * @type {{initFormDataTable, packDataTableParam}}
 *
 */
var dataTableObj = (function () {

    var dataIsLocked = false;// 确认所有数据已经锁定
    /**
     * 初始化dataTable
     * @returns {*|jQuery}
     */
    function initFormDataTable(drawCallback) {
        var dataTable = $('#user-info-data-table').DataTable( {
            serverSide: true,
            lengthChange: false,
            searching: false,
            ordering:true,
            "order": [[ 7, "desc" ]],
            dom:
                "<<tr>>" +
                "<'row'<'col-sm-6'i><'col-sm-6'p>>",
            //order: [[3, "desc"]],
            language: { //定义中文
                "search": "请输入书籍名称:",
                "zeroRecords": "没有匹配的数据",
                "lengthMenu": "显示 _MENU_ 条数据",
                "info": "显示从 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
                "infoFiltered": "(从 _MAX_ 条数据中过滤得来)",
                "loadingRecords": "加载中...",
                "processing": "处理中...",
                "paginate": {
                    "first": "首页",
                    "last": "末页",
                    "next": "下一页",
                    "previous": "上一页"
                }
            },
            ajax: {
                url: "/userInfo/listData?loginId=" + user_login_id,
                type: "POST",
                data:function(boxParam){
                    var tmpObj = packDataTableParam(boxParam);
                    return tmpObj;
                }
            },
            columns: [
                { data: "userInfoId" ,
                    "render": function (data, type, row, meta) {
                        return data;
                    },
                    "orderable": false
                },

                { data: "userName" ,
                    "render": function (data, type, row, meta) {
                        return data;
                    },
                    "orderable": false
                },
                {
                    data: "sex",
                    "render": function (data, type, row, meta) {
                        if (data == 1) {
                            return '男';
                        } else if (data == 0) {
                            return '女';
                        };
                    },
                    "orderable": false
                },
                // {
                //     data: "birthday",
                //     "render": function (data, type, row, meta) {
                //         return data;
                //     },
                //     "orderable": false
                // },
                {
                    data: "idCard",
                    "render": function (data, type, row, meta) {
                        return data;
                    },
                    "orderable": false
                },
                {
                    data: "phone",
                    "render": function (data, type, row, meta) {
                        return data;
                    },
                    "orderable": false
                },
                {
                    data: "dispostion",
                    "render": function (data, type, row, meta) {
                        if (data == 99) {
                            return '管理员';
                        } else if (data == 1) {
                            return '普通员工';
                        } else if (data == 2) {
                            return '负责人';
                        } else if (data == 3) {
                            return '中心经理';
                        } else if (data == 4) {
                            return '部门经理';
                        }
                    },
                    "orderable": false
                },
                {
                    data: "createdTime",
                    "render": function (data, type, row, meta) {
                        return new XDate(data).toString("yyyy-MM-dd HH:mm:ss");
                    },
                    "orderable": false
                },
                {
                    data: "userPerformance.performanceScore",
                    "render": function (data, type, row, meta) {
                        if(!row.userPerformance || !row.userPerformance.performanceId) {// 没有生成审核数据
                            return "尚未生成审核初始化数据！";
                        }
                        if(!row.permissionToFix){// 没有权限修改
                            return data;
                        }

                        /*----------------------------*/
                        /* NOTICE 不合适（但由于锁定是由异步数据是否支持决定，所以只能放此）  暂时放这里*/
                        if(row.userPerformance.isLocked == 1 || dataIsLocked){
                            dataIsLocked = true;
                        }
                        /*-----------------------------*/
                        var INPUT_TEMPL_SCORE = '' + '<input type="text" older-val="{olderVal}" user-info-id="{userInfoId}" user-performance-id="{userPerformanceId}" class="user-performance-performance-score" id="user-performance-performance-score" value="{performanceScore}">';
                        return INPUT_TEMPL_SCORE.replace(/\{performanceScore\}/g, !!data ? data : "")
                            .replace(/\{olderVal\}/g,!!data ? data : "")
                            .replace(/\{userPerformanceId\}/g, row.userPerformance.performanceId)
                            .replace(/\{userInfoId\}/g, row.userInfoId);
                    },
                    "orderable": true
                },
                {
                    data: "userPerformance.performanceContent",
                    "render": function (data, type, row, meta) {
                        if(!row.userPerformance || !row.userPerformance.performanceId) {// 没有生成审核数据
                            row.makePerformance = true;
                            return "尚未生成审核初始化数据！";
                        }
                        if(!row.permissionToFix){// 没有权限修改
                            return data;
                        }
                        var INPUT_TEMPL_CONTENT = '' +  '<input type="text" older-val="{olderVal}" user-info-id="{userInfoId}" user-performance-id="{userPerformanceId}" class="user-performance-performance-content" id="user-performance-performance-content" value="{performanceContent}">';
                        return INPUT_TEMPL_CONTENT.replace(/\{performanceContent\}/g, !!data ? data : "")
                            .replace(/\{olderVal\}/g, !!data ? data : "")
                            .replace(/\{userPerformanceId\}/g, row.userPerformance.performanceId)
                            .replace(/\{userInfoId\}/g, row.userInfoId);
                    },
                    "orderable": false
                },
                {
                    data: "action",
                    "render" : function (data, type, row, meta) {
                        if(row.makePerformance){
                            // var INPUT_TEMPL_MAKE_PER = '<a href="javascript:;" user-info-id="{userInfoId}" class="makePerformance">初始化绩效</a>';
                            // return INPUT_TEMPL_MAKE_PER.replace(/\{userInfoId\}/g, row.userInfoId);
                            return "";
                        }
                    },
                    "orderable": false
                }
            ],
            columnDefs: [
                {
                    "targets": "_all",
                    "defaultContent": "",//空列默认值
                    "className": "dt-column"
                }
            ],
            drawCallback: function(settings) {
                if(!!drawCallback){
                    drawCallback(dataIsLocked);
                }
            }
        } );
        return dataTable;
    }

    /**
     * 组装搜索数据
     * @param boxParam
     * @returns {{"dTParam.draw": *, "dTParam.pageNo": number, "dTParam.pageSize", "param.performanceTime": *|{}|jQuery}}
     */
    function packDataTableParam(boxParam){

        // 目前不排序 不搜索
        var orderField = boxParam.columns[7].data;
        var orderDir = boxParam.order[0].dir;
        var param = {
            "dataTableParam.draw": boxParam.draw,
            "dataTableParam.pageNo": boxParam.start/boxParam.length+1,
            "dataTableParam.pageSize": boxParam.length,
            "orderField": orderField,
            "orderDir": orderDir,
            // 封装其他参数
            "userInfoPageParam.performanceTime" : $("#user-info-performance-time").val(),
        };
        console.log(boxParam);
        console.log(param);
        return param;
    }

    return {
        "packDataTableParam" : packDataTableParam,
        "initFormDataTable" : initFormDataTable,
        "dataIsLocked" : dataIsLocked
    };
})();

/**
 * 时间插件
 */
var dataPicker = (function () {

    $("#user-info-check-performance-time").datetimepicker({
        language:  'zh-CN',//选择语言类型
        format:"yyyy-MM",      //格式化日期
        startView: 3,//默认视图为年视图
        minView: 3,//设置只显示到月份
        autoclose: true,
    }).on("changeDate",function(e){
        var nowDate = DateFormat.format(e.date, "yyyyMM");
        $("#user-info-performance-time").val(nowDate);
    });

    // 初始化当前时间
    $("#user-info-check-performance-time").val(DateFormat.format(new Date(), "yyyy-MM"));
    var nowDate = DateFormat.format(new Date(), "yyyyMM");
    $("#user-info-performance-time").val(nowDate);

})();

/**
 * 页面控制类
 */
var ListObj = (function () {

    var ListObj = function () {
        this.init();
    };

    /**
     * 初始化
     */
    ListObj.prototype.init = function () {
        var _this = this;
        // 表格初始化
        this.dataTable = dataTableObj.initFormDataTable(dataTableCallback);
        this.searchDataReload = searchDataReload;
        this.searchDataReload();
        this.setOption();

        function searchDataReload() {
            /*获取是否显示添加绩效按钮*/
            var searchDate = $("#user-info-performance-time").val();
            var date = new Date(Date.parse(searchDate));
            /*如果搜索时间大于当前时间 则直接去掉添加绩效审核内容*/
            if(DateFormat.compareDate(date, new Date()) <= 0){
                $("#user-performance-add-div").hide();
            } else {
                $.ajax({
                    url:"/userInfo/performanceToAdd",
                    type:"post",
                    dataType:"json",
                    data:{
                        performanceTime:searchDate,
                        loginId:user_login_id
                    },
                    success:function (data) {
                        console.log("返回结果：" + data);
                        if(data.success && data.data){
                            $("#user-performance-add-div").show();
                        }
                    }
                });
            }
            /*获取显示绩效信息数据*/
            $.ajax({
                url:"/userInfo/getUserPerformance",
                type:"post",
                dataType:"json",
                data:{
                    performanceTime:$("#user-info-performance-time").val(),
                    loginId:user_login_id
                },
                success:function (res) {
                    console.log("返回结果：" + res);
                    if(res.success && !!res.data){
                        var PERFORMANCE_TEMPL = ""
                            + '您的绩效信息为：<br>'
                            + '绩效分数：{performanceScore}<br>'
                            + '绩效内容：{performanceContent}<br>';
                        $("#performance-content")
                            .html(PERFORMANCE_TEMPL.replace(/\{performanceScore\}/g, res.data.performanceScore)
                            .replace(/\{performanceContent\}/g
                                , !!res.data.performanceContent ? res.data.performanceContent : ''));
                    } else {
                        $("#performance-content").html("您的绩效尚未生成");
                    }
                }
            });
        }
        // 公共方法
        function dataTableCallback(dataIsLocked) {
            if(dataIsLocked){// 锁定按钮置空
                $("#user-info-lock-div").empty();
            }
        }
    };

    /**
     * 设置参数
     */
    ListObj.prototype.setOption = function () {
        this.render();
    };

    ListObj.prototype.render = function () {
        this.listen();

    };

    /**
     * 添加监听事件
     */
    ListObj.prototype.listen = function () {
        var _this = this;

        //修改分数监听
        $("#user-info-data-table").delegate(".user-performance-performance-score", "blur", updateScore);
        //修改评价监听
        $("#user-info-data-table").delegate(".user-performance-performance-content", "blur", updateContent);
        //锁定数据监听
        $("#user-info-lock").click(lockPerformance);
        //添加审核数据
        $("#user-performance-add").click(performanceAdd);
        //生成审核数据
        $("#user-info-data-table").delegate(".makePerformance", "click", makePerformance);
        //搜索监听
        $("#user-info-btn-search").click(function(){
            _this.dataTable.ajax.reload();
            _this.searchDataReload();
        });

        // 公共方法////////////////// TODO 生成单个审核数据
        function makePerformance() {
            var userInfoId = $(this).attr("user-info-id");
            $.ajax({
                url:"/",
                type:"POST",
                dataType:"json",
                data:{},
                async:true,
                success:function () {
                }
            });
        }
        function performanceAdd() {
            $.ajax({
                url:"/userPerformance/setCurrPerformance",
                type:"GET",
                dataType:"json",
                async:false,
                data:{
                    performanceTime: $("#user-info-performance-time").val(),
                    loginId:user_login_id
                },
                success:function (data) {
                    if(!!data && data.success){
                        _this.dataTable.ajax.reload();
                        $("#user-performance-add-div").hide();
                    }
                }
            });
        }
        function lockPerformance() {
            var user_info_id = $(this).attr("user-info-id");
            console.log("user_info_id:" + user_info_id);
            $.ajax({
                url:"/userPerformance/lockPerformance",
                type:"POST",
                async: true,
                data:{
                    performanceTime:$("#user-info-performance-time").val(),
                    loginId:user_login_id
                },
                dataType:"json",
                success: function (data) {
                    if(data.success){
                        $("#info-msg").text("锁定数据成功！");
                        _this.dataTable.ajax.reload();
                    } else {
                        $("#info-msg").text(data.msg);
                    }
                }
            });
        }
        function updateScore() {
            console.log("监听到修改分数");
            console.log("新的值：" + $(this).val() + "旧值：" + $(this).attr("older-val"));
            var newScore = $(this).val();
            var olderScore = $(this).attr("older-val");
            var userPerformanceId = $(this).attr("user-performance-id");
            var userInfoId = $(this).attr("user-info-id");
            if(newScore == olderScore){
                return;
            }
            if(!newScore){
                alert("请录入分数！");
                return;
            }
            if(!(/^(-)?[0-9]+?/.test(newScore))){
                alert("请录入合适的分数！");
                return;
            }
            if(newScore>100 || newScore < -100){
                alert("请录入合理的绩效分数（绩效>0并且绩效<100）！");
                return;
            }
            $.ajax({
                url:"/userPerformance/doSave",
                type:"POST",
                data:{
                    "userPerformance.performanceScore": newScore,
                    "userPerformance.performanceId": userPerformanceId,
                    "loginId":user_login_id
                    },
                async:true,
                dataType:"json",
                success:function (data) {
                    if(data.success){
                        $("#info-msg").text("编号ID为：" + userInfoId+ "的员工绩效分数保存成功！");
                    } else {
                        $("#info-msg").text(data.msg);
                    }
                }
            });
        }
        function updateContent() {
            console.log("监听到修改内容");
            console.log("新的值：" + $(this).val() + "旧值：" + $(this).attr("older-val"));
            var newContent = $(this).val();
            var olderContent = $(this).attr("older-val");
            var userPerformanceId = $(this).attr("user-performance-id");
            var userInfoId = $(this).attr("user-info-id");
            if(newContent == olderContent){
                return;
            }
            if(newContent.length > 10){
                alert("录入数据超过限制！");
                return;
            }

            $.ajax({
                url:"/userPerformance/doSave",
                type:"POST",
                data:
                    {
                        "userPerformance.performanceId": userPerformanceId,
                        "userPerformance.performanceContent": newContent,
                        "loginId":user_login_id
                    },
                async:true,
                dataType:"json",
                success:function (data) {
                    if(data.success){
                        $("#info-msg").text("编号ID为：" + userInfoId+ "的员工绩效内容保存成功！");
                    } else {
                        $("#info-msg").text(data.msg);
                    }
                }
            });
        }
    };

    return ListObj;
})();

ListObj = new ListObj();
