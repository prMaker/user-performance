package com.performance.service.impl;

import com.performance.common.query.UserInfoPageParam;
import com.performance.dao.mapper.UserInfoDao;
import com.performance.dao.mapper.UserLoginDao;
import com.performance.dao.mapper.UserPerformanceDao;
import com.performance.pojo.UserInfo;
import com.performance.pojo.UserLogin;
import com.performance.pojo.UserPerformance;
import com.performance.pojo.constant.IsDeletedEnum;
import com.performance.service.PermissionService;
import com.performance.pojo.UserInfoPerfor;
import com.performance.service.UserInfoService;
import com.performance.service.exec.AuthenException;
import com.performance.service.exec.ErrorDataException;
import com.performance.service.handler.AppContextFactory;
import com.performance.service.impl.handler.IdsSelectClass;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    private static final Logger _logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private UserLoginDao userLoginDao;
    @Resource
    private UserPerformanceDao userPerformanceDao;
    @Resource
    private PermissionService permissionService;

    public UserInfo getUserInfoById(Long userInfoId) throws IllegalArgumentException {
        return null == userInfoId ? new UserInfo() : userInfoDao.selectById(userInfoId);
    }

    @Transactional
    public void saveOrUpdate(UserInfo userInfo, UserLogin createdLogin, UserLogin currUserLogin) {
        // 同步保存 userLogin中的userInfoId信息
        if(null == userInfo.getUserInfoId()){
            userInfo.setCreatedUserInfoId(createdLogin.getUserInfoId());
            userInfo.setModifiedUserInfoId(createdLogin.getUserInfoId());
            userInfo.setDispostion(currUserLogin.getDispostion());
            userInfo.setIsDeleted(IsDeletedEnum.IsNotDeleted.getCode());
            userInfo.setPid(createdLogin.getUserInfoId());

            // FIXME 查看id是否已经拿到,未拿到重新获取
            userInfoDao.insert(userInfo);
            UserLogin userLogin = new UserLogin();
            userLogin.setLoginId(currUserLogin.getLoginId());
            userLogin.setUserInfoId(userInfo.getUserInfoId());
            userLoginDao.updateById(userLogin);
        } else {
            userInfoDao.updateById(userInfo);
        }
    }

    public List<UserInfoPerfor> getUserIPByParam(UserInfo currUserInfo, UserInfoPageParam param){
        // 1. 查出所有子ID 集合
        addIdsToParam(param);
        if(param.getInfoIds().size() == 0){
            return new ArrayList<>();
        }
        addSortedToParam(param);
        // 2. 查表数据
        List<UserInfoPerfor> userInfoPerfors = userInfoDao.selectForPage(param);

        for(int i = 0; i < userInfoPerfors.size() ; i ++ ){
            try {
                userInfoPerfors.get(i).setPermissionToFix(permissionService.getAuthen(currUserInfo, userInfoPerfors.get(i).getUserPerformance()));
            } catch (AuthenException ex) {
                _logger.error("该条数据不可以审核:" + userInfoPerfors.get(i) + "原因："
                        + ex.getMessage() + "参数：当前登录用户：" + currUserInfo , ex);
                userInfoPerfors.get(i).setPermissionToFix(false);
            }
        }

        return userInfoPerfors;

//        HashMap<String,Object> uPParam = new HashMap<>();
//        uPParam.put("performanceTime", param.getPerformanceTime());
//        uPParam.put("userInfos", userInfos);
//        // TODO  添加分页参数
//        List<UserPerformance> perforPages = userPerformanceDao.selectForPage(uPParam);
//        return getUserInfoPerfors(currUserInfo, param, userInfos, perforPages);
    }

    private void addSortedToParam(UserInfoPageParam param) {
        if(StringUtils.isBlank(param.getOrderField())){
            return;
        }
        switch (param.getOrderField()){
            case "userInfoId": param.setOrderField("user_info.user_info_id");break;
            case "userPerformance.performanceScore" : param.setOrderField("user_performance.performance_score");break;
//            case "createdTime": param.setOrderField("user_info.created_time");break;
//            case "dispostion": param.setOrderField("user_info.dispostion");break;
            default:param.setOrderField(null);
        }
    }

    public Long getUserIPCount(UserInfoPageParam param) {
        addIdsToParam(param);
        if(param.getInfoIds().size() == 0){
            return 0L;
        }
        return userInfoDao.selectPageCount(param);
    }

    private void addIdsToParam(UserInfoPageParam param) {
        List<Long> infoIds = (AppContextFactory.getApplicationContextNotNull()
                .getBean("idsSelectClass", IdsSelectClass.class)).getAllIdsByParam(param);
        param.setInfoIds(infoIds);
    }

    public List<Long> getIdsByPid(Long pid){
        UserInfoPageParam pageParam = new UserInfoPageParam();
        pageParam.setPid(pid);
        return (AppContextFactory.getApplicationContextNotNull()
                .getBean("idsSelectClass", IdsSelectClass.class)).getAllIdsByParam(pageParam);
    }

    @Override
    @Transactional
    public boolean deleteById(UserInfo userInfo) {
        int res = userInfoDao.deleteById(userInfo.getUserInfoId());
        int resLogin = 0;
        if(res > 0){
            resLogin = userLoginDao.deleteById(userInfo.getLoginId());
            if(resLogin < 0){
                throw new RuntimeException("删除失败");
            }
        }
        return res > 0 && resLogin > 0;
    }

    /**
     *
     * 误导方法 查出所有数据之后  再操作
     * 通过 userInfos 和performances 组装页面对象
     *
     * 目前尚不支持排序功能
     * @param currUserInfo
     * @param param
     * @param userInfos
     * @param performances
     * @return
     */
    private List<UserInfoPerfor> getUserInfoPerfors(UserInfo currUserInfo, UserInfoPageParam param
            , List<UserInfo> userInfos, List<UserPerformance> performances)
            throws ErrorDataException{
        List<UserInfoPerfor> userInfoPerfors = new ArrayList<UserInfoPerfor>();
        boolean canLoadPerformanceData = true;// 是否可以加载当前用户的审核信息

        // 1.   数据若不一致性，即表示该月审核信息还没有生成，当前用户审核信息不加载
        if(userInfos.size() != performances.size()){
            _logger.error("查询数据库表user_info和user_performance数据不一致，参数{}", param);
            canLoadPerformanceData = false;
//            throw new ErrorDataException("后台数据不一致，请联系管理员！");
        }

        // 2.   JDK8 排序查出的数据
        List<UserInfo> infoSorted = userInfos.stream()
                .sorted((e1,e2) -> -e1.getUserInfoId().compareTo(e2.getUserInfoId()))
                .collect(Collectors.<UserInfo>toList());
        List<UserPerformance> perforSorted = performances.stream()
                .sorted((e1,e2) -> -e1.getUserInfoId().compareTo(e2.getUserInfoId()))
                .collect(Collectors.toList());
        // 3. 组装数据到userInfoPerfors
        UserInfoPerfor perfor;
        for(int i=0; i< infoSorted.size(); i++){
            perfor = new UserInfoPerfor();
            BeanUtils.copyProperties(infoSorted.get(i), perfor);
            if(canLoadPerformanceData){// 当月审核信息未生成
                perfor.setUserPerformance(perforSorted.get(i));
                try {
                    perfor.setPermissionToFix(permissionService.getAuthen(currUserInfo, perforSorted.get(i)));
                } catch (AuthenException ex) {
                    _logger.error("设置审核权限信息异常:"
                            + ex.getMessage() + "参数：当前登录用户：" + currUserInfo + "，校验审核信息："
                            + perforSorted.get(i), ex);
                    // 校验审核出错 默认设置不可以修改
                    perfor.setPermissionToFix(false);
                }
            }

            userInfoPerfors.add(perfor);
        }
        return userInfoPerfors;
    }

}
