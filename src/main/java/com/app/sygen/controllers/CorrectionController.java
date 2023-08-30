

package com.app.sygen.controllers ;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.app.sygen.entities.Param;
import com.app.sygen.entities.Participe;
import com.app.sygen.repositories.EvaluationRepository;
import com.app.sygen.repositories.ParticipeRepository;
import com.app.sygen.repositories.UeRepository;
import com.app.sygen.services.CorrectionService;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class CorrectionController {
  @Autowired
  UeRepository ueRepository ;
   @Autowired
   private CorrectionService correctionService = new CorrectionService();

   @Autowired
   ParticipeRepository participeRepository ;

   @Autowired
   EvaluationRepository evaluationRepository ;

   public CorrectionController(CorrectionService correctionService){
    this.correctionService = correctionService ;

   }
@RequestMapping(method = RequestMethod.PUT , path = "/update-note/{typeEval}/{oldMatricule}/{oldNomEtudiant}/{code}" )
   public void updateNote( @RequestBody Param param,  @PathVariable String oldMatricule , @PathVariable String typeEval , @PathVariable String code , @PathVariable String oldNomEtudiant ) {


       //TODO: process PUT request


       this.correctionService.updateNote( param ,typeEval, oldMatricule  ,oldNomEtudiant , code  );
       
   } 

   




   @RequestMapping(method = RequestMethod.PUT , path = "modifierNotesAvancee/{matricule}/{code}" )

   public String modifierNotesAvanceeodifierNotesAvancee(@PathVariable("matricule") String matricule ,  @PathVariable("code") String code , Model model  ){
     
   
     correctionService.modifierNotesAvancee(matricule , code);

     return matricule ;
     
   }


    @GetMapping(path = "test")
    public String testcontroller(Model model , String matricule ,  String code){
            model.addAttribute("matricule" , matricule) ;
            model.addAttribute("code", code) ;


     return "correction/index" ;

    }

    @GetMapping(path = "return")
    public String testcontroller(Model model ,Participe p  , Param param){

      model.addAttribute("p", p) ;
      model.addAttribute(param) ;

      return "correction/cherif" ;
    }
    
  }
