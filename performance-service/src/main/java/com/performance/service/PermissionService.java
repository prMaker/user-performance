package com.performance.service;

import com.performance.pojo.UserInfo;
import com.performance.pojo.UserPerformance;
import com.performance.service.exec.AuthenException;

public interface PermissionService {

    /**
     * 校验单条数据当前用户是否可以修改
     *
     * 校验策略：
     * 1.配置超级管理员，超级管理员可以修改，若是超级管理员修改之后，其他人不可以修改
     * 2.上级修改完后下级不可以修改，上级没有修改的数据下级可以修改
     * 3.锁定的数据 除了超级管理员可以修改其他人不可以修改  同时修改之后的数据重新锁定，
     * 其他人不可以修改
     * @return
     * @throws AuthenException
     */
    boolean getAuthen(UserInfo localUserInfo, UserPerformance performance) throws AuthenException;
}
