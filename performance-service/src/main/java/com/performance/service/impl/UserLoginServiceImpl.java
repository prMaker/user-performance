package com.performance.service.impl;

import com.performance.common.query.UserLoginPageParam;
import com.performance.dao.mapper.UserInfoDao;
import com.performance.dao.mapper.UserLoginDao;
import com.performance.pojo.UserLogin;
import com.performance.service.UserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

@Service("userLoginService")
public class UserLoginServiceImpl implements UserLoginService {

    private static final Logger _logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Resource
    private UserLoginDao userLoginDao;
    @Resource
    private UserInfoDao userInfoDao;


    @Transactional
    public void save(UserLogin userLogin) {
        // TODO 先查数据，确认没有该用户名，此时再添加用户
        userLoginDao.insert(userLogin);
        _logger.info("新增登录用户数据成功：{}", userLogin);
    }

    public UserLogin getUserLoginById(Long userLoginId) {
        Assert.notNull(userLoginId, "userLoginId 不能为空");
        return userLoginDao.selectById(userLoginId);
    }

    @Override
    public UserLogin getUserLoginByCond(UserLogin userLogin) {
        return userLoginDao.selectByCondition(userLogin);
    }

    public void update(UserLogin userLogin) {
        userLoginDao.updateById(userLogin);
    }

    public UserLogin checkCanLogin(UserLogin userLogin) {
        // 常用操作： 获取用户信息后再比较  通过密码+盐方式保护用户密码
        return userLoginDao.selectByCondition(userLogin);
    }

    public List<UserLogin> getAllLoginUser() {
        return userLoginDao.getAllLoginUser();
    }

    @Override
    public List<UserLogin> getForPage(UserLoginPageParam pageParam) {
        return userLoginDao.getForPage(pageParam);
    }

    @Override
    public Long getCountByCreatedId(Long loginId) {
        UserLogin userLogin = new UserLogin();
        userLogin.setLoginId(loginId);
//    TODO    同 userInfo 一样 需要递归查询出所有ID 集合  然后算总数
        return userLoginDao.selectCount(userLogin);
    }


}
