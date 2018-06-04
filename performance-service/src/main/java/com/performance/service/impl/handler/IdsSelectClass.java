package com.performance.service.impl.handler;

import com.performance.common.query.UserInfoPageParam;
import com.performance.dao.mapper.UserInfoDao;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 递归获取所有PID 集合
 */
@Service("idsSelectClass")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class IdsSelectClass{

    @Resource
    private UserInfoDao userInfoDao;
    private List<Long> ids = new LinkedList<>();

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public List<Long> getAllIdsByParam(UserInfoPageParam param) {
        ids.add(param.getPid());
        List<Long> pids = new ArrayList<>();
        pids.add(param.getPid());
        getCircleIds(pids);
        return ids;
    }

    /**
     * NOTICE 所有递归方法  对应数据库信息确定完毕才能使用
     * @param pids
     * @return
     */
    private List<Long> getCircleIds(List<Long> pids) {
        List<Long> infoIds = userInfoDao.selectForIdsByParam(pids);
        if(CollectionUtils.isEmpty(infoIds)){
            return null;
        }
        ids.addAll(infoIds);
        return getCircleIds(infoIds);
    }
}