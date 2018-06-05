package com.performance.dao.mapper.mapperImpl;

import com.performance.common.query.UserLoginPageParam;
import com.performance.dao.BaseDaoImpl;
import com.performance.dao.mapper.UserLoginDao;
import com.performance.pojo.UserLogin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userLoginDao")
public class UserLoginDaoImpl extends BaseDaoImpl<UserLogin, Long> implements UserLoginDao {

    private static final String NAMESPACE = "com.performance.dao.mapper.UserLoginDao.";
    private static final String SELECT_BY_CONDITION = "selectByCondition";
    private static final String SELECT_FOR_PAGE = "selectForPage";

    public String getNameSpace(String statement) {
        return NAMESPACE + statement;
    }

    public List<UserLogin> getAllLoginUser() {
        return null;
    }

    public UserLogin selectByCondition(UserLogin userLogin) {
        return sqlTemplate.selectOne(getNameSpace(SELECT_BY_CONDITION), userLogin);
    }

    /**
     * NOTICE 测试  使用接口来设置参数能否查到对应的page数据
     * @param pageParam
     * @return
     */
    public List<UserLogin> getForPage(UserLoginPageParam pageParam) {
        return sqlTemplate.selectList(getNameSpace(SELECT_FOR_PAGE), pageParam);
    }

    public int deleteById(UserLogin userLogin) {
        return 0;
    }
}
