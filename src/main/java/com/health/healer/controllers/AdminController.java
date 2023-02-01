package com.health.healer.controllers;
import com.health.healer.connections.HttpSessionBean;
import com.health.healer.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/adminMain")
public class AdminController {
    @Autowired
    private WorkerService workerService;
    @Autowired
    private HttpSessionBean httpSessionBean;
    @PostMapping("/changeUserPassword")
    public String changeUserPassword(@RequestParam String mobile, @RequestParam String newPassword){
        workerService.changeUserPassword(httpSessionBean.getConnection(), newPassword, mobile);
        return "redirect:/adminMain";
    }
}
