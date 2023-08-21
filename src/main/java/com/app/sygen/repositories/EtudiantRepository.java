package com.app.sygen.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.sygen.entities.Etudiant;

@Repository
public interface EtudiantRepository extends AppRepository<Etudiant, Long>
{
	Etudiant findByMatricule(String matricule);

    @Query(value = "SELECT SUM(p.Montant) FROM Paiement p WHERE p.etudiant.id = :idEtudiant", nativeQuery = true)
    Double showStatus(@Param("idEtudiant") Long idEtudiant);
}
