# 기본 docker-compose 파일 설정
COMPOSE_FILE = docker-compose.m1.yaml

# 모든 서비스 관련 명령어
all-up:
	docker-compose -f $(COMPOSE_FILE) up --build

all-down:
	docker-compose -f $(COMPOSE_FILE) down

all-restart:
	docker-compose -f $(COMPOSE_FILE) restart

# 개별 서비스 실행
eureka:
	docker-compose -f $(COMPOSE_FILE) up --build eureka-server

gateway:
	docker-compose -f $(COMPOSE_FILE) up --build api-gateway

user:
	docker-compose -f $(COMPOSE_FILE) up --build user-service

claim:
	docker-compose -f $(COMPOSE_FILE) up --build claim-service

document:
	docker-compose -f $(COMPOSE_FILE) up --build document-processing-service

mongodb:
	docker-compose -f $(COMPOSE_FILE) up --build mongodb

# 개별 서비스 중지
stop-eureka:
	docker-compose -f $(COMPOSE_FILE) stop eureka-server

stop-gateway:
	docker-compose -f $(COMPOSE_FILE) stop api-gateway

stop-user:
	docker-compose -f $(COMPOSE_FILE) stop user-service

stop-claim:
	docker-compose -f $(COMPOSE_FILE) stop claim-service

stop-document:
	docker-compose -f $(COMPOSE_FILE) stop document-processing-service

# 로그 보기
logs-eureka:
	docker-compose -f $(COMPOSE_FILE) logs -f eureka-server

logs-gateway:
	docker-compose -f $(COMPOSE_FILE) logs -f api-gateway

logs-user:
	docker-compose -f $(COMPOSE_FILE) logs -f user-service

logs-claim:
	docker-compose -f $(COMPOSE_FILE) logs -f claim-service

logs-document:
	docker-compose -f $(COMPOSE_FILE) logs -f document-processing-service

# 컨테이너 상태 확인
ps:
	docker-compose -f $(COMPOSE_FILE) ps

# 모든 컨테이너와 네트워크 삭제
clean:
	docker-compose -f $(COMPOSE_FILE) down --rmi all --volumes --remove-orphans

# 의존성을 명시적으로 처리하는 명령어들
user-with-deps: eureka gateway
	docker-compose -f $(COMPOSE_FILE) up --build user-service

claim-with-deps: eureka gateway
	docker-compose -f $(COMPOSE_FILE) up --build claim-service

document-with-deps: eureka gateway
	docker-compose -f $(COMPOSE_FILE) up --build document-processing-service

# 안전한 중지 명령어 (의존성 역순으로 중지)
safe-stop-eureka: stop-user stop-claim stop-document stop-gateway
	docker-compose -f $(COMPOSE_FILE) stop eureka-server

safe-stop-gateway: stop-user stop-claim stop-document
	docker-compose -f $(COMPOSE_FILE) stop api-gateway

# 도움말
help:
	@echo "사용 가능한 명령어:"
	@echo "  make all-up        : 모든 서비스 실행"
	@echo "  make all-down      : 모든 서비스 중지"
	@echo "  make all-restart   : 모든 서비스 재시작"
	@echo "  make eureka        : Eureka 서버 실행"
	@echo "  make gateway       : API Gateway 실행"
	@echo "  make user          : User 서비스 실행"
	@echo "  make claim         : Claim 서비스 실행"
	@echo "  make document      : Document 서비스 실행"
	@echo "  make stop-[서비스명] : 특정 서비스 중지"
	@echo "  make logs-[서비스명] : 특정 서비스 로그 보기"
	@echo "  make ps            : 컨테이너 상태 확인"
	@echo "  make clean         : 모든 컨테이너와 이미지 삭제"
	@echo "  make user-with-deps  : User 서비스와 의존성 있는 서비스들 함께 실행"
	@echo "  make safe-stop-eureka: 의존성 있는 서비스들을 안전하게 중지 후 Eureka 중지" 