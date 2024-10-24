package com.health.healer.controllers;

import com.health.healer.connections.HttpSessionBean;
import com.health.healer.models.*;
import com.health.healer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;

@Controller
@RequestMapping(value = "/docMain")
public class DoctorController {
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
    @PostMapping
    public String loginDoctor(@RequestParam(name="mobile", required = false) String mobile, Model model) {
        Card card = cardService.findByMobile(httpSessionBean.getConnection(), mobile);
        System.out.println(mobile);
        if(mobile != null){
            model.addAttribute("card", card);
            model.addAttribute("visitList", visitService.findVisitByCardId(card.getId(), httpSessionBean.getConnection()));
            model.addAttribute("recipeList", recipeService.findRecipeByCardId(card.getId(), httpSessionBean.getConnection()));
            model.addAttribute("goPList", goPService.findGoPByCardId(card.getId(), httpSessionBean.getConnection()));
            model.addAttribute("goLList", goLService.findGoLByCardId(card.getId(), httpSessionBean.getConnection()));
        }

        return "docMain";
    }

    @GetMapping
    public String docMobile1() {
        return "docMain";
    }

    @GetMapping("/docVisit/{mobile}")
    public String goDocVisit(@PathVariable String mobile, Model model) {
        Card card = cardService.findByMobile(httpSessionBean.getConnection(), mobile);

        model.addAttribute("card", card);

        return "docVisit";
    }

    @GetMapping("/docRecipe/{mobile}")
    public String goGocRecipe(@PathVariable String mobile, Model model) {
        Card card = cardService.findByMobile(httpSessionBean.getConnection(), mobile);
        model.addAttribute("card", card);

        return "docRecipe";
    }

    @GetMapping("/docGoL/{mobile}")
    public String goDocGoL(@PathVariable String mobile, Model model) {
        Card card = cardService.findByMobile(httpSessionBean.getConnection(), mobile);

        model.addAttribute("card", card);

        return "docGoL";
    }

    @GetMapping("/docGoP/{mobile}")
    public String goDocGoP(@PathVariable String mobile, Model model) {
        Card card = cardService.findByMobile(httpSessionBean.getConnection(), mobile);

        model.addAttribute("card", card);

        return "docGoP";
    }
    @PostMapping("/addVisit/{cardId}")
    public String addVisit(Visit visit, @PathVariable int cardId, RedirectAttributes attributes){
        LocalDateTime time = LocalDateTime.now();

        visit.setCardId(cardId);
        visit.setDoctorId(httpSessionBean.getId());
        visit.setDate(time.minusNanos(time.getNano()));
        visitService.save(httpSessionBean.getConnection(), visit);
        attributes.addAttribute("mobile", cardService.findById(httpSessionBean.getConnection(), cardId).getMobile());
        return "redirect:/docMain";
    }

    @PostMapping("/addRecipe/{cardId}")
    public String addRecipe(Recipe recipe, @PathVariable int cardId, RedirectAttributes attributes){
        recipe.setVisitId(visitService.findVisitByCardId(cardId, httpSessionBean.getConnection()).stream().max(Comparator.comparing(VisitView::getDate)).get().getId());
        recipeService.save(httpSessionBean.getConnection(), recipe);
        attributes.addAttribute("mobile", cardService.findById(httpSessionBean.getConnection(), cardId).getMobile());
        return "redirect:/docMain";
    }

    @PostMapping("/addGoL/{cardId}")
    public String addGoL(GoL goL, @PathVariable int cardId, RedirectAttributes attributes){
        goL.setVisitId(visitService.findVisitByCardId(cardId, httpSessionBean.getConnection()).stream().max(Comparator.comparing(VisitView::getDate)).get().getId());
        goLService.save(httpSessionBean.getConnection(), goL);
        attributes.addAttribute("mobile", cardService.findById(httpSessionBean.getConnection(), cardId).getMobile());
        return "redirect:/docMain";
    }

    @PostMapping("/addGoP/{cardId}")
    public String addGoP(GoP goP, @PathVariable int cardId, RedirectAttributes attributes){
        goP.setVisitId(visitService.findVisitByCardId(cardId, httpSessionBean.getConnection()).stream().max(Comparator.comparing(VisitView::getDate)).get().getId());
        goPService.save(httpSessionBean.getConnection(), goP);
        attributes.addAttribute("mobile", cardService.findById(httpSessionBean.getConnection(), cardId).getMobile());
        return "redirect:/docMain";
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
