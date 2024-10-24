package com.health.healer.controllers;

import com.health.healer.connections.HttpSessionBean;
import com.health.healer.service.GoLService;
import com.health.healer.service.GoPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/physioMain")
public class PhysioController {
    @Autowired
    private HttpSessionBean httpSessionBean;
    @Autowired
    private GoPService goPService;

    @GetMapping
    public String physioMain() {
        return "physioMain";
    }

    @PostMapping("/physioSearch")
    public String loginPhysio(@RequestParam(required = false) String mobile, Model model) {
        model.addAttribute("goPList", goPService.takeGoPByMobile(httpSessionBean.getConnection(), mobile));
        return "physioSearch";
    }

    @GetMapping("/physioCheck")
    public String showReduceVisits (){
        return "physioCheck";
    }
    @PostMapping("/physioCheck")
    public String reduceVisits (@RequestParam int id){
        goPService.reduceVisits(httpSessionBean.getConnection(), id);
        return "redirect:/physioMain";
    }
}
