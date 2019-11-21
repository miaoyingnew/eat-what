package cn.miaoying.gateway.share.config;

import cn.miaoying.gateway.share.model.UserPrivilegesData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
@Slf4j
public class UserPrivilegesDataConfig {
    @Bean(name = "userPrivilegesData")
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserPrivilegesData getUserPrivilegesData() {
        return new UserPrivilegesData();
    }
}