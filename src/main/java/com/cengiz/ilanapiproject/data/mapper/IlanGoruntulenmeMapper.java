package com.cengiz.ilanapiproject.data.mapper;


import com.cengiz.ilanapiproject.data.dto.IlanGoruntulenmeDto;
import com.cengiz.ilanapiproject.data.entity.IlanGoruntulenme;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Cengiz ÖZDEMİR
 * @created 14/11/2024
 */

@Mapper(componentModel = "spring")
public interface IlanGoruntulenmeMapper {

    IlanGoruntulenmeDto toDto(IlanGoruntulenme ilanGoruntulenme);

    IlanGoruntulenme toEntity(IlanGoruntulenmeDto ilanGoruntulenmeDto);

    List<IlanGoruntulenme> toEntityList(List<IlanGoruntulenmeDto> ilanGoruntulenmeDtos);

    List<IlanGoruntulenmeDto> toDtoList(List<IlanGoruntulenme> ilanGoruntulenmes);
}
