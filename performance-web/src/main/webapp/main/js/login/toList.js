

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
                    "orderable": true
                },
                { data: "idCard" ,
                    "render": function (data, type, row, meta) {
                        return data;
                    },
                    "orderable": false
                },
                {
                    data: "userName",
                    "render": function (data, type, row, meta) {
                        return data;
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
                    data: "action",
                    "render" : function (data, type, row, meta) {
                        var DELETE_TEMPLATE = '<a href="javascript:;" class="user-info-remove" user-info-id="{userInfoId}">删除用户</a>';
                        return DELETE_TEMPLATE.replace(/\{userInfoId\}/g, row.userInfoId);
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
            "dataTableParam.orderField": boxParam.columns[0].data,
            "dataTableParam.orderDir": boxParam.order[0].dir,
            // 封装其他搜索参数
            "userLoginPageParam.performanceTime" : $("#user-info-performance-time").val(),
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


var ListObj = function () {
    this.init();
};

ListObj.prototype = {
    init: function () {
        console.log("init ListObj!");
        this.dataTable = dataTableObj.initFormDataTable();
        this.setOption();
    },
    setOption: function () {
        this.listen();
    },
    listen: function () {
        var _this = this;
        // 删除用户监听
        $("#table-container").delegate(".user-info-remove", "click", removeUserInfo);

        function removeUserInfo() {
            var userInfoId = $(this).attr("user-info-id");
            $.ajax({
                url:"/userInfo/delete",
                type:"POST",
                dataType:"json",
                async:true,
                data:{
                    "userInfoId":userInfoId,
                    "loginId":user_login_id
                },
                success: function (data) {
                    if(data.success){
                        $("#info-msg").text(data.msg + " 用户ID: " + userInfoId);
                        _this.dataTable.ajax.reload();
                    } else {
                        alert(data.msg);
                    }
                }
            });
        }
    }
};

var listObj = new ListObj();