package com.app.sygen.controllers;

import java.util.ArrayList;
import java.util.List;
// import java.security.Principal;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/importation")
    public String importFile(@RequestParam("code") String code,@RequestParam("typeEval") String typeEval,@RequestParam("noteSur") int noteSur,@RequestParam("file") MultipartFile file, Model model) {
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
                    Participe participe = participeService.createParticipeXLSX(row,columnIndexMap,code,typeEval,noteSur);
                    
                        if(participe != null){   
                        participe.setUser(user);                     
                        importedPersons.add(participe);
                    } else {
                        return "redirect:/index-import?status=BadStructure"; 
                    }
                }

                workbook.close();
            } else {
                return "redirect:/index-import?status=BadFormat"; 
            }

            // Enregistrer les personnes importées dans la table Person
            participeRepository.saveAll(importedPersons);

            return "redirect:/index-import?status=Success"; 
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("file", file);
            return "redirect:/index-import?status=fail"; 
        }
    }

    @GetMapping("/index-import")
public String ListUe(Model model){
    Users user = userRepository.findByLogin("bosley-12");
    List <Ue> Ues =enseignantRepository.findByUser(user).getUes();
    
    Iterable <Evaluation> evaluation = evaluationRepository.findByUe(Ues);
    model.addAttribute("evaluation",evaluation);
    return "import en masse/index";
    

}



}
