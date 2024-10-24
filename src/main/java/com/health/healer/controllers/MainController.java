package com.health.healer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/guestMain")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "guestMain";
    }

//    @GetMapping("/doctorMain")
//    public String loginDoctor(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "doctorMain";
//    }

//    @GetMapping("/labMain")
//    public String loginLaboratory(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "labMain";
//    }

//    @GetMapping("/physioMain")
//    public String loginPhysio(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "physioMain";
//    }

//    @GetMapping("/adminMain")
//    public String loginAdmin(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "adminMain";
//    }

//    @GetMapping("/userMain")
//    public String loginUser(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "userMain";
//    }
}
