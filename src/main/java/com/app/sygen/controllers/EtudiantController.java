package com.app.sygen.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.app.sygen.entities.Etudiant;
import com.app.sygen.services.EtudiantService;
@Controller
@RequestMapping("/etudiant")
public class EtudiantController 
{
	@Autowired
    private EtudiantService etudiantService;
   
    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Etudiant> listetudiant = etudiantService.shows();
        model.addAttribute("etudiants", listetudiant);
        
        return "index";
    }
    
    @GetMapping("/index")
    public List<Etudiant> show()
    {
        return this.etudiantService.show();

    }
    
    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("etudiant", new Etudiant());
        
        return "/etudiant/create";
    }

    @PostMapping("/create")
    public RedirectView processCreate(@ModelAttribute("etudiant") Etudiant etudiant, RedirectAttributes redir)
    {			
		RedirectView redirectView;
		
		try {
			this.etudiantService.create(etudiant);
			redirectView = new RedirectView("/etudiant/index", true);
			redir.addFlashAttribute("success", "Etudiant créé avec succès");
		}
		catch(Exception e) {
			redirectView = new RedirectView("/etudiant/create", true);
			redir.addFlashAttribute("error", e.getMessage());
		}
		
		return redirectView;
    }
}
