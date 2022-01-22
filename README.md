# HTTP tracing using Spring Cloud Sleuth

This example shows the implementation and usage of HTTP tracing using Spring Cloud Sleuth and [OpenZipkin](https://zipkin.io/).

### Logging

A logfile entry is extended by Spring Cloud Sleuth with a Mapped Diagnostig Context (MDC), e.g. [Logback Mapped Diagnostic Context](http://logback.qos.ch/manual/mdc.html). The MDC contains a *Trace ID* and a *Span ID*. The following example shows the *Trace ID* <code>ad2ab1a1a2e189c4</code> and the *Span ID* <code>721ba8ca7de4000f</code>.

```text
2021-04-05 18:07:37,044Z  INFO [Sleuth Example,ad2ab1a1a2e189c4,721ba8ca7de4000f] [http-nio-auto-1-exec-1] c.e.s.h.s.h.HelloController             : GET a hello.
```

The following Logback layout pattern specifies the MDC data <code>%X{...}</code> and was exemplarily taken from the Logback configuration file <code>logback-spring.xml</code>.

```xml
    <appender name="Console" class="ch.qos.logback.core.ConsoleAppender">
        <layout class="ch.qos.logback.classic.PatternLayout">
            <Pattern>
                %date{ISO8601,UTC}Z %highlight(%5level) [${springAppName},%X{traceId},%X{spanId}] [%15thread] %cyan(%-40logger{25}): %msg%n%throwable
            </Pattern>
        </layout>
    </appender>
```

The following request log entry shows the HTTP trace headers, which are added automatically by Spring Cloud Sleuth.

```text
2021-04-05 18:07:37,035Z DEBUG [Sleuth Example,ad2ab1a1a2e189c4,721ba8ca7de4000f] [http-nio-auto-1-exec-1] o.s.w.f.CommonsRequestLoggingFilter     : Before request [GET /hello, headers=[accept:"text/plain, application/json, application/*+json, */*", x-b3-traceid:"ad2ab1a1a2e189c4", x-b3-spanid:"ad2ab1a1a2e189c4", x-b3-sampled:"1", user-agent:"Java/11.0.10", host:"localhost:34581", connection:"keep-alive"]]
```

### Zipkin

A Zipkin server including a web UI can be operated using a Docker container. The following Docker Compose configuration shows the usage of [docker-zipkin](https://github.com/openzipkin-attic/docker-zipkin), which use an in-memory for data storage.

```yaml
version: '2'

services:
  # The zipkin process services the UI, and also exposes a POST endpoint that
  # instrumentation can send trace data to. Scribe is enabled by default.
  zipkin:
    image: openzipkin/zipkin
    container_name: zipkin
    environment:
      - STORAGE_TYPE=mem
      # Uncomment to disable scribe
      # - SCRIBE_ENABLED=false
      # Uncomment to enable self-tracing
      # - SELF_TRACING_ENABLED=true
      # Uncomment to enable debug logging
      # - JAVA_OPTS=-Dlogging.level.zipkin=DEBUG
    ports:
      # Port used for the Zipkin UI and HTTP Api
      - 9411:9411
```

The Zipkin web UI is accessible with [http://localhost:9411/zipkin/](http://localhost:9411/zipkin/). The Zipkin server with [http://localhost:9411/](http://localhost:9411/).

### See also

* [Spring Cloud Sleuth](https://spring.io/projects/spring-cloud-sleuth)
* [Spring Cloug Sleuth: “How-to” Guides](https://docs.spring.io/spring-cloud-sleuth/docs/current/reference/html/howto.html)
* [Spring Cloug Sleuth: Sending Spans to Zipkin](https://docs.spring.io/spring-cloud-sleuth/docs/current/reference/html/project-features.html#features-zipkin)
* [Spring Cloug Sleuth: Basic example showing distributed tracing across Spring Boot apps](https://github.com/openzipkin-attic/sleuth-webmvc-example)
* [Spring Blog: Distributed Tracing with Spring Cloud Sleuth and Spring Cloud Zipkin](https://spring.io/blog/2016/02/15/distributed-tracing-with-spring-cloud-sleuth-and-spring-cloud-zipkin)
* [Baeldung: Spring Cloud Sleuth in a Monolith Application](https://www.baeldung.com/spring-cloud-sleuth-single-application)
* [DZone: Microservices - Part 6: Distributed Tracing With Spring Cloud Sleuth and Zipkin](https://dzone.com/articles/microservices-part-6-distributed-tracing-with-spri)
* [Medium: Microservices Observability With Zipkin and Spring Cloud-Sleuth](https://medium.com/swlh/microservices-observability-with-zipkin-and-spring-cloud-sleuth-66508ce6840)
* [Logback: Mapped Diagnostic Context (MDC)](http://logback.qos.ch/manual/mdc.html)
* [OpenZipkin](https://zipkin.io/)
* [Github: docker-zipkin](https://github.com/openzipkin-attic/docker-zipkin)
