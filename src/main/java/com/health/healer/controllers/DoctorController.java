package com.health.healer.controllers;

import com.health.healer.connections.HttpSessionBean;
import com.health.healer.models.Card;
import com.health.healer.service.CardService;
import com.health.healer.service.GoLService;
import com.health.healer.service.GoPService;
import com.health.healer.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/doctorMain")
public class DoctorController {
    @Autowired
    private HttpSessionBean httpSessionBean;

    @Autowired
    private GoLService goLService;

    @Autowired
    private GoPService goPService;

    @Autowired
    private VisitService visitService;

    @Autowired
    private CardService cardService;

    @GetMapping
    public String loginDoctor(@RequestParam(name="mobile", required=false) String mobile, Model model) {
        Card card = cardService.findByMobile(httpSessionBean.getConnection(), mobile);
        if(mobile != null){
            model.addAttribute("card", card);
            model.addAttribute("visitList", visitService.findByCardId(card.getId(), httpSessionBean.getConnection()));
            //model.addAttribute("recipeList", visitService.findByCardId(card.getId(), httpSessionBean.getConnection()));
        }

        return "doctorMain";
    }
}
