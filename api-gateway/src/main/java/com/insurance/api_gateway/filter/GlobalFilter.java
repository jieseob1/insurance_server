package com.insurance.api_gateway.filter;

// 로깅을 위한 SLF4J 임포트
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// Spring Cloud Gateway의 필터 관련 클래스들
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
// HTTP 요청/응답을 처리하기 위한 리액티브 클래스들
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
// 스프링 컴포넌트 어노테이션
import org.springframework.stereotype.Component;
// 리액티브 프로그래밍을 위한 Mono 클래스
import reactor.core.publisher.Mono;

@Component  // 스프링 빈으로 등록
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {
    // 로깅을 위한 Logger 인스턴스 생성
    private static final Logger logger = LoggerFactory.getLogger(GlobalFilter.class);

    // 생성자 - Config 클래스를 부모 클래스에 전달
    public GlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        // GatewayFilter 인터페이스 구현
        return ((exchange, chain) -> {
            // 현재 요청과 응답 객체 가져오기
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            // 기본 메시지 로깅
            logger.info("Global Filter baseMessage: {}", config.getBaseMessage());
            
            // 사전 로깅이 활성화된 경우 요청 ID 로깅
            if (config.isPreLogger()) {
                logger.info("Global Filter Start: request id -> {}", request.getId());
            }
            
            // 필터 체인 실행 및 사후 처리
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                // 사후 로깅이 활성화된 경우 응답 상태 코드 로깅
                if (config.isPostLogger()) {
                    logger.info("Global Filter End: response code -> {}", response.getStatusCode());
                }
            }));
        });
    }

    // 필터 설정을 위한 내부 설정 클래스
    public static class Config {
        private String baseMessage;  // 기본 메시지
        private boolean preLogger;   // 사전 로깅 활성화 여부
        private boolean postLogger;  // 사후 로깅 활성화 여부

        // getter와 setter 메서드들
        public String getBaseMessage() {
            return baseMessage;
        }

        public void setBaseMessage(String baseMessage) {
            this.baseMessage = baseMessage;
        }

        public boolean isPreLogger() {
            return preLogger;
        }

        public void setPreLogger(boolean preLogger) {
            this.preLogger = preLogger;
        }

        public boolean isPostLogger() {
            return postLogger;
        }

        public void setPostLogger(boolean postLogger) {
            this.postLogger = postLogger;
        }
    }
}