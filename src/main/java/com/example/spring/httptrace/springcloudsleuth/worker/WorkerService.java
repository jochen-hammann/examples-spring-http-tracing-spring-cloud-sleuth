package com.example.spring.httptrace.springcloudsleuth.worker;

import com.example.spring.httptrace.springcloudsleuth.hello.Hello;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WorkerService
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    private WorkerRepository workerRepository;

    // ============================== [Construction / Destruction] ==============================

    // -------------------- [Public Construction / Destruction] --------------------

    @Autowired
    public WorkerService(WorkerRepository workerRepository)
    {
        this.workerRepository = workerRepository;
    }

    // ============================== [Getter/Setter] ==============================

    // -------------------- [Private Getter/Setter] --------------------

    // -------------------- [Public Getter/Setter] --------------------

    // ============================== [Methods] ==============================

    // -------------------- [Private Methods] --------------------

    // -------------------- [Public Methods] --------------------

    public Hello getHello()
    {
        log.info("Get a hello from the worker service.");

        return this.workerRepository.getHello();
    }
}
