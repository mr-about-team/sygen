package com.app.sygen.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.sygen.entities.Etudiant;
import com.app.sygen.entities.Paiement;
import com.app.sygen.entities.Participe;
import com.app.sygen.repositories.EtudiantRepository;
import com.app.sygen.repositories.EvaluationRepository;
import com.app.sygen.repositories.FiliereRepository;
import com.app.sygen.repositories.ParticipeRepository;
import com.app.sygen.repositories.UeRepository;
import com.app.sygen.services.ProductionPvService;

@Controller
@RestController
@RequestMapping(path = "doc")
public class ProductionPvController {
    @Autowired
    private ProductionPvService productionPvService;
    @Autowired
    private UeRepository ueRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private FiliereRepository filiereRepository;
    @Autowired
    private ParticipeRepository participeRepository;
    @Autowired
    private EvaluationRepository evaluationRepository;
    
    @GetMapping(path = "pv")
    public ResponseEntity<ByteArrayResource> getPv() throws Exception{
        // return productionPvService.makePdfCC("cc",ueRepository.findByCode("inf-242") , "inf-l3");
        return productionPvService.makePdfUe("sn", ueRepository.findByCode("inf-242"), "inf-l3", "inf-l3");
    }

    
}
