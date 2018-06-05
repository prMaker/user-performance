package com.performance.web.interceptor.session;

import com.performance.pojo.UserInfo;
import com.performance.pojo.UserLogin;

import java.util.HashMap;
import java.util.Map;

public class LoginContext {

    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    /**
     * 使用前先校验是否是空，如果是空则当前用户尚没有录入用户基本信息
     * @return
     */
    public static UserInfo getUserInfo(){
        return (UserInfo) threadLocal.get().get("userInfo");
    }

    public static UserLogin getUserLogin(){
        return (UserLogin) threadLocal.get().get("userLogin");
    }

    public static void setUserInfo(UserInfo userInfo){
        if(null == threadLocal.get()){
            threadLocal.set(new HashMap<String, Object>());
        }
        threadLocal.get().put("userInfo", userInfo);
    }

    public static void setUserLogin(UserLogin userLogin){
        threadLocal.get().put("userLogin", userLogin);
    }

    public static void remove(){
        threadLocal.remove();
    }
}
