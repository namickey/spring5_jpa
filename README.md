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
## Flow
![フロー](koara.png)

## Install java
* jdk8がインストールされていること  

## Install gradle
https://gradle.org/  

Windows10にGradleをインストール  
https://qiita.com/quwahara/items/9c4e5fbda421cfcb09ad  
Gradle入門  
https://qiita.com/vvakame/items/83366fbfa47562fafbf4  

## Install and Setting Lombok
https://projectlombok.org/  

Eclipse：lombokインストール手順  
https://web-dev.hatenablog.com/entry/eclipse/lombok  
【Java】Lombokで冗長コードを削減しよう  
https://www.casleyconsulting.co.jp/blog/engineer/107/  

## Clone Github repository
```
git clone https://github.com/namickey/spring5_jpa.git
```

## Setting Database
### for PostgreSQL
* masterブランチをチェックアウト。※clone直後はmasterブランチになっている  
* masterブランチには、PostgreSQLへ接続設定済み。以下の接続情報は環境に合った内容に変更すること。  

https://github.com/namickey/spring5_jpa/blob/master/src/main/resources/META-INF/spring/applicationContext-beans.xml
```
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

https://github.com/namickey/spring5_jpa/blob/master/build.gradle
```
compile ("org.postgresql:postgresql:42.2.2")
```


### for Oracle
* oracleブランチをチェックアウト。※masterブランチからoracleブランチへ切り替える  
* oracleブランチには、Oracleへ接続設定済み。以下の接続情報は環境に合った内容に変更すること。  

https://github.com/namickey/spring5_jpa/blob/oracle/src/main/resources/META-INF/spring/applicationContext-beans.xml
```
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

https://github.com/namickey/spring5_jpa/blob/oracle/build.gradle
```
compile ("com.oracle:ojdbc6:12.1.0.1-atlassian-hosted")
```

## jpaProperties
* 組み込みTomcat起動時に定義されているEntityに合わせて、テーブルが作成される。  
* 組み込みTomcat停止時にはテーブルが削除される。  

https://github.com/namickey/spring5_jpa/blob/master/src/main/resources/META-INF/spring/applicationContext-beans.xml
```
<property name="jpaProperties">
    <props>
        <prop key="hibernate.hbm2ddl.auto">create-drop</prop>
        <prop key="hibernate.dialect">org.hibernate.dialect.DataDirectOracle9Dialect</prop>
    </props>
</property>
```
* 特にjpaPropertiesは、このままの設定で問題ないが、必要であれば切り替える  
* 「create-drop」が指定されている場合、事前にデータベースにテーブルを作成しておく必要無し。  
```
create - スキーマの作成
update - 既存のスキーマの更新
validate - 既存のスキーマを検証する
create-drop - セッションの開始と終了時にスキーマを自動的に作成して削除する
```

## No install Tomcat
組み込みTomcatを使うので、事前にTomcatをインストールする必要無し。  
基本的には以下の4つがあれば起動する。  
* java  
* gradle  
* cloneしたspring5_jpaプロジェクト  
* database(postgreSQL or Oracle)※databaseも組み込みDBにすれば、事前インストールの必要は無いが、今回は必要。  

## Start Command
Open the Command Prompt.  
```
「build.gradle」ファイルが存在するディレクトリへ移動する。
cd spring5_jpa
```
```
gradle tomcatRun
```
ブラウザで以下のURLにアクセスする。  
http://localhost:8080/demo  

## Stop Command
```
Ctrl + C
```

## Let's challenge ! !
* 自分のgitアカウントにプロジェクトを作成して、写経する。  
* エンティティ、リポジトリ、サービス、コントローラ、JSP等を作り変える。  
* 親子テーブルでCRUD（insert,select,update,delete）を実装する。  
* 悲観的排他、楽観的排他を実装する。以下サイト参考。  
  http://terasolunaorg.github.io/guideline/5.4.1.RELEASE/ja/ArchitectureInDetail/DataAccessDetail/ExclusionControl.html  
* デッドロックを起こしてみる。  
* 様々な方法で、採番を行う。  
  https://qiita.com/kawasima/items/6b0f47a60c9cb5ffb5c4  
* etc  
