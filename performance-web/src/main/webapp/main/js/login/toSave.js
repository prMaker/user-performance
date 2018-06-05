
(function(){
    $("#user-login-login-name").blur(checkUserNameIsRepeat);

    $("#save-btn").click(formSave);

    function checkUserNameIsRepeat() {
        $.ajax({
            url:"/userLogin/checkUserNameIsRepeat",
            type:"POST",
            dataType:"json",
            data:{loginName:$("#user-login-name").val()},
            success:function (data) {
                if(!data.success()){
                    $("#loginName-errMsg").text(data.msg);
                }
            }
        });
    }
    function formSave() {

        var loginName = $("#user-login-login-name").val();
        var passWord = $("#user-login-password").val();
        var repeatPassWord = $("#user-login-repeat-password").val();
        var disposition = $("#user-info-disposition").val();
        if(!checkparam()){
            return;
        }
        $("#do-save-form").submit();
        function checkparam() {
            if(!loginName){
                $("#loginName-errMsg").text("登陆名必填！");
                return false;
            }
            if(loginName.length > 2 || loginName.length < 20){
                $("#loginName-errMsg").text("登录名长度不能小于2不能大于20！");
                return false;
            }
            if(!passWord){
                $("#password-errMsg").text("密码必填！");
                return false;
            }
            if(passWord.length > 2 || passWord.length < 20){
                $("#password-errMsg").text("密码长度不能小于2不能大于20！");
                return false;
            }
            if(passWord !== repeatPassWord){
                $("#repeatPassword-errMsg").text("两次密码请相同！");
                return false;
            }
            if(!disposition){
                $("#dispostion-errMsg").text("请选择职位！");
                return false;
            }
            return true;
        }
    }
})();