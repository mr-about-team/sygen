package com.app.sygen.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.sygen.entities.Etudiant;
import com.app.sygen.entities.Evaluation;
import com.app.sygen.entities.Participe;

@Repository
public interface ParticipeRepository extends AppRepository<Participe, Long>
{
	//  Participe findByMatricule(String matricule);
    Participe findByMatricule(String matricule);

    //  List<Participe> findByEtudiant(Etudiant etudiant);

     List<Participe> findByEvaluation(Evaluation evaluation);

     Participe findByMatriculeAndEvaluationAndAnnee(String matricule, Evaluation evaluation , String annee);

     Participe findByMatriculeAndEvaluationAndAnneeAndNomEtudiant(String matricule, Evaluation evaluation, String annee, String nomEtudiant);

     List<Participe> findByMatriculeAndEvaluation_Ue_Code(String matricule, String evaluation_Ue_Code);
}
