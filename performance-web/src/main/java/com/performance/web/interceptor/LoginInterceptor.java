package com.performance.web.interceptor;

import com.performance.pojo.UserInfo;
import com.performance.pojo.UserLogin;
import com.performance.service.UserInfoService;
import com.performance.service.UserLoginService;
import com.performance.web.interceptor.session.LoginContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录校验 + 存储用户基本信息
 */
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger _logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Resource
    private UserLoginService userLoginService;
    @Resource
    private UserInfoService userInfoService;
    // TODO 写入验证登录
    // 0.使用cookie过期实现登录验证  简单实现
    // 1.使用guava缓存存储session数据 实现服务器端验证cookie登录校验  复杂实现
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserInfo userInfo = null;
        UserLogin userLogin = null;
        try {
            if(request.getRequestURI().startsWith("/userLogin/toLogin") ||
                    request.getRequestURI().startsWith("/userLogin/doLogin")){// 登录页面
                return true;
            }
            if(StringUtils.isBlank(request.getParameter("loginId"))) return getFalseToUrl(response);// FIXME 重定向到登陆页面
            // userLogin 信息必有
            userLogin = userLoginService.getUserLoginById(Long.valueOf(request.getParameter("loginId")));
            if(null == userLogin) return getFalseToUrl(response);// FIXME  拦截之后重新定位到新的页面
            request.getSession().setAttribute("userLogin", userLogin);

            // userInfo 信息，填了才有
            if(null != userLogin.getUserInfoId()){
                userInfo = userInfoService.getUserInfoById(userLogin.getUserInfoId());
                if(null != userInfo)
                    request.getSession().setAttribute("userInfo", userInfo);
            }
        } catch (IllegalArgumentException e) {
            _logger.error("登录id:" + request.getParameter("userInfoId") + "登录校验异常！", e);
            return getFalseToUrl(response);
        }
        _logger.info("用户id:{}已经登录", userLogin.getLoginId());
        LoginContext.setUserInfo(userInfo);
        LoginContext.setUserLogin(userLogin);
        return true;
    }

    /**
     * 失败后跳转到登陆页面
     * @param response
     * @return
     * @throws IOException
     */
    private boolean getFalseToUrl(HttpServletResponse response) throws IOException {
        response.sendRedirect("/userLogin/toLogin");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginContext.remove();
    }
}
