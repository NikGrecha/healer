package com.health.healer.controllers;
import com.health.healer.models.Worker;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddWorkerController {
    @PostMapping
    public String addWorker (Worker worker, @RequestParam String password) {

    }
}
