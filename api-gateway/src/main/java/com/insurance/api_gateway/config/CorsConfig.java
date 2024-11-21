package com.insurance.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration  // 스프링 설정 클래스임을 명시
public class CorsConfig {
    
    @Bean  // 스프링 빈으로 등록
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Arrays.asList("*"));  // 모든 오리진 허용
        corsConfig.setMaxAge(3600L);  // pre-flight 요청 캐시 시간 설정 (1시간)
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));  // 허용할 HTTP 메서드
        corsConfig.setAllowedHeaders(Arrays.asList("*"));  // 모든 헤더 허용

        // CORS 설정을 적용할 URL 패턴 설정
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);  // 모든 경로에 CORS 설정 적용

        return new CorsWebFilter(source);  // CORS 필터 생성 및 반환
    }
}