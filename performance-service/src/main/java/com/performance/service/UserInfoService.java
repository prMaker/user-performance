package com.performance.service;

import com.performance.common.query.UserInfoPageParam;
import com.performance.pojo.UserInfo;
import com.performance.service.Po.UserInfoPerfor;

import java.util.List;

public interface UserInfoService {

    /**
     * 通过ID 获取基本信息
     * @param userInfoId
     * @return
     */
    UserInfo getUserInfoById(Long userInfoId) throws IllegalArgumentException;

    /**
     * 保存用户基本信息
     * @param userInfo
     */
    void save(UserInfo userInfo);

    /**
     * 分页查询 用户信息和当月绩效信息
     * @param userInfo
     * @param param
     * @return
     */
    List<UserInfoPerfor> getUserIPByParam(UserInfo userInfo, UserInfoPageParam param);

    /**
     * 获取条件下总数
     * @param param
     * @return
     */
    Long getUserIPCount(UserInfoPageParam param);
}
