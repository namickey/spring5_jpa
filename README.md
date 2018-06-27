# spring5_jpa

* SpringMVCとJPAを学ぶ。  
* トランザクションを学ぶ。  
* 親テーブル、子テーブルを学ぶ。  
* 排他制御（悲観的、楽観的）を学ぶ。  
* デッドロック防止を学ぶ。  
* lombokを知る。  
* gradleに挑戦。  
* Hibernateで自動テーブル作成を使う。  
* etc  

## Dependencies
* org.springframework:spring-webmvc:5.0.7.RELEASE  
* org.springframework.data:spring-data-jpa:2.0.8.RELEASE  
* org.hibernate:hibernate-entitymanager:5.3.1.Final  
* org.hibernate.validator:hibernate-validator:6.0.10.Final  
* javax.servlet:jstl:1.2  
* org.slf4j:slf4j-nop:1.7.6  
* org.postgresql:postgresql:42.2.2  
* javax.servlet:javax.servlet-api:3.1.0  
* org.projectlombok:lombok:1.16.20  

## Tree
```
├── README.md
├── build.gradle
└── src
    └── main
        ├── java
        │   └── demo
        │       ├── controller
        │       │   ├── ProjectForm.java
        │       │   ├── ProjectParentAndChildController.java
        │       │   └── ProjectTransactionController.java
        │       ├── entity
        │       │   ├── Member.java
        │       │   └── Project.java
        │       ├── repository
        │       │   ├── MemberRepository.java
        │       │   └── ProjectRepository.java
        │       └── service
        │           ├── MemberService.java
        │           └── ProjectService.java
        ├── resources
        │   └── META-INF
        │       ├── messages
        │       │   ├── messages.properties
        │       │   └── messages_ja.properties
        │       └── spring
        │           ├── applicationContext-beans.xml
        │           └── applicationContext-webmvc.xml
        └── webapp
            ├── WEB-INF
            │   ├── views
            │   │   └── project
            │   │       ├── parentAndChild.jsp
            │   │       └── transaction.jsp
            │   └── web.xml
            └── index.jsp
```
## Install gradle
https://gradle.org/  

Windows10にGradleをインストール  
https://qiita.com/quwahara/items/9c4e5fbda421cfcb09ad  
Gradle入門  
https://qiita.com/vvakame/items/83366fbfa47562fafbf4  

## Setting Lombok
https://projectlombok.org/  

Eclipse：lombokインストール手順  
https://web-dev.hatenablog.com/entry/eclipse/lombok  
【Java】Lombokで冗長コードを削減しよう  
https://www.casleyconsulting.co.jp/blog/engineer/107/  

## Setting Database
### for PostgreSQL
* masterブランチをチェックアウト。  
* masterブランチには、PostgreSQLへ接続設定済み。以下の接続情報は環境に合った内容に変更すること。  
```https://github.com/namickey/spring5_jpa/blob/master/src/main/resources/META-INF/spring/applicationContext-beans.xml
<property name="jpaProperties">
    <props>
        <prop key="hibernate.hbm2ddl.auto">create-drop</prop>
        <prop key="hibernate.dialect">org.hibernate.dialect.PostgreSQLDialect</prop>
    </props>
</property>

接続情報
<property name="driverClassName" value="org.postgresql.Driver" />
<property name="url" value="jdbc:postgresql://localhost:5432/postgres" />
<property name="username" value="postgres" />
<property name="password" value="postgres" />
```

```https://github.com/namickey/spring5_jpa/blob/master/build.gradle
compile ("org.postgresql:postgresql:42.2.2")
```


### for Oracle
* oracleブランチをチェックアウト。  
* oracleブランチには、Oracleへ接続設定済み。以下の接続情報は環境に合った内容に変更すること。  
```https://github.com/namickey/spring5_jpa/blob/oracle/src/main/resources/META-INF/spring/applicationContext-beans.xml
<property name="jpaProperties">
    <props>
        <prop key="hibernate.hbm2ddl.auto">create-drop</prop>
        <prop key="hibernate.dialect">org.hibernate.dialect.DataDirectOracle9Dialect</prop>
    </props>
</property>

接続情報
<property name="driverClassName" value="oracle.jdbc.driver.OracleDriver" />
<property name="url" value="jdbc:oracle:thin:@localhost:1521:xe" />
<property name="username" value="sample" />
<property name="password" value="sample" />
```

```https://github.com/namickey/spring5_jpa/blob/oracle/build.gradle
compile ("com.oracle:ojdbc6:12.1.0.1-atlassian-hosted")
```

## jpaProperties
* 「create-drop」を指定しているので、事前にデータベースにテーブルを作成しておく必要無し。
```種類
create - スキーマの作成
update - 既存のスキーマの更新
validate - 既存のスキーマを検証する
create-drop - セッションの開始と終了時にスキーマを自動的に作成して削除する
```

* 組み込みTomcat起動時に定義されているEntityに合わせて、テーブルが作成される。組み込みTomcat停止時にはテーブルが削除される。
```
<property name="jpaProperties">
    <props>
        <prop key="hibernate.hbm2ddl.auto">create-drop</prop>
        <prop key="hibernate.dialect">org.hibernate.dialect.DataDirectOracle9Dialect</prop>
    </props>
</property>
```

## Execute Command
Open the Command Prompt.  
```
gradle tomcatRun
```
http://localhost:8080/demo  

## Stop Command
```
Ctrl + C
```
