package com.cengiz.ilanapiproject.data.dto;


import com.cengiz.ilanapiproject.base.dto.BaseDto;
import lombok.*;


/**
 * @author Cengiz ÖZDEMİR
 * @created 15/11/2024
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IlanVasitaSorguDto extends BaseDto {

    private String baslik;
    private String aciklama;
    private Integer fiyat;
    private Integer ilId;
    private Integer ilceId;
    private Integer mahalleId;
    private String marka;
    private String seri;
    private String model;
    private Integer yil;
    private String yakit;
    private String vites;
    private String aracDurumu;
    private Integer kilometre;
    private String kasaTipi;
    private Integer motorGucu;
    private Integer motorHacmi;
    private String cekis;
    private Integer kapi;
    private String renk;
    private Boolean garanti;
    private Boolean agirHasarKayitli;
    private String plakaUyruk;
    private String kimden;
    private Boolean takas;


}
