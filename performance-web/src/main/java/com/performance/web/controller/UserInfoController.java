package com.performance.web.controller;

import com.performance.common.Result;
import com.performance.common.query.UserInfoPageParam;
import com.performance.pojo.UserInfo;
import com.performance.pojo.UserLogin;
import com.performance.pojo.UserInfoPerfor;
import com.performance.pojo.UserPerformance;
import com.performance.service.UserInfoService;
import com.performance.service.UserLoginService;
import com.performance.service.UserPerformanceService;
import com.performance.web.interceptor.session.LoginContext;
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
    @Resource
    private UserPerformanceService userPerformanceService;

    // 先创建用户登录，在用户信息设置
    @RequestMapping(value = "/toSave",method = RequestMethod.GET)
    public String toSave(Model model){
        if(LoginContext.getUserInfo() == null){
            model.addAttribute("save", true);
        } else {
            UserInfo info = userInfoService.getUserInfoById(LoginContext.getUserInfo().getUserInfoId());
            model.addAttribute("update", true);
            model.addAttribute("userInfo", info);
        }
        return "/userInfo/toSave";
    }

    @RequestMapping(value = "/doSave",method = RequestMethod.POST)
    public String doSave(@ModelAttribute("userInfo") UserInfo userInfo,
                         RedirectAttributes redirectAttr){
        Assert.notNull(userInfo);
        UserLogin createdLogin = userLoginService.getUserLoginById(
                LoginContext.getUserLogin().getCreatedUserId());
        _logger.info("用户账户：{}保存用户信息成功：{}", LoginContext.getUserLogin(), userInfo);
        userInfoService.saveOrUpdate(userInfo, createdLogin, LoginContext.getUserLogin());
        redirectAttr.addAttribute("infoMsg", "修改用户信息成功");
        return "redirect:/userInfo/toList?loginId=" + LoginContext.getUserLogin().getLoginId();
    }

    @RequestMapping(value = "/toList", method = RequestMethod.GET)
    public String toList(Model model, String infoMsg){
        model.addAttribute("noUserInfo", null == LoginContext.getUserInfo());
        model.addAttribute("infoMsg", infoMsg);
        // 添加
        return "/userInfo/toList";
    }

    @RequestMapping(value = "/getUserPerformance", method = RequestMethod.POST)
    @ResponseBody
    public Result getUserPerformance(String performanceTime){
        Assert.notNull(performanceTime);
        UserPerformance perfor =
                userPerformanceService.getUserPerforByCond(getUPByTime(performanceTime));
        return new Result(null != perfor , "", perfor);
    }

    private UserPerformance getUPByTime(String performanceTime) {
        UserPerformance userPerformance = new UserPerformance();
        userPerformance.setPerformanceTime(performanceTime);
        userPerformance.setUserInfoId(LoginContext.getUserInfo().getUserInfoId());
        return userPerformance;
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
            _logger.info("用户：{}查询用户信息：{}", LoginContext.getUserInfo());
            List<UserInfoPerfor> userInfoPerfors = userInfoService.getUserIPByParam(LoginContext.getUserInfo(), userInfoPageParam);
            Long count = userInfoService.getUserIPCount(userInfoPageParam);

            vo.setData(userInfoPerfors);
            vo.setDraw(dataTableParam.getDraw());
            vo.setRecordsTotal(count);
            vo.setRecordsFiltered(count);

        } catch (IllegalArgumentException ex) {
            _logger.error("获取：UserInfoController.listData异常！用户信息：UserInfo" + LoginContext.getUserInfo() + "，原因：" + ex.getMessage()
                    , ex);
            return vo.setError(ex.getMessage());
        } catch (IllegalStateException ex) {
            _logger.error("获取：UserInfoController.listData异常！用户信息：UserInfo" + LoginContext.getUserInfo() + "，原因：" + ex.getMessage()
                    , ex);
            return vo.setError(ex.getMessage());
        }
        return vo;
    }

    private void checkAndSetParam(DataTableParam dataTableParam, UserInfoPageParam param)
            throws IllegalArgumentException, IllegalStateException{
        Assert.notNull(dataTableParam);
        if(null == LoginContext.getUserInfo()){
            _logger.error("用户账号：{}查看用户信息失败：没有记录用户信息", LoginContext.getUserLogin());
            throw new IllegalStateException("只有完善用户信息后才能查看当前月份用户信息列表!");
        }
        param = param == null ? new UserInfoPageParam() : param;
        if(StringUtils.isBlank(param.getPerformanceTime())){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            param.setPerformanceTime(sdf.format(new Date()));
        }

        param.setPageNum(dataTableParam.getPageNo());
        param.setPageSize(dataTableParam.getPageSize());
        param.setOrderDir(dataTableParam.getOrderDir());
        param.setOrderField(dataTableParam.getOrderField());
        param.setPid(LoginContext.getUserInfo().getUserInfoId());
    }

    @RequestMapping(value = "/performanceToAdd", method = RequestMethod.POST)
    @ResponseBody
    public Result performanceToAdd(String performanceTime){
        List<Long> childInfos = userInfoService.getIdsByPid(LoginContext.getUserInfo().getUserInfoId());
        Long perforCount = userPerformanceService.selectCountByInfoIDs(childInfos, performanceTime);
        boolean res = childInfos.size() > perforCount;
        return new Result(res,"", res);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(Long userInfoId){
        Assert.notNull(userInfoId);
        UserInfo userInfo = userInfoService.getUserInfoById(userInfoId);
        boolean res = userInfoService.deleteById(userInfo);
        return new Result(res, res ? "删除成功！" : "删除失败！");
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
