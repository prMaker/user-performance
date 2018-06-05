package com.performance.service;

import com.performance.pojo.UserInfo;
import com.performance.pojo.UserPerformance;

import java.util.List;

public interface UserPerformanceService {

    /**
     * 条件判断
     * 1.如果是本人 则是更新
     * 2.如果不是本人 则是新增并删除之前记录，防止并发修改人员绩效问题
     * @param userInfo
     * @param userPerformance
     * @return
     */
    void save(UserInfo userInfo, UserPerformance userPerformance);

    /**
     * 批量创建审核数据
     * 1.如果数据量大，可以使用带返回值  线程池实现快速生成
     * 2.存在数据事务问题
     * @param performanceTime
     * @param currUserInfo
     */
    void batchSave(String performanceTime, UserInfo currUserInfo) throws Exception;

    /**
     * 审核后锁定数据
     * @param userPerformance
     * @param localUserInfo
     */
    void lockPerformance(UserPerformance userPerformance, UserInfo localUserInfo);

    /**
     * 获取当前审核条件信息
     * @param userPerformance
     * @return
     */
    UserPerformance getUserPerforByCond(UserPerformance userPerformance);

    /**
     * 根据INFO_IDS 集合获取userPerformance中的数据数量
     * @param childInfos
     * @param perfromanceTime
     * @return
     */
    Long selectCountByInfoIDs(List<Long> childInfos, String performanceTime);
}
