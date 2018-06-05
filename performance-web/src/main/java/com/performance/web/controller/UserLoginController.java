package com.performance.web.controller;

import com.performance.common.Result;
import com.performance.common.query.UserLoginPageParam;
import com.performance.pojo.UserLogin;
import com.performance.service.UserLoginService;
import com.performance.web.interceptor.session.LoginContext;
import com.performance.web.vo.DataTableParam;
import com.performance.web.vo.DataTableVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        _logger.info("登录校验：" + (infoMsg == null ? "" : infoMsg));
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
    public String doSave(UserLogin userLogin,
                         RedirectAttributes redirectAttr){
        Assert.notNull(userLogin);
        packUserLAndInfoParam(userLogin);
        _logger.info("userLoginName：{}新建用户：{}", LoginContext.getUserLogin().getLoginName(), userLogin);
        userLoginService.save(userLogin);
        redirectAttr.addAttribute("infoMsg","创建成功！");
        return "redirect:/userLogin/toList?loginId=" + LoginContext.getUserLogin().getLoginId();
    }

    /**
     * 校验用户名是否重复
     * @param loginName
     * @return
     */
    @RequestMapping(value = "/checkUserNameIsRepeat", method = RequestMethod.POST)
    public Result checkUserNameIsRepeat(String loginName){
        Assert.notNull(loginName);
        UserLogin login = new UserLogin();
        login.setLoginName(loginName.trim());
        UserLogin userLogin = userLoginService.getUserLoginByCond(login);
        return new Result(null == userLogin, null == userLogin ? "已存在该用户名" : "");
    }

    private void packUserLAndInfoParam(UserLogin userLogin) {

        // 由于根据url加参数验证登录状态 这里自动装配上了此处设置为null
        userLogin.setLoginId(null);
        userLogin.setLoginName(userLogin.getLoginName().trim());
        userLogin.setCreatedUser(LoginContext.getUserLogin().getLoginName());
        userLogin.setCreatedUserId(LoginContext.getUserLogin().getLoginId());
        userLogin.setModifiedUser(LoginContext.getUserLogin().getLoginName());
        userLogin.setModifiedUserId(LoginContext.getUserLogin().getLoginId());
    }

    @RequestMapping(value = "/toList", method = RequestMethod.GET)
    public String toList(Model model){
        if(null == LoginContext.getUserInfo()){
            _logger.info("没有创建个人信息无法看到员工登录列表用户账号信息：" + LoginContext.getUserLogin());
            model.addAttribute("infoMsg", "没有创建个人信息无法看到员工列表");
            model.addAttribute("seeList", false);
            return "login/toList";
        }
        model.addAttribute("seeList", true);
        return "login/toList";
    }

    @RequestMapping(value = "/toLoginList", method = RequestMethod.GET)
    public String toLoginList(){
        return "login/toLoginList";
    }

    @RequestMapping(value = "/listData", method = RequestMethod.GET)
    @ResponseBody
    public DataTableVO listData(DataTableParam dataTableParam
            ,  UserLoginPageParam userLoginPageParam){
        DataTableVO vo = new DataTableVO();
        Assert.notNull(dataTableParam);
//        Assert.notNull(userLoginPageParam);
        vo.setDraw(dataTableParam.getDraw());
        packDTToUserLoginParam(dataTableParam, userLoginPageParam);
        userLoginPageParam.setCreatedUserId(LoginContext.getUserInfo().getUserInfoId());
        _logger.info("获取数据列表：dTParam:{},uLPageParam:{}", dataTableParam, userLoginPageParam);
        List<UserLogin> logins = userLoginService.getForPage(userLoginPageParam);
        Long count = userLoginService.getCountByCreatedId(LoginContext.getUserLogin().getLoginId());
        vo.setData(logins);
        vo.setRecordsFiltered(count);
        vo.setRecordsTotal(count);
        return vo;
    }

    private void packDTToUserLoginParam(DataTableParam dTParam, UserLoginPageParam uLPageParam) {
        uLPageParam.setPageNum(dTParam.getPageNo());
        uLPageParam.setPageSize(dTParam.getPageSize());
        uLPageParam.setOrderDir(dTParam.getOrderDir());
        uLPageParam.setOrderField(dTParam.getOrderField());
    }

    @InitBinder("userLogin")
    public void pageQueryParamBinder(WebDataBinder dataBinder) {
        dataBinder.setFieldDefaultPrefix("userLogin.");
    }

    @InitBinder("dataTableParam")
    public void dataTableParamBinder(WebDataBinder dataBinder) {
        dataBinder.setFieldDefaultPrefix("dataTableParam.");
    }

    @InitBinder("userLoginPageParam")
    public void userLoginPageParamBinder(WebDataBinder dataBinder) {
        dataBinder.setFieldDefaultPrefix("userLoginPageParam.");
    }
}
