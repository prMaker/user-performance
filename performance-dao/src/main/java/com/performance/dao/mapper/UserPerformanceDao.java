package com.performance.dao.mapper;

import com.performance.dao.BaseDao;
import com.performance.pojo.UserPerformance;

import java.util.HashMap;
import java.util.List;

public interface UserPerformanceDao extends BaseDao<UserPerformance, Long> {

    int lockPerformance(UserPerformance performance);

    /**
     * 根据 userInfos中的id查找对应UserPerformance
     * @param userInfos
     * @return
     */
    List<UserPerformance> selectForPage(HashMap<String, Object> userInfos);

    /**
     * 根据条件查询
     * @param userPerformance
     * @return
     */
    UserPerformance getUserPerforByCond(UserPerformance userPerformance);
}
