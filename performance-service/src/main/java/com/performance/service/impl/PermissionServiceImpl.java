package com.performance.service.impl;

import com.performance.common.Util;
import com.performance.dao.mapper.UserInfoDao;
import com.performance.dao.mapper.UserPerformanceDao;
import com.performance.pojo.UserInfo;
import com.performance.pojo.UserPerformance;
import com.performance.pojo.constant.IsLockedEnum;
import com.performance.service.PermissionService;
import com.performance.service.UserInfoService;
import com.performance.service.exec.AuthenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    private static final Logger _logger = LoggerFactory.getLogger(PermissionServiceImpl.class);
    @Resource
    private UserPerformanceDao userPerformanceDao;
    @Resource
    private UserInfoDao userInfoDao;
    @Value("adminId")// 管理员账户信息ID
    private String adminId;
    @Resource
    private UserInfoService userInfoService;

    /**
     * 校验单条数据当前用户是否可以修改
     *
     * 校验策略：
     * 1.配置超级管理员，超级管理员可以修改，若是超级管理员修改之后，其他人不可以修改
     * 2.上级修改完后下级不可以修改，上级没有修改的数据下级可以修改
     * 3.锁定的数据 除了超级管理员可以修改其他人不可以修改  同时修改之后的数据重新锁定，
     * 其他人不可以修改
     * @return
     */
    public boolean getAuthen(UserInfo localUserInfo, UserPerformance performance) throws AuthenException{
        // 1. 如果该条审核数据还没有生成，则返回可以修改
        if(null == performance || performance.getPerformanceId() == null){
            return true;
        }
        _logger.info("校验用户审核权限：登录用户：{}，要审核的数据：{}", localUserInfo, performance);
        UserPerformance performan = userPerformanceDao.selectById(performance.getPerformanceId());
        // 2. 如果是管理员用户，则可以直接修改
//        _logger.debug("配置的adminID 为："  + adminId == null ? "" : adminId );
        if(localUserInfo.getUserInfoId().longValue() == Util.ADMIN_ID){
            _logger.error("当前管理员用户修改审核数据修改前：{}，修改后数据：{}", performan, performance);
            return true;
        }
        // 3.如果数据已经锁定 则不可以修改
        if(performan.getIsLocked().intValue() == IsLockedEnum.IsLocked.getCode()){
            throw new AuthenException("该数据已经锁定，请找管理员重新修改数据");
        }
        // 4. 未查到 这条数据已经删除，说明其他人已经改了， 返回给用户重新修改尝试,同时也无法修改
        if(null == performan) throw new AuthenException("该数据已经被修改！请刷新页面重试！");

        // 5. 该条数据已经生成 但尚未审核，并且该登录人是当前人的上级  则可以审核
        if(null == performan.getOperateUserInfoId() || IsHighPosition(localUserInfo, performan)){
            return true;
        }
        /*其他情况*/
        _logger.error("其他情况下不可以修改审核数据");
        throw new AuthenException("目前不是该人上级！不可以修改审核数据");
    }

    private boolean IsHighPosition(UserInfo localUserInfo, UserPerformance performan) throws AuthenException{
        List<Long> ids =  userInfoService.getIdsByPid(localUserInfo.getUserInfoId());// 正常应该拿一个比一个
        if(!ids.contains(performan.getOperateUserInfoId())){
            throw new AuthenException("没有权限修改上级绩效修改之后的的数据！");
        }
        return true;
    }
}
