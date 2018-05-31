package com.performance.service.impl;

import com.performance.common.Util;
import com.performance.dao.mapper.UserInfoDao;
import com.performance.dao.mapper.UserPerformanceDao;
import com.performance.pojo.UserInfo;
import com.performance.pojo.UserPerformance;
import com.performance.pojo.constant.IsDeletedEnum;
import com.performance.pojo.constant.IsLockedEnum;
import com.performance.service.PermissionService;
import com.performance.service.UserPerformanceService;
import com.performance.service.exec.AuthenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service("userPerformanceService")
public class UserPerformanceServiceImpl implements UserPerformanceService {

    private static final Logger _logger = LoggerFactory.getLogger(UserPerformanceServiceImpl.class);
    @Resource
    private UserPerformanceDao userPerformanceDao;
    @Resource
    private PermissionService permissionService;
    @Resource
    private UserInfoDao userInfoDao;
    @Value("adminId")// 管理员账户信息ID
    private String adminId;
    // 并发新增绩效信息锁定
    // 单机版本处理方案
    private volatile String batchSaveFlag;

    @Transactional
    public void save(UserInfo userInfo, UserPerformance userPerformance) throws AuthenException{
        // 1.本人修改
        if(userInfo.getUserInfoId().longValue() == userPerformance.getUserInfoId().longValue()){
            userPerformanceDao.updateById(userPerformance);
            _logger.info("用户修改数据{}", userPerformance);
        }
        // 2.其他人修改
        permissionService.getAuthen(userInfo, userPerformance);//校验权限
        if(userInfo.getUserInfoId().longValue() == Util.ADMIN_ID){
            // 如果是管理员，则直接修改原始数据
            userPerformanceDao.updateById(userPerformance);
            _logger.error("当前管理员用户修改审核数据成功！修改之后的数据为：{}", userPerformance);
        } else {
            userPerformance.setOperateDisposition(userInfo.getDispostion());
            userPerformance.setOperateUserInfoId(userInfo.getUserInfoId());
            userPerformanceDao.insert(userPerformance);
            userPerformanceDao.deleteById(userPerformance);
            _logger.info("用户{}审核数据{}", userInfo, userPerformance);
        }


    }

    @Transactional
    public void batchSave(String performanceTime, UserInfo userInfo) throws Exception {
        // 单机版处理方案：
        if(performanceTime.equals(batchSaveFlag)){
            _logger.error("两人同时添加当前月份审核信息：当前用户：{}, 时间：{}", userInfo, performanceTime);
            throw new Exception("其他人正在新增，请稍等刷新页面！");
        }
        performanceTime = batchSaveFlag;
        _logger.error("重置审核月份信息：{}", performanceTime);
        //TODO 集群处理方案：
        //集群数据方案：
        //使用redis保存key，通过zookeeper + redis分布式锁批量创建并发问题
        userPerformanceDao.insertBatch(getPerfoList(performanceTime, userInfo));
    }

    public void lockPerformance(UserPerformance userPerformance) {
        userPerformanceDao.lockPerformance(userPerformance);
    }

    private List<UserPerformance> getPerfoList(String performanceTime, UserInfo userInfo) {
        int size = userInfoDao.selectCount(new UserInfo()).intValue();
        //List<UserPerformance> performances = size < 100000 ? new ArrayList<UserPerformance>(size) : new LinkedList<UserPerformance>();
        return size < 10000 ? getPerforList(size, userInfo, performanceTime) : getAsynList(userInfo, performanceTime);
    }

    private List<UserPerformance> getPerforList(int size, UserInfo userInfo, String performanceTime) {
        List<UserPerformance> performances = new ArrayList<UserPerformance>(size);
        List<UserInfo> userInfos = userInfoDao.selectList(new UserInfo());
        UserPerformance performance;
        for(int i = 0; i < userInfos.size(); i++){
            performance = new UserPerformance();
            performance.setUserInfoId(userInfos.get(i).getUserInfoId());
            performance.setIsDeleted(IsDeletedEnum.IsNotDeleted.getCode());
            performance.setIsLocked(IsLockedEnum.IsNotLocked.getCode());
            performance.setPerformanceTime(performanceTime);

            performances.add(performance);
        }
        return performances;
    }

    private List<UserPerformance> getAsynList( UserInfo userInfo, String performanceTime) {
        // TODO 未实现  使用带返回值  线程池 异步快速实现
        List<UserPerformance> performances = new LinkedList<UserPerformance>();
        return performances;
    }
}
