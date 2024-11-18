package com.cengiz.ilanapiproject.data.dto;


import com.cengiz.ilanapiproject.base.dto.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author Cengiz ÖZDEMİR
 * @created 15/11/2024
 */

@Getter
@Setter
@NoArgsConstructor
public class IlanEmlakSorguDto extends BaseDto {

    private String baslik;
    private String aciklama;
    private Integer fiyat;
    private Integer ilId;
    private Integer ilceId;
    private Integer mahalleId;

    private String emlakTipi;
    private String imarDurumu;
    private Integer metrekare;
    private Double metrekareFiyati;
    private String adaNo;
    private String parselNo;
    private String paftaNo;
    private Double emsal;
    private Double gabari;
    private String krediyeUygunluk;
    private String tapuDurumu;
    private String kimden;
    private Boolean takas;


}
