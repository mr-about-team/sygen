package com.app.sygen.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.sygen.entities.Etudiant;
import com.app.sygen.entities.Filiere;

@Repository
public interface EtudiantRepository extends AppRepository<Etudiant, Long>
{
	/**
	 * Recherche d'un etudiant a partir de son matricule
	 */
	Etudiant findByMatricule(String matricule);
	
	/**
	 * Liste des etudiants d'une filiere
	 */
	@Query(value = "SELECT e FROM Etudiant e WHERE e.filiere.id = :filiere", nativeQuery = true)
	List<Etudiant> findByFiliere(@Param("filiere") Long id);
	
	/**
	 * Liste des etudiants d'une filiere
	 */
	List<Etudiant> findByFiliere(Filiere filiere);

    @Query(value = "SELECT SUM(p.Montant) FROM Paiement p WHERE p.etudiant.id = :idEtudiant", nativeQuery = true)
    Double showStatus(@Param("idEtudiant") Long idEtudiant);
}
