package com.performance.service;

import com.performance.common.query.UserInfoPageParam;
import com.performance.pojo.UserInfo;
import com.performance.pojo.UserInfoPerfor;
import com.performance.pojo.UserLogin;
import com.performance.service.exec.ErrorDataException;

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
     * @param createdLogin
     * @param currUserLogin
     */
    void saveOrUpdate(UserInfo userInfo, UserLogin createdLogin, UserLogin currUserLogin);

    /**
     * 分页查询 用户信息和当月绩效信息
     * @param userInfo
     * @param param
     * @return
     * @throws ErrorDataException 查询数据库信息出错时抛出
     */
    List<UserInfoPerfor> getUserIPByParam(UserInfo userInfo, UserInfoPageParam param);

    /**
     * 获取条件下总数
     * @param param
     * @return
     */
    Long getUserIPCount(UserInfoPageParam param);

    /**
     * 根据父ID 获取子ID 集合
     * @param pid
     * @return
     */
    public List<Long> getIdsByPid(Long pid);
}
