package com.cengiz.ilanapiproject.repository;

import com.cengiz.ilanapiproject.data.entity.IlanGoruntulenme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Cengiz ÖZDEMİR
 * @created 14/11/2024
 */


public interface IlanGoruntulenmeRepository extends JpaRepository<IlanGoruntulenme,Long> {

    Optional<IlanGoruntulenme> findByFkIlanId(Long id);
}
