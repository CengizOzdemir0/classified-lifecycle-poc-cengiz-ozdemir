package com.cengiz.ilanapiproject.data.dto;

import com.cengiz.ilanapiproject.base.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Cengiz ÖZDEMİR
 * @created 16/11/2024
 */

@Getter
@Setter
public class IlanFavoriDto extends BaseDto {


    private Long fkIlanId;
    private Long fkKullaniciId;
    private LocalDateTime kayitTarihi;


}
