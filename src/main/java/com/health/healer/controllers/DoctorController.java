package com.health.healer.controllers;

import com.health.healer.connections.HttpSessionBean;
import com.health.healer.models.Card;
import com.health.healer.models.Recipe;
import com.health.healer.service.*;
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

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public String loginDoctor(@RequestParam(name="mobile", required=false) String mobile, Model model) {
        Card card = cardService.findByMobile(httpSessionBean.getConnection(), mobile);
        if(mobile != null){
            model.addAttribute("card", card);
            model.addAttribute("visitList", visitService.findVisitByCardId(card.getId(), httpSessionBean.getConnection()));
            model.addAttribute("recipeList", recipeService.findRecipeByCardId(card.getId(), httpSessionBean.getConnection()));
            model.addAttribute("goPList", goPService.findGoPByCardId(card.getId(), httpSessionBean.getConnection()));
            model.addAttribute("goLList", goLService.findGoLByCardId(card.getId(), httpSessionBean.getConnection()));
        }

        return "doctorMain";
    }
}
