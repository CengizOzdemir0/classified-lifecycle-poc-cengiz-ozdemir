package com.cengiz.ilanapiproject.data.dto;


import com.cengiz.ilanapiproject.base.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Cengiz ÖZDEMİR
 * @created 14/11/2024
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IlanDurumDto extends BaseDto {

    private Long fkIlanId;
    private Integer durumId;
    private LocalDateTime kayitTarihi;
    private Long kaydedenKullaniciId;
    private LocalDateTime guncellemeTarihi;
    private Long guncelleyenKullaniciId;
}
