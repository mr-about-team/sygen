package com.app.sygen.repositories;
	
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.sygen.entities.Evaluation;
import com.app.sygen.entities.Ue;
<<<<<<< HEAD
=======

>>>>>>> a37235f (production de pv cc, tp et ue)

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
<<<<<<< HEAD

	@Query("SELECT e FROM Evaluation e WHERE e.ue IN :ue")
	List <Evaluation>  findByUe(List<Ue> ue);

=======
	Evaluation findByTypeEvalAndUe(String typeEval, Ue ue);
>>>>>>> a37235f (production de pv cc, tp et ue)

}
