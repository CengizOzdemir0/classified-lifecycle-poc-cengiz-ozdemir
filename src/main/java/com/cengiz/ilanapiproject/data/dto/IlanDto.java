package com.cengiz.ilanapiproject.data.dto;


import com.cengiz.ilanapiproject.base.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Cengiz ÖZDEMİR
 * @created 14/11/2024
 */

@Getter
@Setter
public class IlanDto extends BaseDto {


    private Long fkKullaniciId;
    private Integer fkKategoriId;
    private String baslik;
    private String aciklama;
    private Double fiyat;
    private LocalDateTime ilanBitisTarihi;
    private Integer ilanDurumId;
    private Integer ilId;
    private Integer ilceId;
    private Integer mahalleId;


}
