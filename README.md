# 2021-04-04-ddd-implementation-lab

## Development Environment

### IDE - VSCode
[VSCode](https://code.visualstudio.com/)  
- [Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)  
- [Spring Boot Extension Pack](https://marketplace.visualstudio.com/items?itemName=Pivotal.vscode-boot-dev-pack)  
- [Lombok Annotations Support for VS Code](https://marketplace.visualstudio.com/items?itemName=GabrielBB.vscode-lombok)

### IDE - Gitpod
[Gitpod](https://www.gitpod.io/)

## Ubiquitous Language
| 中文 | 英文 | 說明 |
|---------|---------|----------|
| 保險商品    | Product  | 商品  |
| 購物車    | Cart  | 消費者於網站上暫時儲存商品的資料結構  |
| 計畫內容    | Proposal  | 由購物車輸出轉變為建議書  |
| 團險主約    | GroupMain  | 合約中的主約  |
| 團險副約    | GroupAdditional  | 合約中的副約  |
| 保險項目    | Item  | 實際對應的保險對象  |


## Setp1 - initProject

### 下載專案模板
[https://start.spring.io](https://start.spring.io/#!type=gradle-project&language=java&platformVersion=2.4.4.RELEASE&packaging=jar&jvmVersion=11&groupId=com.example&artifactId=demo&name=demo&description=DDD%20implementation%20with%20axon&packageName=com.example.demo&dependencies=web,data-jpa,data-rest,mysql,actuator,prometheus,validation,flyway,lombok)

### 更新
- .gitignore

### 建立 package
建立常用資料夾
``` bash
mkdir config
touch config/application-dev.yml
mkdir doc
touch doc/Architecture.drawio
mkdir docker
touch docker/docker-compose.yml
```

建立 service 共用 package
``` bash
export BasePackage=src/main/java/com/example/demo
mkdir -p ${BasePackage}/configuration
mkdir -p ${BasePackage}/exceptions
mkdir -p ${BasePackage}/shareddomain
mkdir -p ${BasePackage}/shareddomain/rest/dto
```

### 設定 Bounded Context 名稱
``` bash
export BoundedContext=cartms
mkdir -p ${BasePackage}/${BoundedContext}
# 應用層
mkdir -p ${BasePackage}/${BoundedContext}/application/internal
mkdir -p ${BasePackage}/${BoundedContext}/application/internal/commandgateways
mkdir -p ${BasePackage}/${BoundedContext}/application/internal/querygateways
mkdir -p ${BasePackage}/${BoundedContext}/application/internal/sagamanagers
mkdir -p ${BasePackage}/${BoundedContext}/application/internal/outboundservices

# 領域層
mkdir -p ${BasePackage}/${BoundedContext}/domain
mkdir -p ${BasePackage}/${BoundedContext}/domain/model/aggregates
mkdir -p ${BasePackage}/${BoundedContext}/domain/model/entites
mkdir -p ${BasePackage}/${BoundedContext}/domain/model/valueobjects
mkdir -p ${BasePackage}/${BoundedContext}/domain/commands
mkdir -p ${BasePackage}/${BoundedContext}/domain/events
mkdir -p ${BasePackage}/${BoundedContext}/domain/queries
mkdir -p ${BasePackage}/${BoundedContext}/domain/projecttions
mkdir -p ${BasePackage}/${BoundedContext}/domain/queryhandlers

# 基礎層
mkdir -p ${BasePackage}/${BoundedContext}/infrastructure
mkdir -p ${BasePackage}/${BoundedContext}/infrastructure/repositories
mkdir -p ${BasePackage}/${BoundedContext}/infrastructure/brokers

# 介面層
mkdir -p ${BasePackage}/${BoundedContext}/interfaces
mkdir -p ${BasePackage}/${BoundedContext}/interfaces/transform
mkdir -p ${BasePackage}/${BoundedContext}/interfaces/rest/dto
mkdir -p ${BasePackage}/${BoundedContext}/interfaces/eventhandlers
```

### 讓空資料夾也可以 commit
``` bash
touch ${BasePackage}/${BoundedContext}/application/internal/commandgateways/.gitkeep
touch ${BasePackage}/${BoundedContext}/application/internal/querygateways/.gitkeep
touch ${BasePackage}/${BoundedContext}/application/internal/sagamanagers/.gitkeep
touch ${BasePackage}/${BoundedContext}/application/internal/outboundservices/.gitkeep

touch ${BasePackage}/${BoundedContext}/domain/model/aggregates/.gitkeep
touch ${BasePackage}/${BoundedContext}/domain/model/entites/.gitkeep
touch ${BasePackage}/${BoundedContext}/domain/model/valueobjects/.gitkeep
touch ${BasePackage}/${BoundedContext}/domain/commands/.gitkeep
touch ${BasePackage}/${BoundedContext}/domain/events/.gitkeep
touch ${BasePackage}/${BoundedContext}/domain/queries/.gitkeep
touch ${BasePackage}/${BoundedContext}/domain/projecttions/.gitkeep
touch ${BasePackage}/${BoundedContext}/domain/queryhandlers/.gitkeep

touch ${BasePackage}/${BoundedContext}/infrastructure/repositories/.gitkeep
touch ${BasePackage}/${BoundedContext}/infrastructure/brokers/.gitkeep

touch ${BasePackage}/${BoundedContext}/interfaces
touch ${BasePackage}/${BoundedContext}/interfaces/transform/.gitkeep
touch ${BasePackage}/${BoundedContext}/interfaces/rest/dto/.gitkeep
touch ${BasePackage}/${BoundedContext}/interfaces/eventhandlers/.gitkeep
```

### DB 版控

#### 建立 projecttion 對應的 Table
src/main/resources/db/migration/V1.0.0__basic_schema.sql

#### axon 需要的管理表
src/main/resources/db/migration/V1.1.0__axon_schema.sql

### 建立需要的設定檔
src/main/resources/application.yml
``` yml
spring:
  application:
    name: 2021-04-04-ddd-implementation-lab
  profiles:
    active:
    - dev
```

config/application-dev.yml
``` yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/testdb?serverTimezone=UTC&useLegacyDatetimeCode=false&autoReconnect=true&useUnicode=true&characterEncoding=utf8&useSSL=false
    username: user1
    password: pw123456
    hikari:
      connection-init-sql: SET NAMES utf8mb4 COLLATE utf8mb4_unicode_ci;
server:
  error:
    include-message: always
    include-binding-errors: always

axon:
  axonserver:
    enabled: true
    servers: 127.0.0.1

logging:
  level:
    root: info
    com.example.demo: debug
```

### 增加需要的套件
build.gradle
``` gradle
implementation 'org.axonframework:axon-spring-boot-starter:4.4.8'
/*
reactor-core is needed for subscription queries when using Spring Boot 2.0, you'll automatically get reactor-core 3.1, which is fine if you're on Spring Boot 1.5, you would get 2.x, which doesn't work, so you would have to manually set version 3.1
 */
implementation 'io.projectreactor:reactor-core'
// Tool
implementation 'org.apache.commons:commons-lang3:3.12.0'
// Swagger
implementation 'io.springfox:springfox-boot-starter:3.0.0'
implementation 'io.springfox:springfox-swagger-ui:3.0.0'
implementation 'io.springfox:springfox-data-rest:3.0.0'
```

## 首次啟動

### docker-compose
將開發需要用的服務起動起來
``` bash
cd docker
docker-compose -up -d
```

### Axon
開啟 [Axon server](http://127.0.0.1:8024/)

### 透過 Jooq 產生 JPA Entity(option)
啟動 SpringBoot 透過 SpringBoot 內的 flyway 建立 Table Schema 後, 即可將服務停止  

詳細步驟可參考 [Use jOOQ 3.11.11 generation JPA entity](https://blog.samzhu.dev/2019/06/25/Use-jOOQ-3-11-11-generation-JPA-entity/)  

產出之實體請參考 src/main/java/com/example/demo/cartms/domain/projecttions

### 建立 Entity Rest API
將 CartRepository.java & CartProductRepository.java 放置於 src/main/java/com/example/demo/cartms/infrastructure/repositories/
``` java
@RestResource
public interface CartRepository extends JpaRepository<Cart, String> {
}
```

``` java
@RestResource
public interface CartProductRepository extends JpaRepository<CartProduct, Integer> {
}
```

這樣即可完成 RestAPI 的實現

### 新增簡易錯誤訊息回傳格式
參考  
- src/main/java/com/example/demo/shareddomain/rest/dto/ErrorMessageBody.java

### 新增相關組態設定
參考  
- src/main/java/com/example/demo/configuration/AxonConfig.java
- src/main/java/com/example/demo/configuration/ExceptionResolver.java
- src/main/java/com/example/demo/configuration/OpenAPIConfig.java
- src/main/java/com/example/demo/configuration/RepositoryConfig.java

### Setp1Complete
下載 TAG[Setp1Complete] 專案內容已完成上面內容  
  
啟動 docker-compose & springboot 後打開 http://localhost:8080/swagger-ui/ 即可獲得 購物車相關 API 操作資訊

## Setp2 - DDD implementation

### Design Cart Aggregate
先設計 ValueObject  
src/main/java/com/example/demo/cartms/domain/model/valueobjects/Customer.java  

以及 Entity
src/main/java/com/example/demo/cartms/domain/model/entites/CartProduct.java  

接下來設計 Aggregate  
src/main/java/com/example/demo/cartms/domain/model/aggregates/CartAggregate.java  

設計 建立購物車命令  
src/main/java/com/example/demo/cartms/domain/commands/CreateCartCommand.java  

設計 購物車已建立事件  
src/main/java/com/example/demo/cartms/domain/events/CartCreatedEvent.java 

增加 Aggregate 處理 CreateCartCommand 並產生 購物車已建立 的事件
``` java
    @CommandHandler
    public CartAggregate(CreateCartCommand command) {
        log.debug("Aggregate CreateCartCommand aggregate={}, command={}", this, command);
        apply(new CartCreatedEvent(command.getCartNumber(), command.getCustomer(), command.getAmount(),
                new HashMap<String, CartProduct>()));
    }
```

增加 Aggregate 處理 CartCreatedEvent 購物車已建立 的事件
``` java
    @EventSourcingHandler
    public void on(CartCreatedEvent event) {
        log.debug("Aggregate CartCreatedEvent aggregate={}, event={}", this, event);
        this.cartNumber = event.getCartNumber();
        this.customer = event.getCustomer();
        this.amount = event.getAmount();
        this.cartProducts = event.getCartProducts();
    }
```
  
依此類堆, 逐步完成 新增購物車商品 的 命令與 購物車商品已新增 的 事件  
src/main/java/com/example/demo/cartms/domain/commands/AddCartProductCommand.java  
src/main/java/com/example/demo/cartms/domain/events/CartProductAddedEvent.java  

### Design Interface Rest API
設計你希望用來建立購物車 與 新增商品的 Rest API Dto, 可放置於 src/main/java/com/example/demo/cartms/interfaces/rest/dto  
參考  
- src/main/java/com/example/demo/cartms/interfaces/rest/dto/CreateCartDto.java
- src/main/java/com/example/demo/cartms/interfaces/rest/dto/AddCartProductDto.java
  
建立 Assembler 物件協助從 DTO 轉為 Command  
src/main/java/com/example/demo/cartms/interfaces/transform/CartAssembler.java  

建立 RestController  












