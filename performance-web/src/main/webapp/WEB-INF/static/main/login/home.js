(function(){

    console.log("123123");

    $("#saveUserInfo").delegate("click",function () {
        $.ajax({
            url:"/userInfo/doSave",
            type:"POST",
            data:{
                loginId:67,
                idCard:410,
                userName:"测试添加",
                birthday:"20170905",
                sex:1,
                phone:110,
                pid:33,
                dispostion:1,
                createdUserInfoId:33,
                modifiedUserInfoId:33
            },
            success:function (data) {
                alert(data);
            },
            error:function () {
                alert("请求异常！");
            }
        });
    });

    $("#userInfoList").delegate("click", function () {
        $.ajax({
            url:"/userInfo/doSave",
            type:"POST",
            data:{},
            success:function (data) {
                alert(data);
            },
            error:function () {
                alert("请求异常！");
            }
        });
    })
})();