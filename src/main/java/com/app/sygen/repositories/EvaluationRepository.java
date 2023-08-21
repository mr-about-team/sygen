package com.app.sygen.repositories;

import org.springframework.stereotype.Repository;

import com.app.sygen.entities.Evaluation;

@Repository
public interface EvaluationRepository extends AppRepository<Evaluation, Long>
{
	Evaluation findByTypeEvalAndUe_Code(String typeEval, String code);
}
