
/**
 * dataTables 生成对象
 * @type {{initFormDataTable, packDataTableParam}}
 *
 */
var dataTableObj = (function () {

    var dataIsLocked = false;
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
            /* TODO 添加默认排序设置*/
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
                    // var _data = KingUtil.addTimeStamp(tmpObj);  添加时间戳

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
                        } else if (data == 2) {
                            return '女';
                        };
                    },
                    "orderable": false
                },
                {
                    data: "birthday",
                    "render": function (data, type, row, meta) {
                        return data;
                    },
                    "orderable": false
                },
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

                        var INPUT_TEMPL_SCORE = ''
+ '<input type="text" older-val="{olderVal}" user-info-id="{userInfoId}" user-performance-id="{userPerformanceId}" class="user-performance-performance-score" id="user-performance-performance-score" value="{performanceScore}">';

                        /* NOTICE 不合适  暂时放这里*/
                        if(row.userPerformance.isLocked == 1){
                            console.log("修为改锁定");
                            dataIsLocked = true;
                        }

                        if(!row.permissionToFix){// 没有权限修改
                            return data;
                        }

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
                        // return row.userPerformance.performanceContent;
                        // return "精彩马上呈现";
                        var INPUT_TEMPL_CONTENT = '' +
'<input type="text" older-val="{olderVal}" user-info-id="{userInfoId}" user-performance-id="{userPerformanceId}" class="user-performance-performance-content" id="user-performance-performance-content" value="{performanceContent}">';

                        if(!row.permissionToFix){// 没有权限修改
                            return data;
                        }

                            return INPUT_TEMPL_CONTENT.replace(/\{performanceContent\}/g, !!data ? data : "")
                                .replace(/\{olderVal\}/g, !!data ? data : "")
                                .replace(/\{userPerformanceId\}/g, row.userPerformance.performanceId)
                                .replace(/\{userInfoId\}/g, row.userInfoId);
                    },
                    "orderable": false
                },
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
        var orderField = boxParam.columns[8].data;
        var orderDir = boxParam.order[0].dir;
        var param = {
            "dataTableParam.draw": boxParam.draw,
            "dataTableParam.pageNo": boxParam.start/boxParam.length+1,
            "dataTableParam.pageSize": boxParam.length,
            "orderField": orderField,
            "orderDir": orderDir,
            // 封装其他参数
            "userInfoPageParam.performanceTime" : $("#user-info-performance-time").val(),
            //"formId": $.trim($("#formId").val()),
            //"formName": $.trim($("#formName").val()),
            //"formCreateUser": $.trim($("#formCreateUser").val())
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
        this.dataTable = dataTableObj.initFormDataTable(dataTableCallback);
        function dataTableCallback(dataIsLocked) {
            console.log("锁定状态：" + _this.dataTable.dataIsLocked);
            if(dataIsLocked){
                $("#user-info-lock-div").empty();
            }
        }
        this.setOption();
    };

    /**
     * 设置参数
     */
    ListObj.prototype.setOption = function () {
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
        //搜索监听
        $("#user-info-btn-search").click(function(){
            console.log("开始搜索！");
            _this.dataTable.ajax.reload();
        });
        //锁定数据监听
        $("#user-info-lock").click(function () {
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
                    } else {
                        $("#info-msg").text(data.msg);
                    }
                }
            });
        });

        // 公共方法//////////////////
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
            if(newScore){
                if(!(/-?([1-9][0-9]{0,2},[0])?/.test(newScore))){
                    alert("请录入合理的绩效分数（绩效>0并且绩效<100）");
                    return;
                }
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
