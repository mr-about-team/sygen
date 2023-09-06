package com.app.sygen.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.sygen.entities.Etudiant;
import com.app.sygen.entities.Evaluation;
import com.app.sygen.entities.Participe;

public interface ParticipeRepository extends AppRepository<Participe, Long>
{
	 Participe findByMatricule(String matricule);

     List<Participe> findByEtudiant(Etudiant etudiant);

     List<Participe> findByEvaluation(Evaluation evaluation);
<<<<<<< HEAD
     List<Participe> findByAnneeImportationAndEvaluationOrderByNomEtudiantAsc(String Annee, Evaluation evaluation);
     List<Participe> findByEtudiantAndAnneeImportationAndEvaluationOrderByNomEtudiantAsc(Etudiant etudiant, String annee, Evaluation evaluation);
=======


     List<Participe> findFirstByEvaluationIn(List<Evaluation> evaluations);
     Optional<Participe>  findFirstByEvaluation(Evaluation evaluation);;
     List<Participe> findByEvaluation_TypeEvalAndEvaluation_Ue_Code(String TypeEval, String Code);

>>>>>>> 419876d (modification au traitement des importations)
}
