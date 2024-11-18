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
public class IlanAlisverisTalepDto extends BaseDto {


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
}
