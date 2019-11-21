package cn.miaoying.gateway.filter;

import cn.miaoying.gateway.common.HeaderConstant;
import cn.miaoying.gateway.share.model.UserPrivilegesData;
import cn.miaoying.gateway.share.service.UserService;
import cn.miaoying.gateway.utils.RequestUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Slf4j
@Component
public class RequestFilter implements GlobalFilter, Ordered {

    private static final String TOKEN = "token";

    private static final String ACTUATOR_HEALTH = "/actuator/health";

    @Autowired
    private UserService userService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = RequestUtil.getPath(exchange);
        log.info("RequestFilter.filter.path = " + path);
        ServerHttpRequest request = exchange.getRequest();
        String token = request.getHeaders().getFirst(TOKEN);
        log.info("RequestFilter.filter.token = " + token);
        boolean specialUrl = StringUtils.endsWithIgnoreCase(path, ACTUATOR_HEALTH);
        boolean tokenIsNull = StringUtils.isEmpty(token);
        if (tokenIsNull && specialUrl) {
            log.warn("RequestFilter.filter.token is null, path is = " + path);
            // 对某些特定 url ，
            return chain.filter(exchange);
        }
        // 根据 token 获取用户权限信息
        UserPrivilegesData userPrivilegesData = userService.getPermissions(token);
        log.info("RequestFilter.filter.userPrivilegesData = " + userPrivilegesData);

        String encodeStr = JSON.toJSONString(userPrivilegesData);
        try {
            encodeStr = URLEncoder.encode(encodeStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }
        return chain.filter(
                exchange.mutate().request(
                        request.mutate()
                                .header(HeaderConstant.SESSION, encodeStr)
                                .build())
                        .build());
    }

    @Override
    public int getOrder() {
        return 100;
    }
}