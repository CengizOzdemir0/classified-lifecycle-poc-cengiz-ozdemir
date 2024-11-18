package com.cengiz.ilanapiproject.repository;

import com.cengiz.ilanapiproject.data.dto.IlanEmlakDto;
import com.cengiz.ilanapiproject.data.entity.IlanEmlakDetay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IlanEmlakDetayRepository extends JpaRepository<IlanEmlakDetay, Long> {


    @Query("SELECT new com.cengiz.ilanapiproject.data.dto.IlanEmlakDto(i.id, i.fkKullaniciId, i.fkKategoriId, i.baslik, i.aciklama, " +
            "i.fiyat, i.ilId, i.ilceId, i.mahalleId, d.emlakTipi, d.imarDurumu, d.metrekare, d.metrekareFiyati, d.adaNo, " +
            "d.parselNo, d.paftaNo, d.emsal, d.gabari, d.krediyeUygunluk, d.tapuDurumu, d.kimden, d.takas, d.fkIlanId, " +
            "i.kayitTarihi, i.kaydedenKullaniciId, i.guncellemeTarihi, i.guncelleyenKullaniciId) " +
            "FROM IlanEmlakDetay d JOIN Ilan i ON d.fkIlanId = i.id WHERE i.ilanDurumId = 1")
    List<IlanEmlakDto> findAllByAktifEmlakIlanDurum();

    @Query("SELECT new com.cengiz.ilanapiproject.data.dto.IlanEmlakDto(" +
            "i.id, " +
            "i.fkKullaniciId, " +
            "i.fkKategoriId, " +
            "i.baslik, " +
            "i.aciklama, " +
            "i.fiyat, " +
            "i.ilId, " +
            "i.ilceId, " +
            "i.mahalleId, " +
            "ied.emlakTipi, " +
            "ied.imarDurumu, " +
            "ied.metrekare, " +
            "ied.metrekareFiyati, " +
            "ied.adaNo, " +
            "ied.parselNo, " +
            "ied.paftaNo, " +
            "ied.emsal, " +
            "ied.gabari, " +
            "ied.krediyeUygunluk, " +
            "ied.tapuDurumu, " +
            "ied.kimden, " +
            "ied.takas, " +
            "ied.fkIlanId, " +
            "i.kayitTarihi, " +
            "i.kaydedenKullaniciId, " +
            "i.guncellemeTarihi, " +
            "i.guncelleyenKullaniciId) " +
            "FROM Ilan i " +
            "JOIN IlanEmlakDetay ied ON i.id = ied.fkIlanId " +
            "WHERE (:baslik IS NULL OR i.baslik LIKE %:baslik%) " +
            "AND (:aciklama IS NULL OR i.aciklama LIKE %:aciklama%) " +
            "AND (:fiyat IS NULL OR i.fiyat = :fiyat) " +
            "AND (:ilId IS NULL OR i.ilId = :ilId) " +
            "AND (:ilceId IS NULL OR i.ilceId = :ilceId) " +
            "AND (:mahalleId IS NULL OR i.mahalleId = :mahalleId) " +
            "AND (:emlakTipi IS NULL OR ied.emlakTipi LIKE %:emlakTipi%) " +
            "AND (:imarDurumu IS NULL OR ied.imarDurumu LIKE %:imarDurumu%) " +
            "AND (:metrekare IS NULL OR ied.metrekare = :metrekare) " +
            "AND (:metrekareFiyati IS NULL OR ied.metrekareFiyati = :metrekareFiyati) " +
            "AND (:adaNo IS NULL OR ied.adaNo LIKE %:adaNo%) " +
            "AND (:parselNo IS NULL OR ied.parselNo LIKE %:parselNo%) " +
            "AND (:paftaNo IS NULL OR ied.paftaNo LIKE %:paftaNo%) " +
            "AND (:emsal IS NULL OR ied.emsal = :emsal) " +
            "AND (:gabari IS NULL OR ied.gabari = :gabari) " +
            "AND (:krediyeUygunluk IS NULL OR ied.krediyeUygunluk LIKE %:krediyeUygunluk%) " +
            "AND (:tapuDurumu IS NULL OR ied.tapuDurumu LIKE %:tapuDurumu%) " +
            "AND (:kimden IS NULL OR ied.kimden LIKE %:kimden%) " +
            "AND (:takas IS NULL OR ied.takas = :takas) " +
            "AND i.ilanDurumId = 1")
    List<IlanEmlakDto> findByIlanEmlak(
            @Param("baslik") String baslik,
            @Param("aciklama") String aciklama,
            @Param("fiyat") Integer fiyat,
            @Param("ilId") Integer ilId,
            @Param("ilceId") Integer ilceId,
            @Param("mahalleId") Integer mahalleId,
            @Param("emlakTipi") String emlakTipi,
            @Param("imarDurumu") String imarDurumu,
            @Param("metrekare") Integer metrekare,
            @Param("metrekareFiyati") Double metrekareFiyati,
            @Param("adaNo") String adaNo,
            @Param("parselNo") String parselNo,
            @Param("paftaNo") String paftaNo,
            @Param("emsal") Double emsal,
            @Param("gabari") Double gabari,
            @Param("krediyeUygunluk") String krediyeUygunluk,
            @Param("tapuDurumu") String tapuDurumu,
            @Param("kimden") String kimden,
            @Param("takas") Boolean takas
    );


}