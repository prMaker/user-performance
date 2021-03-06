

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
                url: "/userLogin/listData?loginId=" + user_login_id,
                type: "GET",
                data:function(boxParam){
                    var tmpObj = packDataTableParam(boxParam);
                    return tmpObj;
                }
            },
            columns: [
                { data: "loginId" ,
                    "render": function (data, type, row, meta) {
                        return data;
                    },
                    "orderable": true
                },
                { data: "loginName" ,
                    "render": function (data, type, row, meta) {
                        return data;
                    },
                    "orderable": false
                },
                {
                    data: "password",
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
     * @returns {{"dataTableParam.draw": *, "dataTableParam.pageNo": number, "dataTableParam.pageSize", orderField: string, orderDir, "userInfoPageParam.performanceTime": *|{}|jQuery}}
     */
    function packDataTableParam(boxParam){
        var param = {
            "dataTableParam.draw": boxParam.draw,
            "dataTableParam.pageNo": boxParam.start/boxParam.length+1,
            "dataTableParam.pageSize": boxParam.length,
            // "dataTableParam.orderField": boxParam.columns[0].data,
            // "dataTableParam.orderDir": boxParam.order[0].dir,
            // 封装其他搜索参数
        };
        console.log(boxParam);
        console.log(param);
        return param;
    }

    return {
        "packDataTableParam" : packDataTableParam,
        "initFormDataTable" : initFormDataTable
    };
})();

dataTableObj.initFormDataTable();