package cn.miaoying.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@Slf4j
public class CorsFilter {
    @Value("${health.gateway.access.control.allow.origin}")
    private String allowOrigin;

    @Value("${health.gateway.access.control.allow.method}")
    private String allowMethod;

    private static final String MAX_AGE = String.valueOf(3600 * 24 * 7);

    @Bean
    public WebFilter setCorsFilter() {

        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();

            if (CorsUtils.isCorsRequest(request)) {
                HttpHeaders reqHeaders = request.getHeaders();

                ServerHttpResponse response = ctx.getResponse();
                HttpHeaders resHeaders = response.getHeaders();

                //测试服务器，由于开发人员会本地调用，所以先设置成允许*访问
                if ("*".equals(allowOrigin)) {
                    resHeaders.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
                } else {
                    String origin = reqHeaders.getOrigin();
                    origin = allowOrigin(origin);
                    if (origin != null) {
                        resHeaders.add("Access-Control-Allow-Origin", origin);
                    }
                }

                resHeaders.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, allowMethod);
                resHeaders.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, MAX_AGE);

                HttpMethod method = reqHeaders.getAccessControlRequestMethod();
                if (method != null) {
                    resHeaders.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, method.name());
                }

                List<String> accessControlRequestHeaders = reqHeaders.getAccessControlRequestHeaders();
                log.info("ACCESS_CONTROL_ALLOW_HEADERS=======" + String.join(",", accessControlRequestHeaders));
                resHeaders.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, String.join(",", accessControlRequestHeaders));

                if (request.getMethod() == HttpMethod.OPTIONS) {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }
            return chain.filter(ctx);
        };
    }

    private String allowOrigin(String origin) {

        List<String> ALLOWED_ORIGINS = new ArrayList<String>(
                Arrays.asList(allowOrigin.split(",")));

        if (origin == null) {
            return null;
        }
        for (String ao : ALLOWED_ORIGINS) {
            if (origin.toLowerCase().contains(ao)) {
                return origin;
            }
        }
        return null;
    }
}