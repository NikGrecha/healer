package com.health.healer.controllers;

import com.health.healer.connections.HttpSessionBean;
import com.health.healer.models.Card;
import com.health.healer.service.GoLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/labMain")
public class LabController {
    @Autowired
    private HttpSessionBean httpSessionBean;

    @Autowired
    private GoLService goLService;

//    @GetMapping("/labTake")
//    public String goLabTake(){
//        return "labTake";
//    }

    @GetMapping("/labUpload")
    public String goLabUpload(){
        return "labUpload";
    }

    @GetMapping
    public String GoLByMobile(){
        return "labMain";
    }

    @PostMapping("/labTake")
    public String takeGoLByMobile(@RequestParam (required = false) String mobile, Model model){
        model.addAttribute("goLList", goLService.takeGoLByMobile(httpSessionBean.getConnection(), mobile));
        return "labTake";
    }

    @PostMapping
    public String uploadResult(@RequestParam MultipartFile analysisResult, @RequestParam int goLId){
        goLService.saveDocument(analysisResult, goLId, httpSessionBean.getId(), httpSessionBean.getConnection());
        return "redirect:/labMain";
    }
}
