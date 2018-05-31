package com.performance.service;

import com.performance.common.query.UserLoginPageParam;
import com.performance.pojo.UserLogin;

import java.util.List;

public interface UserLoginService {

    void save(UserLogin userLogin);

    UserLogin getUserLoginById(Long userLoginId);

    UserLogin getUserLoginByCond(UserLogin userLogin);

    void update(UserLogin userLogin);

    /**
     * 校验登录
     * @param userLogin
     * @return
     */
    UserLogin checkCanLogin(UserLogin userLogin);

    List<UserLogin> getAllLoginUser();

    /**
     * 根据条件分页
     * @param pageParam
     * @return
     */
    List<UserLogin> getForPage(UserLoginPageParam pageParam);

    /**
     * 查询创建的员工登录信息数量
     * @param loginId
     * @return
     */
    Integer getCountByCreatedId(Long loginId);
}
