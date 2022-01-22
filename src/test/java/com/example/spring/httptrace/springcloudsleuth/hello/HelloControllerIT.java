package com.example.spring.httptrace.springcloudsleuth.hello;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)  // TestRestTemplate requires a webEnvironment !!!
class HelloControllerIT
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    @Autowired
    private TestRestTemplate restTemplate;

    // ============================== [Unit Tests] ==============================

    // -------------------- [Test Helper Classes] --------------------

    // -------------------- [Test Helper Methods] --------------------

    private ResponseEntity<String> makeRestCall(String username, String password)
    {
        return restTemplate.withBasicAuth(username, password).getForEntity("/hello", String.class);
    }

    private ResponseEntity<String> makeRestCall()
    {
        return restTemplate.getForEntity("/hello", String.class);
    }

    // -------------------- [Test Initialization] --------------------

    // -------------------- [Tests] --------------------

    @Test
    void getTest() throws Exception
    {
        ResponseEntity<String> response = restTemplate.getForEntity("/hello", String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        System.out.println(response.getBody());
    }
}
