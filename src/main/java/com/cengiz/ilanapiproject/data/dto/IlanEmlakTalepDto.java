package com.cengiz.ilanapiproject.data.dto;


import com.cengiz.ilanapiproject.base.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Cengiz ÖZDEMİR
 * @created 15/11/2024
 */

@Getter
@Setter
public class IlanEmlakTalepDto extends BaseDto {


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

}
