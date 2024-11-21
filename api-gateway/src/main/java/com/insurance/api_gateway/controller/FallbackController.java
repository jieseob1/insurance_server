package com.insurance.api_gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono; // 해당 부분 어떻게 동작하늕 ㅣ확인하기
// Mono는 리액티브 프로그래밍에서 단일 값을 방출하는 리액티브 스트림을 나타내는 리액티브 타입

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    // 사용자 서비스 폴백 메서드
    @GetMapping("/user")
    public Mono<String> userServiceFallback() {
        return Mono.just("사용자 서비스가 현재 응답할 수 없습니다. 잠시 후 다시 시도해주세요.");
    }

    // 클레임 서비스 폴백 메서드
    @GetMapping("/claim")
    public Mono<String> claimServiceFallback() {
        return Mono.just("클레임 서비스가 현재 응답할 수 없습니다. 잠시 후 다시 시도해주세요.");
    }

    // 문서 처리 서비스 폴백 메서드
    @GetMapping("/document")
    public Mono<String> documentServiceFallback() {
        return Mono.just("문서 처리 서비스가 현재 응답할 수 없습니다. 잠시 후 다시 시도해주세요.");
    }
}