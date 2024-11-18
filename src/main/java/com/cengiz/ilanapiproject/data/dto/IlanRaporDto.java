package com.cengiz.ilanapiproject.data.dto;

import com.cengiz.ilanapiproject.base.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Cengiz ÖZDEMİR
 * @created 14/11/2024
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IlanRaporDto extends BaseDto {

    private int fkKategoriId;
    private Long toplam;
    private Long aktif;
    private Long pasif;
    private Long onayBekliyor;
    private Long mukerrer;
    private Long redEdildi;


}