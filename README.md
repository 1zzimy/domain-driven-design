# DDD (Domain Driven Design)

## 정의
- 각각의 기능적인 문제 영역들을 도메인이라고 정의하고, 그렇게 정의된 도메인에 대한 로직을 중심으로 설계

## 특징
- 데이터 중심이 아닌 도메인의 모델과 로직에 집중
- 동일한 표현과 단어로 구성된 단일화된 언어체계의 사용
- Software Entity와 Domain 간 개념을 일치하여, 도메인 모델부터 코드까지 항상 함께 움직이는 구조의 모델 지향

## 주요 개념
- Bounded Context : 도메인을 명확하게 구분 짓는 경계
- Context Map : Bounded-Context 간의 관계를 보여주는 맵
- Aggregate : 데이터 변경 단위, 라이프 사이클이 같은 도메인을 모아놓은 집합

### Domain
- 소프트웨어가 해결하고자 하는 문제 영역

> 도메인 모델 설계 규칙
> 1. 도메인 모델은 나눌 수 있는 최대한 작은 단위로 나누어 설계
> 2. 도메인에 종속된 데이터들을 유일하게 관리하고 제어
> 3. 도메인 모델은 도메인에 대한 비지니스 규칙을 포함

### Ubiquitous Language
- 개발자와 도메인 전문가가 공통으로 사용하는 언어로, 코드와 대화에서 일관되게 사용

### Bounded-Context
- 도메인을 명확하게 구분 짓는 경계
- 각 Bounded Context는 독립적으로 개발 및 배포될 수 있음
- 서로 다른 Bounded Context 간의 상호작용은 명확한 인터페이스를 통해 이루어짐

### Context-Map
- up/down 스트림을 한눈에 볼 수 있게 하고 전체적인 흐름을 예상할 수 있게 됨
- Context-Map을 가지고 서비스를 각각 구현

### Aggregate
- 도메인 모델의 일관성을 유지하기 위해 관련된 객체들을 묶어 관리하는 단위
- 하나의 Root Entity를 가지며, 외부에서는 Root Entity를 통해서만 접근 가능
- 해당 접근으로 인한 데이터의 변경은 내부의 모든 객체에 영향을 미침
- 내부 개별 객체의 상호작용보다 외부의 다른 Aggregate나 객체들 사이의 관계를 조금 더 넓은 시야로 바라볼 수 있으며, 제약사항들을 하나의 맥락으로 관리할 수 있게 됨

### Entity
- 고유한 식별자를 가지며, 상태와 행동을 갖는 객체
- 도메인 모델의 핵심 구성 요소

### Value Object
- 고유한 식별자를 가지지 않으며, 불변성을 가지는 객체
- 도메인 모델의 속성을 표현하는 데 사용

### Hexagonal Architecture 
- Port-and-Adaptor Architecture
- port로 들어오는 데이터를 adapter를 통해 알맞게 변환
- 핵심 : **비지니스 로직이 표현 로직이나 데이터 접근 로직에 의존하지 않는 것**

## 프로젝트 구조

이 프로젝트는 DDD(Domain Driven Design)와 Hexagonal Architecture 패턴을 적용한 결제(Payment) 도메인 예제입니다.

```
src/
├── main/
│   ├── java/com/study/ddd/
│   │   ├── DomainDrivenDesignApplication.java    # Spring Boot 메인 애플리케이션
│   │   └── payment/                              # Payment 도메인 (Bounded Context)
│   │       ├── application/                      # 애플리케이션 계층 (Use Case)
│   │       │   ├── dto/                          # 애플리케이션 DTO
│   │       │   │   ├── PaymentRequest.java
│   │       │   │   └── PaymentResponse.java
│   │       │   ├── port/                         # 포트 (인터페이스)
│   │       │   │   ├── in/                       # 인바운드 포트 (Use Case)
│   │       │   │   │   ├── ApprovePaymentUseCase.java
│   │       │   │   │   ├── CanceledPaymentUseCase.java
│   │       │   │   │   └── CreatePaymentUseCase.java
│   │       │   │   └── out/                      # 아웃바운드 포트 (Repository, External Service)
│   │       │   │       ├── ExternalPaymentPort.java
│   │       │   │       ├── LoadPaymentPort.java
│   │       │   │       └── SavePaymentPort.java
│   │       │   └── service/                      # 애플리케이션 서비스
│   │       │       └── PaymentApplicationService.java
│   │       ├── domain/                           # 도메인 계층 (핵심 비즈니스 로직)
│   │       │   ├── event/                        # 도메인 이벤트
│   │       │   │   ├── PaymentApprovedEvent.java
│   │       │   │   └── PaymentCanceledEvent.java
│   │       │   ├── model/                        # 도메인 모델
│   │       │   │   ├── entity/                   # 엔티티
│   │       │   │   │   └── Payment.java
│   │       │   │   └── valueobject/              # 값 객체
│   │       │   │       ├── CurrencyType.java
│   │       │   │       ├── Money.java
│   │       │   │       ├── PaymentId.java
│   │       │   │       ├── PaymentMethod.java
│   │       │   │       └── PaymentStatus.java
│   │       │   └── service/                      # 도메인 서비스
│   │       │       ├── ExchangeRateService.java
│   │       │       └── FeeCalculationService.java
│   │       ├── infrastructure/                   # 인프라스트럭처 계층 (외부 시스템 연동)
│   │       │   ├── config/                       # 설정 클래스
│   │       │   │   ├── JpaConfig.java
│   │       │   │   ├── KafkaConfig.java
│   │       │   │   └── RedisConfig.java
│   │       │   ├── external/                     # 외부 시스템 어댑터
│   │       │   │   ├── adapter/
│   │       │   │   │   └── ExternalPaymentAdapter.java
│   │       │   │   ├── dto/
│   │       │   │   │   ├── ExternalPaymentRequest.java
│   │       │   │   │   └── ExternalPaymentResponse.java
│   │       │   │   └── mapper/
│   │       │   │       └── ExternalPaymentMapper.java
│   │       │   ├── persistence/                  # 데이터 영속성 어댑터
│   │       │   │   ├── adapter/
│   │       │   │   │   └── PaymentPersistenceAdapter.java
│   │       │   │   └── jpa/
│   │       │   │       ├── entity/
│   │       │   │       │   └── PaymentJpaEntity.java
│   │       │   │       └── repository/
│   │       │   │           └── PaymentJpaRepository.java
│   │       │   └── util/
│   │       └── presentation/                     # 프레젠테이션 계층 (API)
│   │           ├── controller/
│   │           │   └── PaymentController.java
│   │           ├── dto/                          # API DTO
│   │           │   ├── PaymentApproveResponse.java
│   │           │   ├── PaymentCancelResponse.java
│   │           │   └── PaymentCreateRequest.java
│   │           ├── exception/
│   │           │   └── GlobalExceptionHandler.java
│   │           └── mapper/
│   │               └── PaymentMapper.java
│   └── resources/
│       └── application.properties                # 애플리케이션 설정
└── test/
    └── java/com/study/ddd/
        └── DomainDrivenDesignApplicationTests.java
```

### 계층별 역할

- **Domain Layer**: 핵심 비즈니스 로직과 규칙을 담당
  - Entity: 고유 식별자를 가진 도메인 객체
  - Value Object: 불변 객체로 도메인 개념 표현
  - Domain Service: 엔티티나 값 객체로 표현하기 어려운 비즈니스 로직
  - Domain Event: 도메인에서 발생하는 중요한 사건

- **Application Layer**: 도메인을 조합하여 사용자 요청을 처리
  - Use Case: 애플리케이션의 주요 기능
  - Port: 도메인과 외부 계층 간의 인터페이스
  - Application Service: 여러 도메인을 조합하여 복잡한 비즈니스 시나리오 처리

- **Infrastructure Layer**: 외부 시스템과의 연동 담당
  - Adapter: 포트 인터페이스의 구현체
  - Config: 외부 라이브러리 설정
  - Persistence: 데이터 영속성 처리

- **Presentation Layer**: 사용자 인터페이스 담당
  - Controller: HTTP 요청/응답 처리
  - DTO: API 계약 정의
  - Mapper: 도메인 객체와 DTO 간 변환
