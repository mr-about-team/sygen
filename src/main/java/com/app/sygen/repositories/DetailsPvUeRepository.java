package com.app.sygen.repositories;

import org.springframework.stereotype.Repository;

import com.app.sygen.entities.DetailPvUe;
import java.util.List;
import com.app.sygen.entities.Etudiant;
import com.app.sygen.entities.PvUe;


public interface DetailsPvUeRepository extends AppRepository<DetailPvUe, Long>
{
    DetailPvUe findByEtudiantAndPvUe(Etudiant etudiant, PvUe pvUe);
}
