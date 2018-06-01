
/**
 * dataTables 生成对象
 * @type {{initFormDataTable, packDataTableParam}}
 *
 */
var dataTableObj = (function () {

    var dataTable;

    function initFormDataTable() {
        var dataTable = $('#user-info-data-table').DataTable( {
            serverSide: true,
            lengthChange: false,
            searching: false,
            ordering:false,
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
                    }
                },

                { data: "userName" ,
                    "render": function (data, type, row, meta) {
                        return data;
                    }
                },
                {
                    data: "sex",
                    "render": function (data, type, row, meta) {
                        if (data == 1) {
                            return '男';
                        } else if (data == 2) {
                            return '女';
                        };
                    }
                },
                {
                    data: "birthday",
                    "render": function (data, type, row, meta) {
                        return data;
                    }
                },
                {
                    data: "idCard",
                    "render": function (data, type, row, meta) {
                        return data;
                    }
                },
                {
                    data: "phone",
                    "render": function (data, type, row, meta) {
                        return data;
                    }
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
                    }
                },
                {
                    data: "createdTime",
                    "render": function (data, type, row, meta) {
                        return new XDate(data).toString("yyyy-MM-dd HH:mm:ss");
                    }
                },
                {
                    data: "userPerformance.performanceScore",
                    "render": function (data, type, row, meta) {

                        var INPUT_TEMPL_SCORE = ''
+ '<input type="text" older-val="{olderVal}" user-performance-id="{userPerformanceId}" class="user-performance-performance-score" id="user-performance-performance-score" value="{performanceScore}">';


                        if(!data){
                            return "当月绩效信息尚未生成，请稍等！";
                        } else {
                            return INPUT_TEMPL_SCORE.replace(/\{performanceScore\}/g, data)
                                .replace(/\{olderVal\}/g,data)
                                .replace(/\{userPerformanceId\}/g, row.userPerformance.performanceId);
                        }
                    }
                },
                {
                    data: "userPerformance.performanceContent",
                    "render": function (data, type, row, meta) {
                        // return row.userPerformance.performanceContent;
                        // return "精彩马上呈现";
                        var INPUT_TEMPL_CONTENT = '' +
'<input type="text" older-val="{olderVal}" user-performance-id="{userPerformanceId}" class="user-performance-performance-content" id="user-performance-performance-content" value="{performanceContent}">';

                        if(!data){
                            return "当月绩效信息尚未生成，请稍等！";
                        } else {
                            return INPUT_TEMPL_CONTENT.replace(/\{performanceContent\}/g, data)
                                .replace(/\{olderVal\}/g, data)
                                .replace(/\{userPerformanceId\}/g, row.userPerformance.performanceId);
                        }
                    }
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
                // $(".checkall").attr("checked", false);
            }
        } );
        return dataTable;
    }

    function packDataTableParam(boxParam){

        // 目前不排序 不搜索
        //var orderField = boxParam.columns[boxParam.order[0].column].data;
        //var orderDir = boxParam.order[0].dir;
        var param = {
            "dTParam.draw": boxParam.draw,
            "dTParam.pageNo": boxParam.start/boxParam.length+1,
            "dTParam.pageSize": boxParam.length,
            //"orderField": orderField,
            //"orderDir": orderDir,
            // 封装其他参数
            "param.performanceTime" : $("#user-info-performance-time").val(),
            //"formId": $.trim($("#formId").val()),
            //"formName": $.trim($("#formName").val()),
            //"formCreateUser": $.trim($("#formCreateUser").val())
        };
        return param;
    }

    return {

        "packDataTableParam" : packDataTableParam,
        "initFormDataTable" : initFormDataTable,
    };
})();

var ListObj = (function () {

    var ListObj = function () {
        this.init();
    };

    /**
     * 初始化
     */
    ListObj.prototype.init = function () {
        dataTableObj.initFormDataTable();
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

        //搜索监听
        $("#user-info-btn-search").click(function(){
            dataTable.ajax.reload();
        });

        //修改分数监听
        $("#user-info-data-table").delegate(".user-performance-performance-score", "blur", updateScore);
        //修改评价监听
        $("#user-info-data-table").delegate(".user-performance-performance-content", "blur", updateContent);


        function updateScore() {
            console.log("监听到修改分数");
            console.log("新的值：" + $(this).val() + "旧值：" + $(this).attr("older-val"));
            var newScore = $(this).val();
            var olderScore = $(this).attr("older-val");
            var userPerformanceId = $(this).attr("user-performance-id");
            if(newScore == olderScore){
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
                        $("#info-msg").text(data.msg);
                    } else {
                        $("#info-msg").text(data.msg);
                    }
                }
            });
        };
        function updateContent() {
            console.log("监听到修改分数");
            console.log("新的值：" + $(this).val() + "旧值：" + $(this).attr("older-val"));
            var newContent = $(this).val();
            var olderContent = $(this).attr("older-val");
            var userPerformanceId = $(this).attr("user-performance-id");
            if(newContent == olderContent){
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
                        $("#info-msg").text(data.msg);
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
