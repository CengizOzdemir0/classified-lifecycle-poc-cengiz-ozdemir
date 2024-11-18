package com.cengiz.ilanapiproject.data.dto;

import com.cengiz.ilanapiproject.base.dto.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author Cengiz ÖZDEMİR
 * @created 14/11/2024
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IlanGoruntulenmeDto extends BaseDto {

    @NotNull
    private Long fkIlanId;
    private Long goruntulenmeSayisi;
}
