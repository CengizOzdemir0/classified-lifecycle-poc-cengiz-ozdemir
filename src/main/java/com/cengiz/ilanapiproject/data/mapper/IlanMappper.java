package com.cengiz.ilanapiproject.data.mapper;


import com.cengiz.ilanapiproject.data.dto.IlanDto;
import com.cengiz.ilanapiproject.data.entity.Ilan;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Cengiz ÖZDEMİR
 * @created 14/11/2024
 */

@Mapper(componentModel = "spring")
public interface IlanMappper {

    IlanDto toDto(Ilan ilan);

    Ilan toEntity(IlanDto ilanDto);

    List<Ilan> toEntityList(List<IlanDto> ilanDtos);

    List<IlanDto> toDtoList(List<Ilan> ilanList);


}
