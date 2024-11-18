package com.cengiz.ilanapiproject.data.dto;

import com.cengiz.ilanapiproject.base.dto.BaseDto;
import com.cengiz.ilanapiproject.base.validation.ValidDeger;
import com.cengiz.ilanapiproject.base.validation.ValidYil;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Cengiz ÖZDEMİR
 * @created 13/11/2024
 */

@Getter
@Setter
public class IlanVasitaTalepDto extends BaseDto {


    @NotNull
    @ValidDeger
    private Integer fkKullaniciId;
    @NotNull
    @ValidDeger
    private Integer fkKategoriId;
    @NotNull
    private String baslik;
    @NotNull
    private String aciklama;
    @NotNull
    @ValidDeger
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
    @ValidYil
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

}
