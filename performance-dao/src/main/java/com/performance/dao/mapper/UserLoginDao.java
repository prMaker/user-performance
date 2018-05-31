package com.performance.dao.mapper;

import com.performance.common.query.UserLoginPageParam;
import com.performance.dao.BaseDao;
import com.performance.pojo.UserLogin;

import java.util.List;

public interface UserLoginDao extends BaseDao<UserLogin, Long>{

    List<UserLogin> getAllLoginUser();

    UserLogin selectByCondition(UserLogin userLogin);

    List<UserLogin> getForPage(UserLoginPageParam pageParam);
}
