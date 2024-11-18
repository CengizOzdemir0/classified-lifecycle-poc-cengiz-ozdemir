package com.cengiz.ilanapiproject.data.dto;


import com.cengiz.ilanapiproject.base.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author Cengiz ÖZDEMİR
 * @created 13/11/2024
 */

@Getter
@Setter
public class IlanOnayDto extends BaseDto {

    @NotNull
    private Long ilanId;
    @NotNull
    private Long guncelleyenKullaniciId;

}
