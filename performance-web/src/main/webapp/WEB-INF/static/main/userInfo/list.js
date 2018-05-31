var formTable;

/**
 * dataTables 生成对象
 * @type {{initFormDataTable, packDataTableParam}}
 *
 */
var dataTableObj = (function () {

    function initFormDataTable() {
        var table = $('#form-list').DataTable( {
            serverSide: true,
            lengthChange: false,
            searching: false,
            ordering:false,
            dom:
            "<<tr>>" +
            "<'row'<'col-sm-6'i><'col-sm-6'p>>",
            //order: [[3, "desc"]],
            language: KingConstant.zh_CN,
            ajax: {
                url: "/publish/listData.action",
                type: "POST",
                data:function(boxParam){
                    var tmpObj = packDataTableParam(boxParam);
                    var _data = KingUtil.addTimeStamp(tmpObj);

                    return tmpObj;
                }
            },
            columns: [
                { data: "id" ,
                    "render": function (data, type, row, meta) {
                        var txt = "<a class='text-u-l' href='/formMgr/form.action?type=1&formId=" + row.id + "'>";
                        txt += data;
                        txt += "</a>";
                        return txt;
                    }
                },

                { data: "name" ,
                    "render": function (data, type, row, meta) {
                        var txt = "";
                        txt += "<div class='text-cut-300'>";
                        if(row.readFlag == 0){
                            txt += '<i class="fa fa-envelope text-info-dk" aria-hidden="true"></i>&nbsp;&nbsp;';
                            txt += "<a class='text-u-l font-bold' href='/formMgr/form.action?type=1&formId=" + row.id + "&from=2' title='" + data + "'>";
                        } else {
                            txt += '<i class="fa fa-envelope-open-o text-muted" aria-hidden="true"></i>&nbsp;&nbsp;';
                            txt += "<a class='text-u-l' href='/formMgr/form.action?type=1&formId=" + row.id + "&from=2' title='" + data + "'>";
                        }
                        // txt += "<a class='text-u-l' href='/taskDefine/execute.action?formId=" + row.id + "' title='" + data + "'>";
                        txt += data;
                        txt += "</a></div>";
                        return txt;
                    }
                },
                // 创建人
                //{
                //    data: "createUserName",
                //    "render": function (data, type, row, meta) {
                //        return data + '(' + row.createUser.replace("erp_", "") + ')';
                //    }
                //},
                {
                    data: "type",
                    "render": function (data, type, row, meta) {
                        if (data == 1) {
                            return '质控管理类';
                        } else if (data == 2) {
                            return '财务结算类';
                        } else if (data == 3) {
                            return '资质审核类';
                        } else if (data == 4) {
                            return '任务类';
                        } else if (data == 5) {
                            return '通知类';
                        } else if (data == 6){
                            return '客服类';
                        }else {
                            return '未知类';
                        }
                    }
                },
                {
                    data: "createTime",
                    "render": function (data, type, row, meta) {
                        return new XDate(data).toString("yyyy-MM-dd HH:mm:ss");
                    }
                },
                {
                    data: "endTime",
                    "render": function (data, type, row, meta) {
                        return new XDate(data).toString("yyyy-MM-dd HH:mm:ss");
                    }
                },
                {
                    data: "",
                    "render": function (data, type, row, meta) {
                        var create_sec = new XDate().diffMilliseconds(new XDate(row.createTime));//正整数则startTime大
                        var end_sec = new XDate().diffMilliseconds(new XDate(row.endTime));//正整数则endTime大
                        var state = "";
                        if (create_sec > 0) {
                            state += '<i class="fa fa-circle text-warning-lt"></i><span>&nbsp;未开始</span>';
                        } else if (create_sec <= 0 && end_sec >= 0) {
                            state += '<i class="fa fa-circle text-success-lt"></i><span>&nbsp;进行中</span>';
                        } else if (end_sec < 0) {
                            state += '<i class="fa fa-circle text-danger-lt"></i><span>&nbsp;已结束</span>';
                        }
                        //else {
                        //    state += '&nbsp;&nbsp;<i class="fa fa-envelope-open-o" aria-hidden="true"></i>';
                        //}
                        return state;
                    },
                    "sortable": false
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
                $(".checkall").attr("checked", false);
            }
        } );
        return table;
    }

    function packDataTableParam(boxParam){

        // 目前不排序 不搜索
        //var orderField = boxParam.columns[boxParam.order[0].column].data;
        //var orderDir = boxParam.order[0].dir;
        var param = {
            "dataTableParam.draw": boxParam.draw,
            "dataTableParam.pageNo": boxParam.start/boxParam.length+1,
            "dataTableParam.pageSize": boxParam.length,
            //"orderField": orderField,
            //"orderDir": orderDir,
            // 封装其他参数
            "formQueryParam.type" : $("#search-formType").val(),
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

function callback(searchValueObj){
    formTable.ajax.reload(null, true);
}

/**
 * 搜索组件生成
 * 目前不搜索
 * 暂时去掉
 *
 *  {
     *  doSearchCallback: func,
     *  elements: [
     *  {
     *      name:"",
     *      id: "",
     *      type: "text|select|radio|checkbox",
     *      showName: "",
     *      placeholder: "",
     *      options: {
     *          k:v
     *      },
     *      validate: func,
     *  },
     *  ]
     * }
 *
 *
 * @type {*[]}
 */


function mymoduleBuildedCallback() {
    formTable = dataTableObj.initFormDataTable();

    $(document).on("keyup", function (event) {
        if(event.keyCode === 13){
            formTable.ajax.reload(null, true);
        }
    });
    //添加埋没点
    $("#search-btn").attr("clstag","pageclick|keycount|king_daiban|1");
    $("#formReset").attr("clstag","pageclick|keycount|king_daiban|2");
}


var searchParam = {
    doSearchCallback: callback,
    moduleBuildedCallback: mymoduleBuildedCallback,
    elements: [
        {
            name: "type",
            id: "search-formType",
            type: "select",
            className: "w-md",
            showName: "类型",
            placeholder: "类型",
            selectedValue: queryParam.type,
            /*
             if (data == 1) {
             return '质控管理类';
             } else if (data == 2) {
             return '财务结算类';
             } else if (data == 3) {
             return '资质审核类';
             } else if (data == 4) {
             return '任务类';
             } else if (data == 5) {
             return '通知类';
             } else if (data == 6){
             return '客服类';
             }else {
             return '未知类';
             }
            * */
            options: [
                {
                    value: '', name: '请选择'
                },
                {
                    value: '1', name: '质控管理类'
                },
                {
                    value: '2', name: '财务结算类'
                },
                {
                    value: '3', name: '资质审核类'
                },
                {
                    value: '4', name: '任务类'
                },
                {
                    value: '5', name: '通知类'
                },
                {
                    value: '6', name: '客服类'
                },

            ]
        },
    ]
};


var moduleSearchGenerator = new ModuleSearchGenerator("#search-wrapper", searchParam);


//var searchParam = {
//    doSearchCallback: callback,
//    elements: [
//        {
//            name: "formId",
//            id: "search-formId",
//            className: "w-md",
//            showName: "主题ID",
//            placeholder: "主题ID",
//        },
//        {
//            name: "formName",
//            id: "search-formName",
//            className: "w-md",
//            showName: "主题名称",
//            placeholder: "主题名称",
//        },
//        {
//            name: "formCreateUser",
//            id: "search-formCreateUser",
//            className: "w-md",
//            showName: "创建人",
//            placeholder: "创建人",
//        },
//        {
//            name: "formSelect",
//            id: "search-formSelect",
//            className: "w-md",
//            type: "select",
//            showName: "年份",
//            options: [
//                {
//                    value: '16', name: '2016'
//                },
//                {
//                    value: '17', name: '2017'
//                },
//                {
//                    value: '18', name: '2018'
//                },
//            ]
//        },
//    ]
//};
//var moduleSearchGenerator = new ModuleSearchGenerator("#search-wrapper", searchParam, {
//    // search click return results {formId : '', formName : '', formCreateUser : ''}
//});