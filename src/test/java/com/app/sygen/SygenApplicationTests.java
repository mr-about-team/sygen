package com.app.sygen;

import static org.mockito.ArgumentMatchers.matches;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.sygen.entities.Participe;
import com.app.sygen.repositories.EtudiantRepository;
import com.app.sygen.repositories.EvaluationRepository;
import com.app.sygen.repositories.ParticipeRepository;
import com.app.sygen.repositories.UeRepository;
import com.app.sygen.services.CorrectionService;

@SpringBootTest
class SygenApplicationTests {
    
	@Autowired
	EtudiantRepository etudiantRepository ;

	@Autowired
	ParticipeRepository participeRepository ;

	@Autowired
	EvaluationRepository evaluationRepository ;

	@Autowired
	UeRepository ueRepository ;
	@Test
	void contextLoads() {
		//  correctionService.modifierNotesAvancee("21T2330") ;
        //    CorrectionService correctionService = new CorrectionService() ;
          
		// etudiantRepository.findByMatricule("21T233") ;
        
		// participeRepository.findByMatricule("21T2330") ;

		// System.out.println(participeRepository.findByMatriculeAndEvaluation("21T2330", evaluationRepository.findByTypeEvalAndUe("CC", ueRepository.findByCode("INF222"))).get(0).getNomEtudiant());

		
	

}

}
