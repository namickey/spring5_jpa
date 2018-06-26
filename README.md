# spring5_jpa

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
            │   │       └── transaction.jsp
            │   └── web.xml
            └── index.jsp

```
## Install gradle
https://gradle.org/  

## Setting Lombok
https://projectlombok.org/

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
