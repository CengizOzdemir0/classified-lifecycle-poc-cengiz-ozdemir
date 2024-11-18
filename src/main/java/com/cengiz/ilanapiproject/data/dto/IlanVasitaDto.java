package com.cengiz.ilanapiproject.data.dto;

import com.cengiz.ilanapiproject.base.dto.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Cengiz ÖZDEMİR
 * @created 12/11/2024
 */

@Getter
@Setter
@NoArgsConstructor
public class IlanVasitaDto extends BaseDto {

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
    @NotNull
    private String marka;
    @NotNull
    private String seri;
    @NotNull
    private String model;
    @NotNull
    private int yil;
    @NotNull
    private String yakit;
    @NotNull
    private String vites;
    @NotNull
    private String aracDurumu;
    @NotNull
    private int kilometre;
    @NotNull
    private String kasaTipi;
    @NotNull
    private int motorGucu;
    @NotNull
    private int motorHacmi;
    @NotNull
    private String cekis;
    @NotNull
    private int kapi;
    @NotNull
    private String renk;
    @NotNull
    private boolean garanti;
    @NotNull
    private boolean agirHasarKayitli;
    @NotNull
    private String plakaUyruk;
    @NotNull
    private String kimden;
    @NotNull
    private boolean takas;
    private LocalDateTime kayitTarihi;
    private Long kaydedenKullaniciId;
    private LocalDateTime guncellemeTarihi;
    private Long guncelleyenKullaniciId;


    public IlanVasitaDto(Long ilanId, Integer fkKullaniciId, Integer fkKategoriId, String baslik, String aciklama, Integer fiyat,
                         Integer ilId, Integer ilceId, Integer mahalleId, String marka, String seri, String model, int yil,
                         String yakit, String vites, String aracDurumu, int kilometre, String kasaTipi, int motorGucu, int motorHacmi,
                         String cekis, int kapi, String renk, boolean garanti, boolean agirHasarKayitli, String plakaUyruk,
                         String kimden, boolean takas, LocalDateTime kayitTarihi, Long kaydedenKullaniciId,
                         LocalDateTime guncellemeTarihi, Long guncelleyenKullaniciId) {

        this.ilanId = ilanId;
        this.fkKullaniciId = fkKullaniciId;
        this.fkKategoriId = fkKategoriId;
        this.baslik = baslik;
        this.aciklama = aciklama;
        this.fiyat = fiyat;
        this.ilId = ilId;
        this.ilceId = ilceId;
        this.mahalleId = mahalleId;
        this.marka = marka;
        this.seri = seri;
        this.model = model;
        this.yil = yil;
        this.yakit = yakit;
        this.vites = vites;
        this.aracDurumu = aracDurumu;
        this.kilometre = kilometre;
        this.kasaTipi = kasaTipi;
        this.motorGucu = motorGucu;
        this.motorHacmi = motorHacmi;
        this.cekis = cekis;
        this.kapi = kapi;
        this.renk = renk;
        this.garanti = garanti;
        this.agirHasarKayitli = agirHasarKayitli;
        this.plakaUyruk = plakaUyruk;
        this.kimden = kimden;
        this.takas = takas;
        this.kayitTarihi = kayitTarihi;
        this.kaydedenKullaniciId = kaydedenKullaniciId;
        this.guncellemeTarihi = guncellemeTarihi;
        this.guncelleyenKullaniciId = guncelleyenKullaniciId;
    }

}
