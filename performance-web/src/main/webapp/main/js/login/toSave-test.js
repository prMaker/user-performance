//校验
$("#do-save-form").validate({
    errorClass:"text-danger",
    errorElement:"span",
    rules:{
        'userLogin.loginName':{
            required:true,
            rangelength:[2,20]
        },
        'userLogin.password':{
            required:true,
            rangelength:[2,20]
        },
        // 'repeatPassword':{
        //     equalTo:"#user-login-password"
        // }
    },
    messages:{
        'userLogin.loginName':{
            required:"请输入用户名",
            rangelength:"用户名长度2~20位"
        },
        'userLogin.password':{
            required:"请输入密码",
            rangelength:"密码长度2~20位"
        },
        // 'repeatPassword':{
        //     equalTo:"请录入相同的密码"
        // }
    },
    submitHandler:function(form){
        $("#do-save-form").submit();
        /*

        $.extend({
            PostSubmitForm: function (url, args) {
                var body = $(document.body),
                    form = $("<form method='post' style='display:none'></form>"),
                    input;
                form.attr({ "action": url });
                $.each(args, function (key, value) {
                    input = $("<input type='hidden'>");
                    input.attr({ "name": key });
                    input.val(value);
                    form.append(input);
                });

                //IE低版本和火狐下
                form.appendTo(document.body);
                form.submit();
                document.body.removeChild(form[0]);
            }
        });
        var url = $("#do-save-form").attr("action");
        var data = {
            "userLogin.loginName":loginName,
            "userLogin.password":passWord,
            "userLogin.dispostion":disposition
        };
        $.PostSubmitForm(url , data);


        */
        $("#do-login-form").submit();
    }
});

