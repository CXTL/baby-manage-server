package com.dupake.gateway.filter;

import com.dupake.gateway.config.GatewayLoggerProfile;
import com.dupake.gateway.filter.util.GatewayLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@ConditionalOnProperty(value = "gateway.log.enable", matchIfMissing = true)
public class LowerRequestRecorderGlobalFilter implements GlobalFilter, Ordered {
    private Logger logger = LoggerFactory.getLogger(GatewayLoggerProfile.LOGGER_NAME);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest originalRequest = exchange.getRequest();
        URI originalRequestUrl = originalRequest.getURI();

        //只记录http的请求
        String scheme = originalRequestUrl.getScheme();
        if ((!"http".equals(scheme) && !"https".equals(scheme))) {
            return chain.filter(exchange);
        }

        String upgrade = originalRequest.getHeaders().getUpgrade();
        if ("websocket".equalsIgnoreCase(upgrade)) {
            return chain.filter(exchange);
        }

        // 在 GatewayFilter 之前执行， 此时的request时最初的request
        RecorderServerHttpRequestDecorator request = new RecorderServerHttpRequestDecorator(exchange.getRequest());

        // 此时的response时 发送回客户端的 response
        RecorderServerHttpResponseDecorator response = new RecorderServerHttpResponseDecorator(exchange.getResponse());

        ServerWebExchange ex = exchange.mutate()
                .request(request)
                .response(response)
                .build();

        return GatewayLogUtil.recorderOriginalRequest(ex)
                .then(Mono.defer(() -> chain.filter(ex)))
                .then(Mono.defer(() -> finishLog(ex)));
    }

    private Mono<Void> finishLog(ServerWebExchange ex) {
        return GatewayLogUtil.recorderResponse(ex)
                .doOnSuccess(x -> logger.info(GatewayLogUtil.getLogData(ex) + "\n\n\n"));
    }

    @Override
    public int getOrder() {
        //在GatewayFilter之前执行
        return - 1;
    }
}
