package com.app.sygen.repositories;

import com.app.sygen.entities.Ue;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface UeRepository extends AppRepository<Ue, Long>
{
    Ue findByCode(String code);

}
