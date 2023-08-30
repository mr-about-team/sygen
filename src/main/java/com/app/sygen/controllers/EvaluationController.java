package com.app.sygen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.sygen.entities.Evaluation;
import com.app.sygen.entities.Ue;
import com.app.sygen.repositories.EvaluationRepository;
import com.app.sygen.repositories.UeRepository;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/examen")
public class EvaluationController 
{
    @Autowired
    private EvaluationRepository exam;

    @Autowired
    private UeRepository ue;

    @GetMapping("/new")
    public String showExamForm(Model model){

        List<Ue> listue=ue.findAll();
        model.addAttribute("listue",listue);
        model.addAttribute("exams",new Evaluation());
        return "/examen/form";
    }

    @PostMapping("/save")
    public String saveExam(Evaluation exams){
        exam.save(exams);

        return "redirect:/examen";
    }

    @GetMapping("/")
    public String listExam(Model model){
        List<Evaluation> listExam=exam.findAll();
        model.addAttribute("listExam",listExam);
        return "/examen/list";
    }

    @GetMapping("/edit/{idEval}")
    public String editForm(@PathVariable("idEval") Long id, Model model){
        Evaluation exams= exam.findById( id).get();
        model.addAttribute("exams", exams);
        List<Ue> listue=ue.findAll();
        model.addAttribute("listue", listue);
        return "/examen/form";
    }

    @GetMapping("/delete/{idEval}")
    public String delete(@PathVariable ("idEval") Long id, Model model){
        exam.deleteById(id);
        return "redirect:/examen";
    }

    @ModelAttribute("nombres")
    public List<Integer> getNombres(){
        List<Integer> nombres = new ArrayList<>();
        nombres.add(1);
        nombres.add(2);
        nombres.add(3);
        nombres.add(4);
        return nombres;

    }

    @ModelAttribute("types")
    public List<String > getTypes() {
        List<String> types=new ArrayList<>();
        types.add("SN");
        types.add("CC");
        types.add("TP");
        return types;
    }    
}
