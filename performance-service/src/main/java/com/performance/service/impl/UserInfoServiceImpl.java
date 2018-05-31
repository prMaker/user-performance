package com.performance.service.impl;

import com.performance.common.query.UserInfoPageParam;
import com.performance.dao.mapper.UserInfoDao;
import com.performance.dao.mapper.UserLoginDao;
import com.performance.dao.mapper.UserPerformanceDao;
import com.performance.pojo.UserInfo;
import com.performance.pojo.UserLogin;
import com.performance.pojo.UserPerformance;
import com.performance.pojo.constant.IsDeletedEnum;
import com.performance.service.PermissionService;
import com.performance.service.Po.UserInfoPerfor;
import com.performance.service.UserInfoService;
import com.performance.service.exec.AuthenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    private static final Logger _logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private UserLoginDao userLoginDao;
    @Resource
    private UserPerformanceDao userPerformanceDao;
    @Resource
    private PermissionService permissionService;

    public UserInfo getUserInfoById(Long userInfoId) throws IllegalArgumentException {
        return userInfoDao.selectById(userInfoId);
    }

    @Transactional
    public void save(UserInfo userInfo) {
        // 同步保存 userLogin中的userInfoId信息
        userInfo.setIsDeleted(IsDeletedEnum.IsNotDeleted.getCode());
        userInfoDao.insert(userInfo);// FIXME 查看id是否已经拿到,未拿到重新获取
        UserLogin userLogin = userLoginDao.selectById(userInfo.getLoginId());
        userLogin.setUserInfoId(userInfo.getUserInfoId());
        userLoginDao.updateById(userLogin);
    }

    public List<UserInfoPerfor> getUserIPByParam(UserInfo currUserInfo, UserInfoPageParam param) throws RuntimeException {

        List<UserInfo> userInfos = userInfoDao.selectForPage(param);
//        UserPerformance performance = new UserPerformance();
//        performance.setPerformanceTime(param.getPerformanceTime());
        HashMap<String,Object> uPParam = new HashMap<>();
        uPParam.put("performanceTime", param.getPerformanceTime());
        uPParam.put("userInfos", userInfos);

        List<UserPerformance> perforPages = userPerformanceDao.selectForPage(uPParam);
//        List<UserPerformance> performances = userPerformanceDao.selectList(performance);
        return getUserInfoPerfors(currUserInfo, param, userInfos, perforPages);
    }

    /**
     * 通过 userInfos 和performances 组装页面对象
     *
     * 目前尚不支持排序功能
     * @param currUserInfo
     * @param param
     * @param userInfos
     * @param performances
     * @return
     */
    private List<UserInfoPerfor> getUserInfoPerfors(UserInfo currUserInfo, UserInfoPageParam param
            , List<UserInfo> userInfos, List<UserPerformance> performances)
            throws RuntimeException{
        List<UserInfoPerfor> userInfoPerfors = new ArrayList<UserInfoPerfor>();

        // 1.   数据若不一致性，即数据出现问题，一般不会
        if(userInfos.size() != performances.size()){
            _logger.error("查询数据库表user_info和user_performance数据不一致，参数{}", param);
            throw new RuntimeException("后台数据不一致，请联系管理员！");
        }

        // 2.   JDK8 排序查出的数据
        List<UserInfo> infoSorted = userInfos.stream()
                .sorted((e1,e2) -> -e1.getUserInfoId().compareTo(e2.getUserInfoId()))
                .collect(Collectors.<UserInfo>toList());
        List<UserPerformance> perforSorted = performances.stream()
                .sorted((e1,e2) -> -e1.getUserInfoId().compareTo(e2.getUserInfoId()))
                .collect(Collectors.toList());
        // 3. 组装数据到userInfoPerfors
        UserInfoPerfor perfor;
        for(int i=0; i< infoSorted.size(); i++){
            perfor = new UserInfoPerfor();
            BeanUtils.copyProperties(infoSorted.get(i), perfor);
            perfor.setUserPerformance(perforSorted.get(i));
            try {
                perfor.setPermissionToFix(permissionService.getAuthen(currUserInfo, perforSorted.get(i)));
            } catch (AuthenException ex) {
                _logger.error("设置审核权限信息异常:"
                        + ex.getMessage() + "参数：当前登录用户：" + currUserInfo + "，校验审核信息："
                        + perforSorted.get(i), ex);
                // 校验审核出错 默认设置不可以修改
                perfor.setPermissionToFix(false);
            }
            userInfoPerfors.add(perfor);
        }
        return userInfoPerfors;
    }

    public Long getUserIPCount(UserInfoPageParam param) {
        return userInfoDao.selectCount(new UserInfo());
    }
}
