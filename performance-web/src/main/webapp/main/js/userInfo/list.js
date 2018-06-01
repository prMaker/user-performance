
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
            /*
                <th>编号</th>
                <th>用户姓名</th>
                <th>用户性别</th>
                <th>用户生日</th>
                <th>用户身份证号</th>
                <th>用户电话</th>
                <th>用户职位</th>
                <th>用户创建时间</th>
                <th>用户当月评分</th>
                <th>用户当月绩效评价</th>
            */

            /*
                protected Long userInfoId;
                protected String userName;
                protected Integer sex;
                protected String birthday;
                protected String idCard;
                protected String phone;
                protected Integer dispostion;
                protected Timestamp createdTime;
                userPerformance.performanceScore;
                userPerformance.performanceContent;
                PermissionToFix
            */

            /*
                private Long performanceId;
                private Long userInfoId;
                private Integer isDeleted;
                private Long operateUserInfoId;
                private Timestamp createdTime;
                private Timestamp modifiedTime;
                private Integer isLocked;
                private Integer operateDisposition;
                private String performanceTime;
            */
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
                // 创建人
                //{
                //    data: "createUserName",
                //    "render": function (data, type, row, meta) {
                //        return data + '(' + row.createUser.replace("erp_", "") + ')';
                //    }
                //},
                /*
                protected Long userInfoId;
                protected String userName;
                protected Integer sex;
                protected String birthday;
                protected String idCard;
                protected String phone;
                protected Integer dispostion;
                protected Timestamp createdTime;
                userPerformance.performanceScore;
                userPerformance.performanceContent;
                PermissionToFix
                */
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
                        // console.log(
                        //     "data:" + data + "   " +
                        //     "type:" + type + "   " +
                        //     "row:" + row + "   " +
                        //     "meta:" + meta
                        // );
                        // return row["userPerformance"]["performanceScore"];
                        return "精彩马上呈现";
                    }
                },
                {
                    data: "userPerformance.performanceContent",
                    "render": function (data, type, row, meta) {
                        // return row.userPerformance.performanceContent;
                        return "精彩马上呈现";
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


    //搜索
    $("#user-info-btn-search").click(function(){
        dataTable.ajax.reload();
    });

    return {

        "packDataTableParam" : packDataTableParam,
        "initFormDataTable" : initFormDataTable,
    };
})();

console.log("123123");
dataTableObj.initFormDataTable();
