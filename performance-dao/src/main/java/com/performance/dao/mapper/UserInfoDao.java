package com.performance.dao.mapper;

import com.performance.common.query.UserInfoPageParam;
import com.performance.dao.BaseDao;
import com.performance.pojo.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserInfoDao extends BaseDao<UserInfo,Long>{

    List<UserInfo> selectAllChild(Long userInfoId);

    UserInfo containsChild(Map<String, Long> query);

    List<UserInfo> selectForPage(UserInfoPageParam param);
}
