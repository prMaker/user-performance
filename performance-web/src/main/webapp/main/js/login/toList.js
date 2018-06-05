

/**
 * dataTables 生成对象
 * @type {{initFormDataTable, packDataTableParam}}
 *
 */
var dataTableObj = (function () {

    /**
     * 初始化dataTable
     * @returns {*|jQuery}
     */
    function initFormDataTable(drawCallback) {
        var dataTable = $('#login-list-table').DataTable( {
            serverSide: true,
            lengthChange: false,
            searching: false,
            ordering:true,
            "order": [[ 0, "asc" ]],
            dom:
            "<<tr>>" +
            "<'row'<'col-sm-6'i><'col-sm-6'p>>",
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
                            var INPUT_TEMPL_MAKE_PER = '<a href="javascript:;" user-info-id="{userInfoId}" class="makePerformance">初始化绩效</a>';
                            return INPUT_TEMPL_MAKE_PER.replace(/\{userInfoId\}/g, row.userInfoId);
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