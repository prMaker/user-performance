package com.performance.dao.mapper.mapperImpl;

import com.performance.dao.BaseDaoImpl;
import com.performance.dao.mapper.UserPerformanceDao;
import com.performance.pojo.UserPerformance;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("userPerformanceDao")
public class UserPerformanceDaoImpl extends BaseDaoImpl<UserPerformance, Long> implements UserPerformanceDao {

    private static final String NAMESPACE = "com.performance.dao.mapper.UserPerformanceDao.";
    public String getNameSpace(String statement) {
        return NAMESPACE + statement;
    }

    public int deleteById(UserPerformance performance) {
        return sqlTemplate.update(getNameSpace("deleteById"), performance);
    }

    public int lockPerformance(UserPerformance performance) {
        return sqlTemplate.update(getNameSpace("lockPerformance"), performance);
    }

    public List<UserPerformance> selectForPage(HashMap<String, Object> userInfos) {
        return sqlTemplate.selectList(getNameSpace("selectForPage"), userInfos);
    }

    public UserPerformance getUserPerforByCond(UserPerformance userPerformance) {
        return sqlTemplate.selectOne(getNameSpace("getUserPerforByCond"), userPerformance);
    }
}
