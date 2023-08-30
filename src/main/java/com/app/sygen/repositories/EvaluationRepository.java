package com.app.sygen.repositories;
	
import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.sygen.entities.Evaluation;
import com.app.sygen.entities.Ue;


public interface EvaluationRepository extends AppRepository<Evaluation, Long>
{
	/**
	 * Recupere la liste des evaluation d'un type donnée (CC, SN, etc)
	 */
	List<Evaluation> findByTypeEval(String typeEval);
	
	/**
	 * Recupere une evaluation d'un type pour une UE 
	 */
	Evaluation findByTypeEvalAndUe_Code(String typeEval, String code);

	Evaluation findByTypeEvalAndUe(String string, Ue findByCode);

	
}
