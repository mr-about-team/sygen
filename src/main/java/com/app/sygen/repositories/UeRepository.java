package com.app.sygen.repositories;

import org.springframework.stereotype.Repository;

import com.app.sygen.entities.Ue;
import java.util.List;


@Repository
public interface UeRepository extends AppRepository<Ue, Long>
{
    Ue findByCode(String code);
}
