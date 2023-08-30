
package com.app.sygen.services;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.sygen.entities.HistoriqueCorrection;
import com.app.sygen.entities.Param;
import com.app.sygen.entities.Participe;
import com.app.sygen.repositories.EtudiantRepository;
import com.app.sygen.repositories.EvaluationRepository;
import com.app.sygen.repositories.HistoriqueCorrectionRepository;
import com.app.sygen.repositories.ParticipeRepository;
import com.app.sygen.repositories.UeRepository;




@Service
public class CorrectionService {

    @Autowired
    UeRepository ueRepository ;

    @Autowired
    ParticipeRepository participeRepository ;

    @Autowired
    EtudiantRepository etudiantRepository ;
    
   

    @Autowired
    HistoriqueCorrectionRepository historiqueCorrectionRepository;

  //-------------------------------------------------------------

   @Autowired
      EvaluationRepository evaluationRepository ;
      
     
    public void updateNote( Param param ,String typeEval, String oldMatricule   , String oldNomEtudiant , String code) {
     
              // Recherche de l'étudi0ant dans la table Participe

        
      Participe participations = participeRepository.findByMatriculeAndEvaluationAndAnneeAndNomEtudiant(oldMatricule, evaluationRepository.findByTypeEvalAndUe(typeEval, ueRepository.findByCode(code)),LocalDate.now().getYear()+"",  oldNomEtudiant);

         
       // Enregistrement de l'historique de correction
            HistoriqueCorrection historiqueCorrection = new HistoriqueCorrection();
            // historiqueCorrection.setId(participations.getUser().getId());
            historiqueCorrection.setAnonymat(participations.getAnonymat());
            historiqueCorrection.setDateModif(participations.getDateImportation());
            historiqueCorrection.setMatricule(participations.getMatricule());
            historiqueCorrection.setNomEtudiant(participations.getNomEtudiant());
            historiqueCorrection.setNote(participations.getNote());

            historiqueCorrectionRepository.save(historiqueCorrection);


         if (participations != null) {
            // Mise à jour de la note dans la table Participe
            participations.setNote(param.getNewNote());
            participations.setNomEtudiant(param.getNewNomEtudiant());
            participations.setAnonymat(param.getNewAnonymat());
            participations.setDateImportation(param.getNewDate());
            participations.setMatricule(param.getNewMatricule());
            // participations.setUserId(newUser);
             participeRepository.save(participations);


           
           
         } 

      
}



   
// // Méthode pour effectuer la modification avancée des notes

public void modifierNotesAvancee(String matricule , String code ) {

    // Récupérer les participations de l'étudiant à partir de la base de données

           Participe participe1 = participeRepository.findByMatriculeAndEvaluationAndAnnee(matricule, evaluationRepository.findByTypeEvalAndUe("CC", ueRepository.findByCode(code)) ,LocalDate.now().getYear()+"" );
           Participe participe2 = participeRepository.findByMatriculeAndEvaluationAndAnnee(matricule, evaluationRepository.findByTypeEvalAndUe("TP", ueRepository.findByCode(code)) ,LocalDate.now().getYear()+"" );
           Participe participe3 = participeRepository.findByMatriculeAndEvaluationAndAnnee(matricule, evaluationRepository.findByTypeEvalAndUe("SN", ueRepository.findByCode(code)) ,LocalDate.now().getYear()+"" );

          // Vérifier si la note de CC est invalide
           if (participe1.getNote() == null && participe2.getNote() != null) {
              
                Float noteTP = Float.valueOf(0);
                noteTP = participe2.getNote();
                 // Convertir la note de TP en échelle de 20
                    Float noteCCConvertie = Float.valueOf(0);
                   noteCCConvertie= (noteTP / 30) * 20;

                   participe1.setNote(noteCCConvertie);
                   // Enregistrer les modifications dans la base de données
                   participeRepository.save(participe1);
             // Vérifier si la note de TP est invalide
           } else if (participe2.getNote()==null && participe1.getNote() != null) {
              Float noteCC = Float.valueOf(0);
              noteCC =participe1.getNote();


                     // Convertir la note de CC en échelle de 30

                      Float noteTPconvertie = Float.valueOf(0);
                noteTPconvertie =  (noteCC / 20) * 30 ;
                participe2.setNote(noteTPconvertie);
                // Enregistrer les modifications dans la base de données
                participeRepository.save(participe2) ;
           }else if (participe3.getNote()!= null && participe1.getNote()==null && participe2.getNote() == null) {
            
              // Récupérer la note de SN de l'étudiant

            Float noteSN = Float.valueOf(0);
            noteSN = participe3.getNote();

            // Convertir la note de SN en échelle de 30
           
            Float noteTPconvertieSN = Float.valueOf(0) ;
           noteTPconvertieSN = (noteSN / 50) * 30 ;
              participe2.setNote(noteTPconvertieSN ) ;

              // Enregistrer les modifications dans la base de données
            participeRepository.save(participe2) ;

            // Convertir la note de SN en échelle de 20
             Float noteCCconvertieSN = Float.valueOf(0) ;
                   //findByEtudiant(etudiantRepository.findByMatricule(matricule));
    noteCCconvertieSN = (noteSN / 50) * 20 ;
             //  Mettre à jour la note de TP de la participation avec la nouvelle valeur
     

               participe1.setNote(noteCCconvertieSN) ;
            
             // Enregistrer les modifications dans la base de données
             participeRepository.save(participe1);
           }

      
               

             
         } 
        
         }