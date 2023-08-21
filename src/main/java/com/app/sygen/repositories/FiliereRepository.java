package com.app.sygen.repositories;

import org.springframework.stereotype.Repository;

import com.app.sygen.entities.Filiere;

@Repository
public interface FiliereRepository extends AppRepository<Filiere, Long>
{
	/**
	 * Recherche d'une filiere a travers son code
	 */
	Filiere findFirstByCode(String code);
}
