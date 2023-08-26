package com.app.sygen.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.sygen.entities.Ue;
import com.app.sygen.repositories.UeRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
@Controller
public class UeController {
    @Autowired
    private UeRepository repo;

    @GetMapping("/ueList")
    public String listue(Model model){
        List<Ue> listue=repo.findAll();
        model.addAttribute("listue",listue);
        return "/examen/ueList";
    }

    @GetMapping("/ueList/new")
    public String shownewForm(Model model){
        model.addAttribute("category",new Ue());

        return "/examen/ue_form";
    }
    @PostMapping("/ueList/save")
        public String saveue(Ue category){
            repo.save(category);

            return "redirect:/ueList";
        }

    @ModelAttribute("credits")
    public List<Integer> getCredits(){
        List<Integer> credits= new ArrayList<>();
        credits.add(3);
        credits.add(6);
        return credits;
    }
    
}
