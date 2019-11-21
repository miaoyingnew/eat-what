package cn.miaoying.gateway.share.service;

import cn.miaoying.gateway.share.model.UserPrivilegesData;

public interface UserService {
    UserPrivilegesData getPermissions(String token);
}
