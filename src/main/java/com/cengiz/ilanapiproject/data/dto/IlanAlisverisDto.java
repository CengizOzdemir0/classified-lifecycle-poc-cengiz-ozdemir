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
public class IlanAlisverisDto extends BaseDto {

    private Long fkIlanId;
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


    private String model;
    private String hafiza;
    private String garanti;
    private String kimden;
    private String durumu;

    private LocalDateTime kayitTarihi;
    private Long kaydedenKullaniciId;
    private LocalDateTime guncellemeTarihi;
    private Long guncelleyenKullaniciId;

    public IlanAlisverisDto(Long fkIlanId, Integer fkKullaniciId, Integer fkKategoriId, String baslik, String aciklama, Integer fiyat, Integer ilId, Integer ilceId, Integer mahalleId, String model, String hafiza, String garanti, String kimden, String durumu, LocalDateTime kayitTarihi, Long kaydedenKullaniciId, LocalDateTime guncellemeTarihi, Long guncelleyenKullaniciId) {
        this.fkIlanId = fkIlanId;
        this.fkKullaniciId = fkKullaniciId;
        this.fkKategoriId = fkKategoriId;
        this.baslik = baslik;
        this.aciklama = aciklama;
        this.fiyat = fiyat;
        this.ilId = ilId;
        this.ilceId = ilceId;
        this.mahalleId = mahalleId;
        this.model = model;
        this.hafiza = hafiza;
        this.garanti = garanti;
        this.kimden = kimden;
        this.durumu = durumu;
        this.kayitTarihi = kayitTarihi;
        this.kaydedenKullaniciId = kaydedenKullaniciId;
        this.guncellemeTarihi = guncellemeTarihi;
        this.guncelleyenKullaniciId = guncelleyenKullaniciId;
    }
}
