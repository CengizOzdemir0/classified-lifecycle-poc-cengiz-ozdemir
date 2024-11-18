package com.cengiz.ilanapiproject.data.dto;

import com.cengiz.ilanapiproject.base.dto.BaseDto;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class IlanAlisverisSorguDto extends BaseDto {


    private Integer fkKategoriId;
    private String baslik;
    private String aciklama;
    private Integer fiyat;
    private Integer ilId;
    private Integer ilceId;
    private Integer mahalleId;


    private String model;
    private String hafiza;
    private String garanti;
    private String kimden;
    private String durumu;

}