package com.cengiz.ilanapiproject.data.dto;


import com.cengiz.ilanapiproject.base.dto.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Cengiz ÖZDEMİR
 * @created 15/11/2024
 */

@Getter
@Setter
@NoArgsConstructor
public class IlanEmlakDto extends BaseDto {

    private Long ilanId;
    @NotNull
    private Integer fkKullaniciId;
    @NotNull
    private Integer fkKategoriId;
    @NotNull
    private String baslik;
    @NotNull
    private String aciklama;
    @NotNull
    private Integer fiyat;
    @NotNull
    private Integer ilId;
    @NotNull
    private Integer ilceId;
    @NotNull
    private Integer mahalleId;

    private String emlakTipi;
    private String imarDurumu;
    private int metrekare;
    private double metrekareFiyati;
    private String adaNo;
    private String parselNo;
    private String paftaNo;
    private double emsal;
    private double gabari;
    private String krediyeUygunluk;
    private String tapuDurumu;
    private String kimden;
    private boolean takas;
    private long fkIlanId;


    private LocalDateTime kayitTarihi;
    private Long kaydedenKullaniciId;
    private LocalDateTime guncellemeTarihi;
    private Long guncelleyenKullaniciId;

    public IlanEmlakDto(Long ilanId, Integer fkKullaniciId, Integer fkKategoriId, String baslik, String aciklama, Integer fiyat, Integer ilId, Integer ilceId, Integer mahalleId, String emlakTipi, String imarDurumu, int metrekare, double metrekareFiyati, String adaNo, String parselNo, String paftaNo, double emsal, double gabari, String krediyeUygunluk, String tapuDurumu, String kimden, boolean takas, long fkIlanId, LocalDateTime kayitTarihi, Long kaydedenKullaniciId, LocalDateTime guncellemeTarihi, Long guncelleyenKullaniciId) {
        this.ilanId = ilanId;
        this.fkKullaniciId = fkKullaniciId;
        this.fkKategoriId = fkKategoriId;
        this.baslik = baslik;
        this.aciklama = aciklama;
        this.fiyat = fiyat;
        this.ilId = ilId;
        this.ilceId = ilceId;
        this.mahalleId = mahalleId;
        this.emlakTipi = emlakTipi;
        this.imarDurumu = imarDurumu;
        this.metrekare = metrekare;
        this.metrekareFiyati = metrekareFiyati;
        this.adaNo = adaNo;
        this.parselNo = parselNo;
        this.paftaNo = paftaNo;
        this.emsal = emsal;
        this.gabari = gabari;
        this.krediyeUygunluk = krediyeUygunluk;
        this.tapuDurumu = tapuDurumu;
        this.kimden = kimden;
        this.takas = takas;
        this.fkIlanId = fkIlanId;
        this.kayitTarihi = kayitTarihi;
        this.kaydedenKullaniciId = kaydedenKullaniciId;
        this.guncellemeTarihi = guncellemeTarihi;
        this.guncelleyenKullaniciId = guncelleyenKullaniciId;
    }
}
