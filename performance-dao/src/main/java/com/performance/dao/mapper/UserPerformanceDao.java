package com.performance.dao.mapper;

import com.performance.dao.BaseDao;
import com.performance.pojo.UserPerformance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserPerformanceDao extends BaseDao<UserPerformance, Long> {

    /**
     * 锁定数据
     * @param param
     * @return
     */
    int lockPerformance(Map<String, Object> param);

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

    /**
     * 根据INFO_IDS集合查询performance中的数量
     * @param childInfos
     * @return
     */
    Long selectCountByInfoIDs(List<Long> childInfos);
}
