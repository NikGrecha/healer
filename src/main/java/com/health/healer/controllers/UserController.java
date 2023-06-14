package com.health.healer.controllers;

import com.health.healer.connections.HttpSessionBean;
import com.health.healer.models.Card;
import com.health.healer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

@Controller
@RequestMapping(value = "/userMain")
public class UserController {
    @Value("${filepath}")
    private String filepath;
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

    @GetMapping("/{mobile}")
    public String loginUser(@PathVariable String mobile, Model model) {
        Card card = cardService.findByMobile(httpSessionBean.getConnection(), mobile);

        model.addAttribute("card", card);
//        model.addAttribute("visitList", visitService.findVisitByCardId(card.getId(), httpSessionBean.getConnection()));
//        model.addAttribute("goPList", goPService.findGoPByCardId(card.getId(), httpSessionBean.getConnection()));
//        model.addAttribute("goLList", goLService.findGoLByCardId(card.getId(), httpSessionBean.getConnection()));

        return "userMain";
    }

    @GetMapping("/userVisit/{mobile}")
    public String goUserVisit(@PathVariable String mobile, Model model){
        Card card = cardService.findByMobile(httpSessionBean.getConnection(), mobile);

//        model.addAttribute("card", card);
        model.addAttribute("mobile", mobile);
        model.addAttribute("visitList", visitService.findVisitByCardId(card.getId(), httpSessionBean.getConnection()));

        return "userVisit";
    }

    @GetMapping("/userRecipe/{mobile}")
    public String goUserRecipe(@PathVariable String mobile, Model model){
        Card card = cardService.findByMobile(httpSessionBean.getConnection(), mobile);

//        model.addAttribute("card", card);
        model.addAttribute("mobile", mobile);
        model.addAttribute("recipeList", recipeService.findRecipeByCardId(card.getId(), httpSessionBean.getConnection()));

        return "userRecipe";
    }

    @GetMapping("/userLab/{mobile}")
    public String goUserLab(@PathVariable String mobile, Model model){
        Card card = cardService.findByMobile(httpSessionBean.getConnection(), mobile);

//        model.addAttribute("card", card);
        model.addAttribute("mobile", mobile);
        model.addAttribute("goLList", goLService.findGoLByCardId(card.getId(), httpSessionBean.getConnection()));

        return "userLab";
    }

    @GetMapping("/userPhys/{mobile}")
    public String goUserPhys(@PathVariable String mobile, Model model){
        Card card = cardService.findByMobile(httpSessionBean.getConnection(), mobile);

//        model.addAttribute("card", card);
        model.addAttribute("mobile", mobile);
        model.addAttribute("goPList", goPService.findGoPByCardId(card.getId(), httpSessionBean.getConnection()));

        return "userPhys";
    }

    @GetMapping("/getResult/{filename}")
    @ResponseBody
    public ResponseEntity<InputStreamResource> getImageDynamicType(@PathVariable String filename) throws FileNotFoundException {
        MediaType contentType = MediaType.IMAGE_JPEG;
        File initialFile = new File(filepath + filename);
        InputStream targetStream = new FileInputStream(initialFile);
        //InputStream in = getClass().getResourceAsStream(filepath + filename);
        return ResponseEntity.ok()
                .contentType(contentType)
                .body(new InputStreamResource(Objects.requireNonNull(targetStream)));
    }
}
