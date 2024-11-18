package com.cengiz.ilanapiproject.repository;

import com.cengiz.ilanapiproject.data.dto.IlanAlisverisDto;
import com.cengiz.ilanapiproject.data.entity.IlanAlisverisDetay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IlanAlisverisDetayRepository extends JpaRepository<IlanAlisverisDetay, Long> {

    @Query("SELECT new com.cengiz.ilanapiproject.data.dto.IlanAlisverisDto(i.id, i.fkKullaniciId, i.fkKategoriId, i.baslik, i.aciklama, " +
            "i.fiyat, i.ilId, i.ilceId, i.mahalleId, d.model, d.hafiza, d.garanti, d.kimden, d.durumu, " +
            "i.kayitTarihi, i.kaydedenKullaniciId, i.guncellemeTarihi, i.guncelleyenKullaniciId) " +
            "FROM IlanAlisverisDetay d JOIN Ilan i ON d.fkIlanId = i.id WHERE i.ilanDurumId = 1")
    List<IlanAlisverisDto> findAllByAktifAlisverisIlanDurum();

    @Query("SELECT new com.cengiz.ilanapiproject.data.dto.IlanAlisverisDto(" +
            "i.id, " +
            "i.fkKullaniciId, " +
            "i.fkKategoriId, " +
            "i.baslik, " +
            "i.aciklama, " +
            "i.fiyat, " +
            "i.ilId, " +
            "i.ilceId, " +
            "i.mahalleId, " +
            "iad.model, " +
            "iad.hafiza, " +
            "iad.garanti, " +
            "iad.kimden, " +
            "iad.durumu, " +
            "i.kayitTarihi, " +
            "i.kaydedenKullaniciId, " +
            "i.guncellemeTarihi, " +
            "i.guncelleyenKullaniciId) " +
            "FROM Ilan i " +
            "JOIN IlanAlisverisDetay iad ON i.id = iad.fkIlanId " +
            "WHERE (:fkKategoriId IS NULL OR i.fkKategoriId = :fkKategoriId) " +
            "AND (:baslik IS NULL OR i.baslik LIKE %:baslik%) " +
            "AND (:aciklama IS NULL OR i.aciklama LIKE %:aciklama%) " +
            "AND (:fiyat IS NULL OR i.fiyat = :fiyat) " +
            "AND (:ilId IS NULL OR i.ilId = :ilId) " +
            "AND (:ilceId IS NULL OR i.ilceId = :ilceId) " +
            "AND (:mahalleId IS NULL OR i.mahalleId = :mahalleId) " +
            "AND (:model IS NULL OR iad.model LIKE %:model%) " +
            "AND (:hafiza IS NULL OR iad.hafiza LIKE %:hafiza%) " +
            "AND (:garanti IS NULL OR iad.garanti LIKE %:garanti%) " +
            "AND (:kimden IS NULL OR iad.kimden LIKE %:kimden%) " +
            "AND (:durumu IS NULL OR iad.durumu LIKE %:durumu%) " +
            "AND i.ilanDurumId = 1")
    List<IlanAlisverisDto> findByIlanAlisveris(@Param("fkKategoriId") Integer fkKategoriId,
                                               @Param("baslik") String baslik,
                                               @Param("aciklama") String aciklama,
                                               @Param("fiyat") Integer fiyat,
                                               @Param("ilId") Integer ilId,
                                               @Param("ilceId") Integer ilceId,
                                               @Param("mahalleId") Integer mahalleId,
                                               @Param("model") String model,
                                               @Param("hafiza") String hafiza,
                                               @Param("garanti") String garanti,
                                               @Param("kimden") String kimden,
                                               @Param("durumu") String durumu);

}
