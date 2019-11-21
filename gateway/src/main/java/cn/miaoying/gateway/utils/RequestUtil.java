package cn.miaoying.gateway.utils;

import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class RequestUtil {
    public static Mono<Void> response(ServerHttpResponse serverHttpResponse, HttpStatus httpStatus, int code, String msg, String format) {
        serverHttpResponse.setStatusCode(httpStatus);
        String res = String.format(format, code, msg);
        byte[] bytes = res.getBytes();
        DataBuffer buffer = serverHttpResponse.bufferFactory().wrap(bytes);
        return serverHttpResponse.writeWith(Flux.just(buffer));
    }

    public static String getPath(ServerWebExchange exchange) {
        String defaultPath = exchange.getRequest().getPath().value();
        LinkedHashSet<URI> uris = (LinkedHashSet) exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        if (uris == null || uris.isEmpty()) {
            return defaultPath;
        }
        Iterator<URI> iterator = uris.iterator();
        URI originURI = iterator.next();
        return originURI.getPath();
    }
}
