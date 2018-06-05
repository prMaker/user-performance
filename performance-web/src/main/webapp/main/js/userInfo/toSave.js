
/**
 * 时间插件
 */
var dataPicker = (function () {

    $("#user-info-birthday").datetimepicker({
        language:  'zh-CN',//选择语言类型
        format:"yyyy-MM-dd",      //格式化日期
        startView: 4,//默认视图为年视图
        minView: 2,//设置只显示到月份
        autoclose: true,
    }).on("changeDate",function(e){
        var nowDate = DateFormat.format(e.date, "yyyyMM");
        $("#user-info-performance-time").val(nowDate);
    });
})();


//提交前校验
$("#do-save-form").validate({
    errorClass:"text-danger",
    errorElement:"span",
    rules:{
        'userInfo.idCard':{
            required:true,
            rangelength:[18,22],
            digits:true
        },
        'userInfo.userName':{
            required:true,
            rangelength:[2,10]
        },
        'userInfo.birthday':{
            required:true
        },
        'userInfo.sex':{
            required:true
        },
        'userInfo.phone':{
            required:true,
            rangelength:[10,15],
            digits:true
        }
    },
    messages:{
        'userInfo.idCard':{
            required:"请输入身份证",
            rangelength:"请输入合适长度的数字",
            digits:"请输入数字"
        },
        'userInfo.userName':{
            required:"请输入姓名",
            rangelength:"姓名长度为2到10位"
        },
        'userInfo.birthday':{
            required:"生日必填"
        },
        'userInfo.sex':{
            required:"请选择性别"
        },
        'userInfo.phone':{
            required:"请录入电话",
            rangelength:"电话长度10到15为",
            digits:"请输入数字"
        }
    },
    submitHandler:function(form){
        $("#do-save-form").submit();
    }
});


