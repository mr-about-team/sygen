package com.app.sygen.services.serviceDeliberation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.sygen.entities.Etudiant;
import com.app.sygen.repositories.DetailsPvUeRepository;
import com.app.sygen.repositories.EtudiantRepository;
import com.app.sygen.repositories.PvDeliberationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class IndexDeliberation{

    @Autowired
    EtudiantRepository etudiantRepository;
   
    
    public void deliberation(List<CritereDeliberation> blocCriteres, Long filiere){
        List<Etudiant> etudiantsDeFiliere  =  etudiantRepository.findByFiliere(filiere);
      
        for(Etudiant etudiant : etudiantsDeFiliere){

            for(CritereDeliberation critere : blocCriteres){

                new EtudiantDeliberation(etudiant, critere);

            }
        }






    }

}