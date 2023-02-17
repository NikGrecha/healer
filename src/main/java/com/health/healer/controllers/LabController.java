package com.health.healer.controllers;

import com.health.healer.connections.HttpSessionBean;
import com.health.healer.service.GoLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/labMain")
public class LabController {
    @Autowired
    private HttpSessionBean httpSessionBean;

    @Autowired
    private GoLService goLService;


    @GetMapping
    public String takeGoLByMobile(@RequestParam (required = false) String mobile, Model model){
        model.addAttribute("goLList", goLService.takeGoLByMobile(httpSessionBean.getConnection(), mobile));

        return "labMain";
    }

    @PostMapping
    public String uploadResult(@RequestParam MultipartFile analysisResult, @RequestParam int goLId){
        goLService.saveDocument(analysisResult, goLId, httpSessionBean.getId(), httpSessionBean.getConnection());
        return "redirect:/labMain";
    }
}
