package com.performance.web.controller;

import com.performance.pojo.UserPerformance;
import com.performance.pojo.constant.DispositionEnum;
import com.performance.service.UserPerformanceService;
import com.performance.service.exec.AuthenException;
import com.performance.web.interceptor.session.LoginSession;
import com.performance.common.Result;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

@Controller
@RequestMapping("/performance")
public class UserPerformanceController {

    private static final Logger _logger = LoggerFactory.getLogger(UserPerformanceController.class);

    @Resource
    private UserPerformanceService userPerformanceService;

    /**
     * 保存权限数据
     * @param userPerformance
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Result save(UserPerformance userPerformance){
        if(null == userPerformance){
            return new Result(false, "请求参数异常！");
        }
        userPerformance.setOperateUserInfoId(LoginSession.getUserInfo().getUserInfoId());
        userPerformance.setOperateDisposition(LoginSession.getUserInfo().getDispostion());

        try {
            _logger.info("用户{}审核数据：{}", LoginSession.getUserInfo(), userPerformance);
            userPerformanceService.save(LoginSession.getUserInfo(), userPerformance);
        } catch (AuthenException ex) {
            return new Result(false, ex.getMessage());
        }
        return new Result().setMsg("保存成功");
    }

    /**
     * 设置当前月份的 审核数据信息
     * 初始化该月所有员工审核表数据
     * @param performanceTime
     * @param redirectAttr
     * @return
     */
    @RequestMapping(value = "/setCurrPerformance", method = RequestMethod.GET)
    public String setCurrPerformance(@RequestParam String performanceTime , RedirectAttributes redirectAttr){
        if(StringUtils.isBlank(performanceTime)) {
            return getUserInfoList(redirectAttr);
        }
        _logger.info("当前用户设置该月审核信息：{}，月份：{}", LoginSession.getUserInfo(), performanceTime);
        try {
            userPerformanceService.batchSave(performanceTime, LoginSession.getUserInfo());
        } catch (Exception e) {
            _logger.error("出现并发新增绩效信息：当前并发新增人ID；{}", LoginSession.getUserLogin().getLoginId());
            redirectAttr.addAttribute("infoMsg", e.getMessage());
            e.printStackTrace();
        }
        return getUserInfoList(redirectAttr);
    }

    /**
     * 审核锁定数据
     * @param performanceTime
     * @return
     */
    @RequestMapping(value = "/lockPerformance", method = RequestMethod.GET)
    @ResponseBody
    public Result lockPerformance(@RequestParam String performanceTime){
        if(StringUtils.isBlank(performanceTime)){
            return new Result(false, "请求参数异常，没有当前月份信息！");
        }
        if(LoginSession.getUserInfo().getDispostion().intValue() != DispositionEnum.FourLevel.getCode()){
            return new Result(false, "只有经理可以审核数据，您没有权限！");
        }
        _logger.error("用户:{}锁定审核数据月份：{}", LoginSession.getUserInfo(), performanceTime);
        UserPerformance performance = new UserPerformance();
        performance.setPerformanceTime(performanceTime);
        userPerformanceService.lockPerformance(performance);
        return new Result();
    }


    private String getUserInfoList(RedirectAttributes redirectAttr) {
        redirectAttr.addAttribute("userLoginId", LoginSession.getUserLogin().getLoginId());
        return "redirect:/userInfo/list?loginId=" + LoginSession.getUserLogin().getLoginId();
    }

}
