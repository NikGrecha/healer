package com.health.healer.controllers;

import com.health.healer.connections.HttpSessionBean;
import com.health.healer.models.Card;
import com.health.healer.models.Worker;
import com.health.healer.service.CardService;
import com.health.healer.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/adminMain")
public class AdminController {
    @Autowired
    private WorkerService workerService;
    @Autowired
    private HttpSessionBean httpSessionBean;
    @Autowired
    private CardService cardService;

    @PostMapping("/changeUserPassword")
    public String changeUserPassword(@RequestParam String mobile, @RequestParam String newPassword){
        workerService.changeUserPassword(httpSessionBean.getConnection(), newPassword, mobile);
        return "redirect:/adminMain";
    }
    @PostMapping("/addPatient")
    public String addPatient(Card card, @RequestParam(name = "password") String password){
        cardService.save(card, password, httpSessionBean.getConnection());
        return "redirect:/adminMain";
    }
    @PostMapping("/addWorker")
    public String addWorker(Worker worker, @RequestParam(name = "password") String password){
        workerService.save(worker, password, httpSessionBean.getConnection());
        return "redirect:/adminMain";
    }
}
