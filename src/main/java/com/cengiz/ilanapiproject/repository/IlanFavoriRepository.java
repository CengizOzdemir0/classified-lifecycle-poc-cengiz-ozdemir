package com.cengiz.ilanapiproject.repository;

import com.cengiz.ilanapiproject.data.entity.IlanFavori;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Cengiz ÖZDEMİR
 * @created 16/11/2024
 */

public interface IlanFavoriRepository extends JpaRepository<IlanFavori, Long> {

    List<IlanFavori> findByFkIlanId(Long id);
    List<IlanFavori> findByFkKullaniciId(Long id);
}
