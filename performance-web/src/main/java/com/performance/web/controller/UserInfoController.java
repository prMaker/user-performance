package com.performance.web.controller;

import com.performance.common.Result;
import com.performance.common.query.UserInfoPageParam;
import com.performance.pojo.UserInfo;
import com.performance.pojo.UserLogin;
import com.performance.service.Po.UserInfoPerfor;
import com.performance.service.UserInfoService;
import com.performance.service.UserLoginService;
import com.performance.web.interceptor.session.LoginSession;
import com.performance.web.vo.DataTableParam;
import com.performance.web.vo.DataTableVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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
        return "/userInfo/toSave";
    }

    @RequestMapping(value = "/doSave",method = RequestMethod.POST)
    public String doSave(@ModelAttribute("userInfo") UserInfo userInfo,
                         RedirectAttributes redirectAttr){
//        if(null == userInfo){
//            redirectAttr.addAttribute("", "");
//            return "/userInfo/toSave";
//        }
        Assert.notNull(userInfo);
        UserLogin createdLogin = userLoginService.getUserLoginById(
                LoginSession.getUserLogin().getLoginId());
        userInfo.setCreatedUserInfoId(createdLogin.getUserInfoId());
        userInfo.setModifiedUserInfoId(createdLogin.getUserInfoId());
        userInfo.setDispostion(LoginSession.getUserLogin().getDispostion());
        userInfoService.save(userInfo);
        redirectAttr.addAttribute("infoMsg", "修改用户信息成功");
        return "redirect:/userInfo/list?loginId=" + LoginSession.getUserLogin().getLoginId();
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
//                ,produces = {"text/plain", "application/json"})
    @ResponseBody
    public Result listData(DataTableParam dTParam
            , UserInfoPageParam param){
        DataTableVO vo = new DataTableVO();
        // 测试参数
        dTParam = new DataTableParam();
        dTParam.setDraw(1);
        dTParam.setPageNo(1);
        dTParam.setPageSize(10);
        // 完毕

        try {
            checkAndSetParam(dTParam, param);
            handlerDTParamToP(dTParam, param);
            List<UserInfoPerfor> userInfoPerfors = userInfoService.getUserIPByParam(LoginSession.getUserInfo(), param);
            Long count = userInfoService.getUserIPCount(param);

            vo.setData(userInfoPerfors);
            vo.setDraw(dTParam.getDraw());
            vo.setRecordsTotal(count);
            vo.setRecordsFiltered(count);

        } catch (IllegalArgumentException ex) {
            _logger.error("获取：UserInfoController.listData异常！用户信息：UserInfo" + LoginSession.getUserInfo() + "，原因：" + ex.getMessage()
                    , ex);
            return new Result(false, ex.getMessage());
        }
        return new Result().setData(vo);
    }

    private void checkAndSetParam(DataTableParam dTParam, UserInfoPageParam param) {
        Assert.notNull(dTParam);
        param = param == null ? new UserInfoPageParam() : param;
//        Assert.notNull(param);
        if(StringUtils.isBlank(param.getPerformanceTime())){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            param.setPerformanceTime(sdf.format(new Date()));
        }
//        Assert.notNull(param.getPerformanceTime(), "审核时间不能为空！");
    }

    private void handlerDTParamToP(DataTableParam dataTableParam, UserInfoPageParam param) {
//        dataTableParam.setOrderDir("");
//        dataTableParam.setOrderField("");

        param.setPageNum(dataTableParam.getPageNo());
        param.setPageSize(dataTableParam.getPageSize());
//        param.setOrderDir(dataTableParam.getOrderDir());
//        param.setOrderField(dataTableParam.getOrderField());
        param.setPid(LoginSession.getUserInfo().getUserInfoId());
    }

    @InitBinder("userInfo")
    public void pageQueryParamBinder(WebDataBinder dataBinder) {
        dataBinder.setFieldDefaultPrefix("userInfo.");
    }

}
