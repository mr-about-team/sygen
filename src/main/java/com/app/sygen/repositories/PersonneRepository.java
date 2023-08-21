package com.app.sygen.repositories;

import org.springframework.stereotype.Repository;

import com.app.sygen.entities.Personne;

@Repository
public interface PersonneRepository extends AppRepository<Personne, Long>
{
	Personne findByMatricule(String matricule);
}
