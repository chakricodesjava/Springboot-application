# Spring Boot Interview Questions & Answers


## 1. Overview

Spring Boot has become a foundational project in the Spring ecosystem, offering developers a faster path to building robust applications by leveraging auto-configuration and other productivity features. Below, you'll find key interview questions and answers covering essential topics for anyone preparing for a Spring Boot technical interview[1].

## 2. Interview Questions

### Q1. What Is Spring Boot and What Are Its Main Features?

Spring Boot is a framework for rapid application development built on top of the Spring Framework. It simplifies configuration, offers an embedded application server, and provides comprehensive documentation and community support.  
**Key features include:**
- **Starters**: Bundled dependency descriptors for quick setup.
- **Auto-configuration**: Automatic application setup based on dependencies.
- **Actuator**: Monitoring and production-ready features.
- **Security**: Built-in mechanisms for authentication/authorization.
- **Logging**: Centralized and consistent event logging[1].

### Q2. What Are the Differences Between Spring and Spring Boot?

Spring is a versatile, unopinionated framework with features such as dependency injection, data binding, and aspect-oriented programming. Spring Boot, by contrast, is _opinionated_—it makes configuration simpler with:
- Auto-configuration according to classpath content.
- Built-in production features like security and health checks[1].

### Q3. How Can We Set Up a Spring Boot Application With Maven?

You typically inherit from `spring-boot-starter-parent` in your `pom.xml` and declare dependencies as needed:
```xml

    org.springframework.boot
    spring-boot-starter-parent
    3.2.3

```
For custom parent POMs (required by some organizations), you can still leverage Spring Boot’s dependency management[1].

### Q4. What Is Spring Initializr?

Spring Initializr is a web-based tool (https://start.spring.io/) to bootstrap a Spring Boot project.
- Choose dependencies, language, packaging, and version.
- Download a ready-to-use skeleton project, saving setup time and effort.
- IDE project wizards often use Spring Initializr under the hood[1].

### Q5. What Spring Boot Starters Are Available?

Starters are curated dependency collections beginning with `spring-boot-starter-`.  
Common examples:
- `spring-boot-starter`
- `spring-boot-starter-aop`
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-security`
- `spring-boot-starter-test`
- `spring-boot-starter-web`  
  There are over 50 available; see the Spring Boot GitHub for a full list[1].

### Q6. How to Disable a Specific Auto-Configuration?

Use the `exclude` attribute of `@EnableAutoConfiguration` or `@SpringBootApplication`:
```java
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MyConfiguration { }
```
Or, add to `application.properties` OR `application.yml` :
```
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
```


### Q7. How to Register a Custom Auto-Configuration?

List the class under the `EnableAutoConfiguration` key in `META-INF/spring.factories`:
```
org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.example.CustomAutoConfiguration
```
Place this file in `resources/META-INF` in your project[1].

### Q8. How to Tell an Auto-Configuration to Back Away When a Bean Exists?

Use `@ConditionalOnMissingBean`:
```java
@Configuration
public class CustomConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public CustomService service() { ... }
}
```
This ensures the bean is only registered if one isn’t present[1].

### Q9. How to Deploy Spring Boot Web Applications as Jar and War Files?

- **Executable Jar (default):** Add `spring-boot-maven-plugin` to `pom.xml` and package the application as a JAR with embedded server.
- **War:** Change `packaging` to war; set `spring-boot-starter-tomcat` scope to `provided`.
```xml
war

    org.springframework.boot
    spring-boot-starter-tomcat
    provided

```


### Q10. How to Use Spring Boot for Command-Line Applications?

Implement a `main` method and call `SpringApplication.run`:
```java
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class);
        // custom logic here
    }
}
```
The Spring container is bootstrapped just like in a standard Java app[1].

### Q11. What Are Possible Sources of External Configuration?

- **Properties files** (`application.properties`)
- **YAML files** (`application.yml`)
- **Environment variables**
- **System properties**
- **Command-line arguments**  
  Access via `@Value`, `@ConfigurationProperties`, or the `Environment` abstraction[1].

### Q12. What Does It Mean That Spring Boot Supports Relaxed Binding?

You can map properties using camelCase, kebab-case, snake_case, or upper-case-with-underscores—Spring Boot will bind them all to the correct field.  
Example: `myProp` matches `myProp`, `my-prop`, `my_prop`, or `MY_PROP`[1].

### Q13. What Is Spring Boot DevTools Used For?

DevTools enhances the development experience by:
- Enabling automatic application restarts on classpath changes.
- Disabling template caching for rapid feedback.
- Excluding itself in production builds to avoid overhead.
  Add it to `pom.xml`:
```xml

    org.springframework.boot
    spring-boot-devtools

```


### Q14. How to Write Integration Tests?

Use `@SpringBootTest` to create an `ApplicationContext` for tests:
```java
@SpringBootTest
public class MyIntegrationTest { ... }
```
The annotation automatically scans for a configuration class, or you can specify one[1].

### Q15. What Is Spring Boot Actuator Used For?

Actuator provides production-ready features:
- **Endpoints** for app health, environment, metrics, logging, mapping, and more.
  Add to `pom.xml`:
```xml

    org.springframework.boot
    spring-boot-starter-actuator

```
Access endpoints (e.g., `/actuator/health`, `/actuator/metrics`) over HTTP or JMX[1].

### Q16. Which Is Better to Configure a Spring Boot Project—Properties or YAML?

**YAML**:
- More readable, easier for hierarchical data.
- Supports maps, lists, scalar types.
- Single file can contain multiple profiles.

**Properties**:
- Simpler syntax.
- Less error-prone (YAML is sensitive to indentation).[1]

### Q17. What Basic Annotations Does Spring Boot Offer?

- `@EnableAutoConfiguration`: Activates auto-configuration based on classpath.
- `@SpringBootApplication`: Main entry annotation, combines `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`[1].

### Q18. How to Change the Default Port in Spring Boot?

- In `application.properties`:  
  `server.port=8081`
- Programmatically via `SpringApplication` instance.
- Via command line:  
  `java -jar -Dserver.port=8081 myapp.jar`
  [1]

### Q19. Which Embedded Servers Does Spring Boot Support, and How to Change the Default?

- **Spring MVC** supports Tomcat (default), Jetty, Undertow.
- **Spring WebFlux** supports Reactor Netty (default), plus Tomcat, Jetty, Undertow.
  To switch servers, exclude the default and add your chosen alternative dependency in `pom.xml`[1].

### Q20. Why Do We Need Spring Profiles?

Profiles support multiple environments (Dev, QA, Prod), each with separate configuration files (`application-dev.properties`, `application-prod.properties`). The `spring.profiles.active` property in `application.properties` chooses the current profile[1].



the above please refactor the code and give me Explancation