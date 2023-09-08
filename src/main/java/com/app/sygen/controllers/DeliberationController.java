package com.app.sygen.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.app.sygen.entities.Etudiant;
import com.app.sygen.entities.Filiere;
import com.app.sygen.repositories.EtudiantRepository;
import com.app.sygen.repositories.FiliereRepository;
import com.app.sygen.services.serviceDeliberation.CritereDeliberation;
import com.app.sygen.services.serviceDeliberation.Datacontrolleur;
import com.app.sygen.services.serviceDeliberation.IndexDeliberation;
import com.app.sygen.services.serviceDeliberation.ListDeliberation;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeliberationController {
    @Autowired
    ListDeliberation listeDeliberation;

    @Autowired
    IndexDeliberation indexDeliberation;

    @Autowired
    EtudiantRepository etudiantRepository;

    @Autowired
    FiliereRepository filiereRepository;

    @GetMapping("/")
    public String deliberation() {

        return "deliberations/indexDeliberation";
    }


    @PostMapping("/criteresDeliberation")
    public String Criteres(@RequestBody Datacontrolleur jsonData, Model model) {
        System.out.println("*****Caught request");
        String codeFiliere = jsonData.getFiliere();
        System.out.println("codeFiliere" + codeFiliere);
        Filiere filiere = filiereRepository.findFirstByCode(codeFiliere);
        List<CritereDeliberation> blocCriteres = jsonData.getTableData();
        indexDeliberation.deliberation(blocCriteres, filiere.getId());
        System.out.println(filiere.getId());
        return "redirect:rapportDeliberation?idFiliere=" + filiere.getId();
    }

    @GetMapping("/rapportDeliberation")
    public String rapportDeliberation(HttpServletRequest request, Model model) {

        // ListDeliberation listDeliberation = new ListDeliberation();
        HashMap<String, List> modelHashMap = listeDeliberation.listDeliberations(
                Long.parseUnsignedLong((String) request.getParameter("idFiliere")), Optional.empty());
        model.addAttribute("listeDesDeliberations", modelHashMap.get("listeDesDeliberations"));
        model.addAttribute("etudiantsDeliberes", modelHashMap.get("etudiantsDeliberes"));
        System.out.println("Rapport Deliberation end");
        return "/deliberations/indexDeliberation";
    }

}
