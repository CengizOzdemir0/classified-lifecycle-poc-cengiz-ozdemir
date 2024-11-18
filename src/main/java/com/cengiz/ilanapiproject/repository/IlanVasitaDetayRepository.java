package com.cengiz.ilanapiproject.repository;

import com.cengiz.ilanapiproject.data.dto.IlanVasitaDto;
import com.cengiz.ilanapiproject.data.entity.IlanVasitaDetay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Cengiz ÖZDEMİR
 * @created 12/11/2024
 */

public interface IlanVasitaDetayRepository extends JpaRepository<IlanVasitaDetay, Long> {


    @Query("SELECT new com.cengiz.ilanapiproject.data.dto.IlanVasitaDto(i.id, i.fkKullaniciId, i.fkKategoriId, i.baslik, i.aciklama, " +
            "i.fiyat, i.ilId, i.ilceId, i.mahalleId, d.marka, d.seri, d.model, d.yil, d.yakit, d.vites, d.aracDurumu, d.kilometre, " +
            "d.kasaTipi, d.motorGucu, d.motorHacmi, d.cekis, d.kapi, d.renk, d.garanti, d.agirHasarKayitli, d.plakaUyruk, d.kimden, " +
            "d.takas, i.kayitTarihi, i.kaydedenKullaniciId, i.guncellemeTarihi, i.guncelleyenKullaniciId) " +
            "FROM IlanVasitaDetay d JOIN Ilan i ON d.fkIlanId = i.id WHERE i.ilanDurumId = 1")
    List<IlanVasitaDto> findAllByAktifIlanDurum();


    @Query("SELECT new com.cengiz.ilanapiproject.data.dto.IlanVasitaDto(" +
            "i.id, " +
            "i.fkKullaniciId, " +
            "i.fkKategoriId, " +
            "i.baslik, " +
            "i.aciklama, " +
            "i.fiyat, " +
            "i.ilId, " +
            "i.ilceId, " +
            "i.mahalleId, " +
            "iad.marka, " +
            "iad.seri, " +
            "iad.model, " +
            "iad.yil, " +
            "iad.yakit, " +
            "iad.vites, " +
            "iad.aracDurumu, " +
            "iad.kilometre, " +
            "iad.kasaTipi, " +
            "iad.motorGucu, " +
            "iad.motorHacmi, " +
            "iad.cekis, " +
            "iad.kapi, " +
            "iad.renk, " +
            "iad.garanti, " +
            "iad.agirHasarKayitli, " +
            "iad.plakaUyruk, " +
            "iad.kimden, " +
            "iad.takas, " +
            "i.kayitTarihi, " +
            "i.kaydedenKullaniciId, " +
            "i.guncellemeTarihi, " +
            "i.guncelleyenKullaniciId) " +
            "FROM Ilan i " +
            "JOIN IlanVasitaDetay iad ON i.id = iad.fkIlanId " +
            "WHERE (:baslik IS NULL OR i.baslik LIKE %:baslik%) " +
            "AND (:aciklama IS NULL OR i.aciklama LIKE %:aciklama%) " +
            "AND (:fiyat IS NULL OR i.fiyat = :fiyat) " +
            "AND (:ilId IS NULL OR i.ilId = :ilId) " +
            "AND (:ilceId IS NULL OR i.ilceId = :ilceId) " +
            "AND (:mahalleId IS NULL OR i.mahalleId = :mahalleId) " +
            "AND (:marka IS NULL OR iad.marka LIKE %:marka%) " +
            "AND (:seri IS NULL OR iad.seri LIKE %:seri%) " +
            "AND (:model IS NULL OR iad.model LIKE %:model%) " +
            "AND (:yil IS NULL OR iad.yil = :yil) " +
            "AND (:yakit IS NULL OR iad.yakit LIKE %:yakit%) " +
            "AND (:vites IS NULL OR iad.vites LIKE %:vites%) " +
            "AND (:aracDurumu IS NULL OR iad.aracDurumu LIKE %:aracDurumu%) " +
            "AND (:kilometre IS NULL OR iad.kilometre = :kilometre) " +
            "AND (:kasaTipi IS NULL OR iad.kasaTipi LIKE %:kasaTipi%) " +
            "AND (:motorGucu IS NULL OR iad.motorGucu = :motorGucu) " +
            "AND (:motorHacmi IS NULL OR iad.motorHacmi = :motorHacmi) " +
            "AND (:cekis IS NULL OR iad.cekis LIKE %:cekis%) " +
            "AND (:kapi IS NULL OR iad.kapi = :kapi) " +
            "AND (:renk IS NULL OR iad.renk LIKE %:renk%) " +
            "AND (:garanti IS NULL OR iad.garanti = :garanti) " +
            "AND (:agirHasarKayitli IS NULL OR iad.agirHasarKayitli = :agirHasarKayitli) " +
            "AND (:plakaUyruk IS NULL OR iad.plakaUyruk LIKE %:plakaUyruk%) " +
            "AND (:kimden IS NULL OR iad.kimden LIKE %:kimden%) " +
            "AND (:takas IS NULL OR iad.takas = :takas) " +
            "AND i.ilanDurumId = 1")
    List<IlanVasitaDto> findByIlanVasita(@Param("baslik") String baslik,
                                         @Param("aciklama") String aciklama,
                                         @Param("fiyat") Integer fiyat,
                                         @Param("ilId") Integer ilId,
                                         @Param("ilceId") Integer ilceId,
                                         @Param("mahalleId") Integer mahalleId,
                                         @Param("marka") String marka,
                                         @Param("seri") String seri,
                                         @Param("model") String model,
                                         @Param("yil") Integer yil,
                                         @Param("yakit") String yakit,
                                         @Param("vites") String vites,
                                         @Param("aracDurumu") String aracDurumu,
                                         @Param("kilometre") Integer kilometre,
                                         @Param("kasaTipi") String kasaTipi,
                                         @Param("motorGucu") Integer motorGucu,
                                         @Param("motorHacmi") Integer motorHacmi,
                                         @Param("cekis") String cekis,
                                         @Param("kapi") Integer kapi,
                                         @Param("renk") String renk,
                                         @Param("garanti") Boolean garanti,
                                         @Param("agirHasarKayitli") Boolean agirHasarKayitli,
                                         @Param("plakaUyruk") String plakaUyruk,
                                         @Param("kimden") String kimden,
                                         @Param("takas") Boolean takas);

}
