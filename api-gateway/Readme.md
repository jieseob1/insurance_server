### API Gateway Service

### 역할
- 클라이언트의 요청을 받아 각 서비스로 전달하고 응답을 받아 클라이언트에 반환하는 역할
- 인증 및 권한 검사 (인증 인가)
- 기본적인 토큰 유효성 검증
- 로드 밸런싱 맟 라우팅 역할
- 서비스 간 통신 최적화 => 어째서 이런 역할을 할 수 있지?
- 데이터 보안 및 무결성 보장 => 어째서 이런 역할을 할 수 있지?


### 사용 기술
- Spring Cloud Gateway
- Eureka
- JWT

### Spring Cloud
- 클라우드 환경에서 서비스 간 통신을 최적화하고 관리하는 데 도움을 주는 프레임워크

### Eureka
- 서비스 레지스트리 역할
- 서비스 등록 및 검색 역할



1. **서비스 라우팅**:

- 각 MSA 서비스별로 명확한 경로 구분 (/user-service/**, /auth-service/**)
- 서비스별 허용 HTTP 메서드 지정

- LoadBalancer를 통한 서비스 디스커버리 연동 (lb://)

- Eureka 설정:
  - Discovery Service 연동 (도커 네트워크 고려하여 discovery-service 호스트명 사용)
  - IP 주소 기반 서비스 등록 설정

2. 모니터링 및 관리:
  - Actuator 엔드포인트 활성화 => 서비스 상태 모니터링
  - Circuit Breaker 상태 모니터링 설정 => 서비스 장애 대응
- 회복성 패턴:
  - Circuit Breaker 상세 설정 => 서비스 장애 대응
  - Retry 패턴 추가 => 서비스 장애 대응
  - 각 서비스별 독립적인 Circuit Breaker 인스턴스 => 서비스 장애 대응

3. 로깅 설정:
  - Gateway와 Netty 디버그 로깅 활성화 => 서비스 디버깅

4. 이 구성은 다음과 같은 MSA 환경에 최적화되어 있습니다:
  - 서비스 간 독립적인 라우팅
  - 장애 격리
  - 서비스 디스커버리 => 서비스 검색
  - 로드밸런싱 => 서비스 부하 분산
  - 상태 모니터링
  - 회복성 패턴 적용

각 서비스의 엔드포인트는 다음과 같이 호출할 수 있습니다:

- User Service: http://gateway:8000/user-service/users/**
- Auth Service: http://gateway:8000/auth-service/auth/**