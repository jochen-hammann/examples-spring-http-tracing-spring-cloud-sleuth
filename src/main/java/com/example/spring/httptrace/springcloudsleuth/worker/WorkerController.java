package com.example.spring.httptrace.springcloudsleuth.worker;

import com.example.spring.httptrace.springcloudsleuth.hello.Hello;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(path = "/worker")
public class WorkerController
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    private WorkerService workerService;

    // ============================== [Construction / Destruction] ==============================

    // -------------------- [Public Construction / Destruction] --------------------

    @Autowired
    public WorkerController(WorkerService workerService)
    {
        this.workerService = workerService;
    }

    // ============================== [Getter/Setter] ==============================

    // -------------------- [Private Getter/Setter] --------------------

    // -------------------- [Public Getter/Setter] --------------------

    // ============================== [Methods] ==============================

    // -------------------- [Private Methods] --------------------

    // -------------------- [Public Methods] --------------------

    @GetMapping(path = "/hello",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public Hello get()
    {
        log.info("Get a hello from the worker controller.");

        return this.workerService.getHello();
    }
}
