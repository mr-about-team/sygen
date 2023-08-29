package com.app.sygen.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.sygen.entities.Etudiant;
import com.app.sygen.entities.Evaluation;
import com.app.sygen.entities.Participe;

public interface ParticipeRepository extends AppRepository<Participe, Long>
{
	 Participe findByMatricule(String matricule);

     List<Participe> findByEtudiant(Etudiant etudiant);

     List<Participe> findByEvaluation(Evaluation evaluation);
     List<Participe> findByAnneeImportationAndEvaluationOrderByNomEtudiantAsc(String Annee, Evaluation evaluation);
     List<Participe> findByEtudiantAndAnneeImportationAndEvaluationOrderByNomEtudiantAsc(Etudiant etudiant, String annee, Evaluation evaluation);
}
