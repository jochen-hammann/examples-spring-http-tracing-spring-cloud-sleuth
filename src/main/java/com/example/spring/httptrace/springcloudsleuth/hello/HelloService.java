package com.example.spring.httptrace.springcloudsleuth.hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Configuration
@Slf4j
public class HelloService
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    private RestTemplate restTemplate;
    private ServletWebServerApplicationContext webServerAppCtxt;

    // ============================== [Construction / Destruction] ==============================

    // -------------------- [Public Construction / Destruction] --------------------

    @Autowired
    public HelloService(RestTemplate restTemplate, ServletWebServerApplicationContext webServerAppCtxt)
    {
        this.restTemplate = restTemplate;
        this.webServerAppCtxt = webServerAppCtxt;
    }

    // ============================== [Getter/Setter] ==============================

    // -------------------- [Private Getter/Setter] --------------------

    // -------------------- [Public Getter/Setter] --------------------

    // ============================== [Methods] ==============================

    // -------------------- [Private Methods] --------------------

    // -------------------- [Public Methods] --------------------

    public Hello getHello()
    {
        log.info("Get a hello from the service.");

        Hello hello = this.restTemplate.getForObject("http://localhost:{port}/worker/hello", Hello.class, this.webServerAppCtxt.getWebServer().getPort());

        return hello;
    }
}
