package com.app.sygen.controllers;

import java.util.ArrayList;
import java.util.List;
// import java.security.Principal;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.app.sygen.entities.Enseignant;
import com.app.sygen.entities.Etudiant;
import com.app.sygen.entities.Evaluation;
import com.app.sygen.entities.Participe;
import com.app.sygen.entities.Ue;
import com.app.sygen.entities.Users;
import com.app.sygen.repositories.EnseignantRepository;
import com.app.sygen.repositories.EvaluationRepository;
import com.app.sygen.repositories.ParticipeRepository;
import com.app.sygen.repositories.UserRepository;
// import com.app.sygen.repositories.UserRepository;
import com.app.sygen.services.ParticipeService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/participe")
public class ParticipeController {
    @Autowired
    private ParticipeRepository participeRepository;

    @Autowired
    private ParticipeService participeService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnseignantRepository enseignantRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;

    // @GetMapping("/admin")
    public Users showadmin(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        return user;
    }

    @PostMapping("/import")
    public String importFile(@RequestParam("code") String code, @RequestParam("typeEval") String typeEval,
            @RequestParam("noteSur") int noteSur, @RequestParam("file") MultipartFile file, Model model) {
        try {
            List<Participe> importedPersons = new ArrayList<>();
            Users user = userRepository.findByLogin("bosley-12");
            if (file.getOriginalFilename().endsWith(".xlsx")) {
                Workbook workbook = WorkbookFactory.create(file.getInputStream());
                Sheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheet.iterator();

                Row headerRow = rowIterator.next(); // Lecture de la première ligne (titres des colonnes)
                Map<String, Integer> columnIndexMap = participeService.getColumnIndexMap(headerRow);

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Participe participe = participeService.createParticipeXLSX(row, columnIndexMap, code, typeEval,
                            noteSur);

                    if (participe != null) {
                        participe.setUser(user);
                        importedPersons.add(participe);
                    } else {
                        return "redirect:/index-import?status=BadStructure";
                    }
                }

                workbook.close();
            } else {
                return "redirect:/index-import?status=bad";
            }

            // Enregistrer les personnes importées dans la table Person
            participeRepository.saveAll(importedPersons);
            
            

            return "redirect:/index-import?status=success";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("file", file);
            return "redirect:/index-import?status=fail";
        }
    }

    @GetMapping("/index-import")
    public String ListUe(Model model) {
        Users user = userRepository.findByLogin("bosley-12");
        List<Ue> Ues = enseignantRepository.findByUser(user).getUes();

        List<Evaluation> evaluations = evaluationRepository.findByUe(Ues);
        // List<Participe> participe = participeRepository.findFirstByEvaluationIn(evaluations);
        List<Integer> liste = new ArrayList<>();
        for (Evaluation obj : evaluations) {
            Optional<Participe> participeOptional = participeRepository.findFirstByEvaluation(obj);
            int valeur = participeOptional.isPresent() ? 1 : 0;
            liste.add(valeur);
        }

        List<Participe> listeParticipe = (List<Participe>) model.getAttribute("participes");

        Iterable<Evaluation> evaluation=evaluations;
        model.addAttribute("participes", listeParticipe);
        model.addAttribute("evaluation", evaluation);
        model.addAttribute("list", liste);
        return "import en masse/index";



    }
   
    @PostMapping("/delete")
    public String delete (@RequestParam("code") String code, @RequestParam("typeEval") String typeEval){
        // List<Participe> participes = participeRepository.findByEvaluation_TypeEvalAndEvaluation_Ue_Code(typeEval,code);
        Evaluation evaluations = evaluationRepository.findByTypeEvalAndUe_Code(typeEval, code);
        List<Participe> participes = participeRepository.findByEvaluation(evaluations);
        participeRepository.deleteAll(participes);
        return "redirect:/index-import";
    }

    @GetMapping("/shows-data")
    public String data(@RequestParam("code") String code, @RequestParam("typeEval") String typeEval ,Model model){
        Evaluation evaluations = evaluationRepository.findByTypeEvalAndUe_Code(typeEval, code);
        List<Participe> participes = participeRepository.findByEvaluation(evaluations);
        model.addAttribute("participes", participes);
         return ListUe(model);
    }



}
