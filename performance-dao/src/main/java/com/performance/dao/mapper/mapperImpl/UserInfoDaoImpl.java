package com.performance.dao.mapper.mapperImpl;

import com.performance.common.query.UserInfoPageParam;
import com.performance.dao.BaseDaoImpl;
import com.performance.dao.mapper.UserInfoDao;
import com.performance.pojo.UserInfo;
import com.performance.pojo.UserInfoPerfor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("userInfoDao")
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfo,Long> implements UserInfoDao{

    private static final String NAMESPACE = "com.performance.dao.mapper.UserInfoDao.";
    private static final String SELECT_ALL_CHILD = "selectAllChild";
    private static final String SELECT_FOR_PAGE = "selectForPage";
    private static final String CONTAINS_CHILD = "containsChild";
    private static final String SELECT_FOR_IDS_BY_PARAM = "selectForIdsByParam";
    private static final String SELECT_PAGE_COUNT = "selectPageCount";

    public String getNameSpace(String statement) {
        return NAMESPACE + statement;
    }

    public int deleteById(UserInfo userInfo) {
        return 0;
    }

    public List<UserInfo> selectAllChild(Long userInfoId) {
        return sqlTemplate.selectList(getNameSpace(SELECT_ALL_CHILD), userInfoId);
    }

    public UserInfo containsChild(Map<String, Long> query) {
        return sqlTemplate.selectOne(CONTAINS_CHILD, query);
    }

    public List<UserInfoPerfor> selectForPage(UserInfoPageParam param) {
        return sqlTemplate.selectList(getNameSpace(SELECT_FOR_PAGE), param);
    }

    public Long selectPageCount(UserInfoPageParam param) {
        return sqlTemplate.selectOne(getNameSpace(SELECT_PAGE_COUNT), param);
    }

    public List<Long> selectForIdsByParam(List<Long> list) {
        return sqlTemplate.selectList(getNameSpace(SELECT_FOR_IDS_BY_PARAM), list);
    }

    public UserInfo selectByLoginId(Long loginId) {
        return sqlTemplate.selectOne(getNameSpace("selectByLoginId"), loginId);
    }
}
