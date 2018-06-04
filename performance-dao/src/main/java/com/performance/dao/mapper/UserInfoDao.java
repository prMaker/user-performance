package com.performance.dao.mapper;

import com.performance.common.query.UserInfoPageParam;
import com.performance.dao.BaseDao;
import com.performance.pojo.UserInfo;
import com.performance.pojo.UserInfoPerfor;

import java.util.List;
import java.util.Map;

public interface UserInfoDao extends BaseDao<UserInfo,Long>{

    List<UserInfo> selectAllChild(Long userInfoId);

    UserInfo containsChild(Map<String, Long> query);

    /**
     * 根据param查询页面数据
     * @param param
     * @return
     */
    List<UserInfoPerfor> selectForPage(UserInfoPageParam param);

    /**
     * 根据条件查询页面数据
     * @param param
     * @return
     */
    Long selectPageCount(UserInfoPageParam param);

    /**
     * 根据条件递归查出所有父ID的数据
     * @param list
     * @return
     */
    List<Long> selectForIdsByParam(List<Long> list);

    /**
     * 根据 loginID 找
     * @param loginId
     * @return
     */
    UserInfo selectByLoginId(Long loginId);

    /**
     * 根据父ID 查找
     * @param pageParam
     * @return
     */
    List<Long> selectChildNOPerFor(UserInfoPageParam pageParam);
}
