package com.performance.web.controller;

import com.performance.common.query.UserInfoPageParam;
import com.performance.pojo.UserInfo;
import com.performance.pojo.UserLogin;
import com.performance.pojo.UserInfoPerfor;
import com.performance.service.UserInfoService;
import com.performance.service.UserLoginService;
import com.performance.service.exec.ErrorDataException;
import com.performance.web.interceptor.session.LoginSession;
import com.performance.web.vo.DataTableParam;
import com.performance.web.vo.DataTableVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    private static final Logger _logger = LoggerFactory.getLogger(UserInfoController.class);

    @Resource
    private UserInfoService userInfoService;
    @Resource
    private UserLoginService userLoginService;

    // 先创建用户登录，在用户信息设置
    @RequestMapping(value = "/toSave",method = RequestMethod.GET)
    public String toSave(){
        UserInfo userInfo = userInfoService.getUserInfoById(LoginSession.getUserLogin().getUserInfoId());
        return "/userInfo/toSave";
    }

    @RequestMapping(value = "/doSave",method = RequestMethod.POST)
    public String doSave(@ModelAttribute("userInfo") UserInfo userInfo,
                         RedirectAttributes redirectAttr){
        Assert.notNull(userInfo);
        UserLogin createdLogin = userLoginService.getUserLoginById(
                LoginSession.getUserLogin().getCreatedUserId());
        _logger.info("用户账户：{}保存用户信息成功：{}", LoginSession.getUserLogin(), userInfo);
        userInfoService.saveOrUpdate(userInfo, createdLogin, LoginSession.getUserLogin());
        redirectAttr.addAttribute("infoMsg", "修改用户信息成功");
        return "redirect:/userInfo/toList?loginId=" + LoginSession.getUserLogin().getLoginId();
    }

    @RequestMapping(value = "toList", method = RequestMethod.GET)
    public String toList(Model model, String infoMsg){
        model.addAttribute("noUserInfo", null == LoginSession.getUserInfo());
        model.addAttribute("infoMsg", infoMsg);

        return "/userInfo/toList";
    }

    @RequestMapping(value = "/listData", method = RequestMethod.POST
            , produces = "application/json;charset=UTF-8")
    @ResponseBody
    public DataTableVO listData(DataTableParam dataTableParam
            , UserInfoPageParam userInfoPageParam){
        // TODO 校验ERROR参数
        DataTableVO vo = new DataTableVO();

        try {
            checkAndSetParam(dataTableParam, userInfoPageParam);
            _logger.info("用户：{}查询用户信息：{}", LoginSession.getUserInfo());
            List<UserInfoPerfor> userInfoPerfors = userInfoService.getUserIPByParam(LoginSession.getUserInfo(), userInfoPageParam);
            Long count = userInfoService.getUserIPCount(userInfoPageParam);

            vo.setData(userInfoPerfors);
            vo.setDraw(dataTableParam.getDraw());
            vo.setRecordsTotal(count);
            vo.setRecordsFiltered(count);

        } catch (IllegalArgumentException ex) {
            _logger.error("获取：UserInfoController.listData异常！用户信息：UserInfo" + LoginSession.getUserInfo() + "，原因：" + ex.getMessage()
                    , ex);
            return vo.setError(ex.getMessage());
        } catch (IllegalStateException ex) {
            _logger.error("获取：UserInfoController.listData异常！用户信息：UserInfo" + LoginSession.getUserInfo() + "，原因：" + ex.getMessage()
                    , ex);
            return vo.setError(ex.getMessage());
        }
        return vo;
    }

    private void checkAndSetParam(DataTableParam dataTableParam, UserInfoPageParam param)
            throws IllegalArgumentException, IllegalStateException{
        Assert.notNull(dataTableParam);
        if(null == LoginSession.getUserInfo()){
            _logger.error("用户账号：{}查看用户信息失败：没有记录用户信息", LoginSession.getUserLogin());
            throw new IllegalStateException("只有完善用户信息后才能查看当前月份用户信息列表!");
        }
        param = param == null ? new UserInfoPageParam() : param;
        if(StringUtils.isBlank(param.getPerformanceTime())){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            param.setPerformanceTime(sdf.format(new Date()));
        }
//        dataTableParam.setOrderDir("");
//        dataTableParam.setOrderField("");

        param.setPageNum(dataTableParam.getPageNo());
        param.setPageSize(dataTableParam.getPageSize());
//        param.setOrderDir(dataTableParam.getOrderDir());
//        param.setOrderField(dataTableParam.getOrderField());
        param.setPid(LoginSession.getUserInfo().getUserInfoId());
//        Assert.notNull(param.getPerformanceTime(), "审核时间不能为空！");
    }

    @InitBinder("userInfo")
    public void userInfoBinder(WebDataBinder dataBinder) {
        dataBinder.setFieldDefaultPrefix("userInfo.");
    }

    @InitBinder("dataTableParam")
    public void dataTableParamBinder(WebDataBinder dataBinder) {
        dataBinder.setFieldDefaultPrefix("dataTableParam.");
    }


    @InitBinder("userInfoPageParam")
    public void paramBinder(WebDataBinder dataBinder) {
        dataBinder.setFieldDefaultPrefix("userInfoPageParam.");
    }

}
