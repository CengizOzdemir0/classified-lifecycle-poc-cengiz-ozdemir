package com.cengiz.ilanapiproject.repository;

import com.cengiz.ilanapiproject.data.dto.IlanRaporDto;
import com.cengiz.ilanapiproject.data.entity.Ilan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Cengiz ÖZDEMİR
 * @created 12/11/2024
 */

public interface IlanRepository extends JpaRepository<Ilan, Long> {

    Boolean existsByBaslikAndAciklamaAndFkKategoriId(String baslik, String aciklama, Integer kategori);

    @Query("SELECT new com.cengiz.ilanapiproject.data.dto.IlanRaporDto(" +
            "i.fkKategoriId, " +
            "COUNT(i), " +
            "SUM(CASE WHEN i.ilanDurumId = 1 THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN i.ilanDurumId = 3 THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN i.ilanDurumId = 2 THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN i.ilanDurumId = 5 THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN i.ilanDurumId = 4 THEN 1 ELSE 0 END) " +
            ") " +
            "FROM Ilan i " +
            "GROUP BY i.fkKategoriId")
    List<IlanRaporDto> findIlanRaporByKategori();


    List<Ilan> findByFkKategoriIdAndIlanDurumId(Integer fkKategoriId, Integer ilanDurumId);
    List<Ilan> findByFkKategoriIdAndIlanDurumIdAndFkKullaniciId(Integer fkKategoriId, Integer ilanDurumId, Long kullaniciId);

}
