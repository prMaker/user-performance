package com.performance.web.controller;

import com.performance.common.query.UserLoginPageParam;
import com.performance.pojo.UserLogin;
import com.performance.service.UserLoginService;
import com.performance.web.interceptor.session.LoginSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/userLogin")
public class UserLoginController {
    private static final Logger _logger = LoggerFactory.getLogger(UserLoginController.class);
    @Resource
    private UserLoginService userLoginService;

    @RequestMapping(value = "/toLogin",method = RequestMethod.GET)
    // 添加@ModelAttrbate 无法设置值
    public String toLogin(@RequestParam(required = false) String infoMsg,
                          ModelMap mm) {
        mm.put("infoMsg", infoMsg);
        _logger.info("登录校验异常：" + (infoMsg == null ? "" : infoMsg));
        return "login/toLogin";
    }

    /**
     * 校验登录
     * @param userLogin
     * @param redirectAttr
     * @return
     */
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public String doLogin(UserLogin userLogin, RedirectAttributes redirectAttr){
        Assert.notNull(userLogin);
        Assert.notNull(userLogin.getLoginName());
        Assert.notNull(userLogin.getPassword().trim());
        // 登录测试验证
        if((userLogin = userLoginService.checkCanLogin(userLogin)) != null){
            _logger.info("用户登录成功：{}",userLogin);
            redirectAttr.addAttribute("loginId", userLogin.getLoginId());
            return "redirect:/userInfo/toList";
        } else{
            _logger.warn("用户登录失败：{}",userLogin);
            redirectAttr.addAttribute("infoMsg", "用户名或密码错误！");
            return "redirect:/login/toLogin";
        }
    }

    @RequestMapping(value = "/toSave",method = RequestMethod.GET)
    public String toSave(){
        return "login/toSave";
    }

    @RequestMapping(value = "/doSave", method = RequestMethod.POST)
    public String doSave(@ModelAttribute UserLogin userLogin,
                         RedirectAttributes redirectAttr){
//        if(null == userLogin){
//            redirectAttr.addAttribute("errMsg", "请求参数异常");
//            return "handler/error";
//        }
        Assert.notNull(userLogin);
        // 由于根据url加参数验证登录状态 这里自动装配上了此处设置为null
        userLogin.setLoginId(null);
        packUserLAndInfoParam(userLogin);
        _logger.info("userLoginName：{}新建用户：{}", LoginSession.getUserLogin().getLoginName(), userLogin);
        userLoginService.save(userLogin);
        redirectAttr.addAttribute("infoMsg","创建成功！");
        return "redirect:/userLogin/list?loginId=" + LoginSession.getUserLogin().getLoginId();
    }

    private void packUserLAndInfoParam(UserLogin userLogin) {
        userLogin.setCreatedUser(LoginSession.getUserLogin().getLoginName());
        userLogin.setCreatedUserId(LoginSession.getUserLogin().getLoginId());
        userLogin.setModifiedUser(LoginSession.getUserLogin().getLoginName());
        userLogin.setModifiedUserId(LoginSession.getUserLogin().getLoginId());
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap modelMap){
        if(null == LoginSession.getUserInfo()){
            _logger.info("没有创建个人信息无法看到员工列表用户账号信息：" + LoginSession.getUserLogin());
            modelMap.put("infoMsg", "没有创建个人信息无法看到员工列表");
            return "login/list";
        }
        UserLoginPageParam pageParam = new UserLoginPageParam();
        pageParam.setCreatedUserId(LoginSession.getUserLogin().getLoginId());
        List<UserLogin> logins = userLoginService.getForPage(pageParam);
        Long count = userLoginService.getCountByCreatedId(LoginSession.getUserLogin().getLoginId());
        modelMap.addAttribute("logins" , logins);
        modelMap.addAttribute("count" , count);
        return "login/list";
    }

    @InitBinder("userLogin")
    public void pageQueryParamBinder(WebDataBinder dataBinder) {
        dataBinder.setFieldDefaultPrefix("userLogin.");
    }

}
