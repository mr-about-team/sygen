package com.app.sygen.repositories;
	
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.sygen.entities.Evaluation;
import com.app.sygen.entities.Ue;
<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> a37235f (production de pv cc, tp et ue)
=======

=======
>>>>>>> 493712e (foction d'importation des notes)
>>>>>>> 9afc213 (foction d'importation des notes)

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
<<<<<<< HEAD

	@Query("SELECT e FROM Evaluation e WHERE e.ue IN :ue")
	List <Evaluation>  findByUe(List<Ue> ue);

=======
	Evaluation findByTypeEvalAndUe(String typeEval, Ue ue);
>>>>>>> a37235f (production de pv cc, tp et ue)
=======
	Evaluation findByTypeEvalAndUe(String typeEval, Ue ue);
=======

	@Query("SELECT e FROM Evaluation e WHERE e.ue IN :ue")
	List <Evaluation>  findByUe(List<Ue> ue);

>>>>>>> 493712e (foction d'importation des notes)
>>>>>>> 9afc213 (foction d'importation des notes)

}
