package cn.miaoying.gateway.share.impl;

import cn.miaoying.gateway.share.common.FeatureTypeEnum;
import cn.miaoying.gateway.share.model.*;
import cn.miaoying.gateway.share.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserPrivilegesData getPermissions(String token) {
        // TODO: 根据从网关拦截到，请求头里面的token获取用户信息
        UserPrivilegesData userPrivilegesData = new UserPrivilegesData();
        userPrivilegesData.setDataRangeList(setDataRangeList());
        userPrivilegesData.setFeatureList(setFeatureList());
        userPrivilegesData.setRoleList(setRoleList());
        userPrivilegesData.setUserLoginData(setUserLoginData());
        return userPrivilegesData;
    }

    private List<DataRange> setDataRangeList() {
        DataRange dataRange = new DataRange();
        dataRange.setDataId("dataId1");
        dataRange.setDataRange(1);
        dataRange.setModelId(1);
        dataRange.setRangeName("name1");
        return Arrays.asList(dataRange);
    }

    private List<Feature> setFeatureList() {
        Feature feature = new Feature();
        feature.setFeatureId(1);
        feature.setFeatureName("featureName1");
        feature.setFeatureType(FeatureTypeEnum.MENU);
        feature.setModelId(1);
        return Arrays.asList(feature);
    }

    private List<Role> setRoleList() {
        Role role = new Role();
        role.setModelId(1);
        role.setModelId(1);
        role.setRoleName("roleName1");
        return Arrays.asList(role);
    }

    private UserLoginData setUserLoginData() {
        UserLoginData userLoginData = new UserLoginData();
        userLoginData.setLoginName("loginName1");
        userLoginData.setPhone("18777777777");
        userLoginData.setUserId(1);
        return userLoginData;
    }
}