package com.app.sygen.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.sygen.entities.Participe;
import com.app.sygen.entities.PvCcData;
import com.app.sygen.repositories.EvaluationRepository;
import com.app.sygen.repositories.ParticipeRepository;
import com.app.sygen.repositories.UeRepository;
@Service
public class ParticipeService {
    @Autowired
    private ParticipeRepository participeRepository;
    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private UeRepository ueRepository;
    public List<PvCcData> getPvCcData(){
        
        List<Participe> participes = participeRepository.findByAnneeImportationAndEvaluationOrderByNomEtudiantAsc("2023", evaluationRepository.findByTypeEvalAndUe("cc", ueRepository.findByCode("inf-242")));
        
        List<PvCcData> pvsCc = new ArrayList<PvCcData>();


        for (Participe participe : participes) {
            PvCcData pvCc = new PvCcData();

            pvCc.setMatricule(participe.getMatricule());
            pvCc.setNom(participe.getNomEtudiant());
            pvCc.setNote(participe.getNote()+0.0);
            pvsCc.add(pvCc);
        }
        return pvsCc;
    }
}
