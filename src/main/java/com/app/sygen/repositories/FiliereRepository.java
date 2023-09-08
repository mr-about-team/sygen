package com.app.sygen.repositories;

import org.springframework.stereotype.Repository;

import com.app.sygen.entities.Filiere;
import java.util.List;


public interface FiliereRepository extends AppRepository<Filiere, Long>
{
	/**
	 * Recherche d'une filiere a travers son code
	 */
	Filiere findFirstByCode(String code);
	Filiere findByCode(String code);
}
