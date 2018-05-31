package com.performance.web.controller.test;

import com.performance.dao.mapper.UserInfoDao;
import com.performance.dao.mapper.UserLoginDao;
import com.performance.dao.mapper.UserPerformanceDao;
import com.performance.pojo.UserInfo;
import com.performance.pojo.UserLogin;
import com.performance.pojo.UserPerformance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/test")
public class Test {

    @Resource
    UserLoginDao userLoginDao;
    @Resource
    UserInfoDao userInfoDao;
    @Resource
    UserPerformanceDao userPerformanceDao;


    @RequestMapping("/home")
    public String home(){
        return "/test/home";
    }


    @RequestMapping("/insertToTest")
    @ResponseBody
    public String testInsert() {

        userLoginDao.insert(getUserLogin());
        userInfoDao.insert(getUserInfo());
        userPerformanceDao.insert(getUserPerformance());
        return "success";
    }

    @RequestMapping("/temp")
    public String temp(){
        return "/test/temp";
    }


//    (is_deleted,login_name,password,dispostion
//   ,login_state,created_time,modified_time,created_user,modified_user,created_user_id,modified_user_id)
//    VALUES
//    (0,'admin','admin',99
//  ,'',now(),now(),'admin','admin',0,0);
    public UserLogin getUserLogin() {
        UserLogin userLogin = new UserLogin();
        return userLogin;
    }

    public UserInfo getUserInfo() {
        UserInfo userInfo = null;
        return userInfo;
    }

    public UserPerformance getUserPerformance() {
        UserPerformance userPerformance = null;
        return userPerformance;
    }
}
