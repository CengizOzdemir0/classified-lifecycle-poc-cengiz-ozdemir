package com.cengiz.ilanapiproject.data.mapper;


import com.cengiz.ilanapiproject.data.dto.IlanDurumDto;
import com.cengiz.ilanapiproject.data.entity.IlanDurum;
import org.mapstruct.Mapper;

import java.util.List;


/**
 * @author Cengiz ÖZDEMİR
 * @created 13/11/2024
 */

@Mapper(componentModel = "spring")
public interface IlanDurumMapper {

    IlanDurumDto toDto(IlanDurum ilanDurum);

    IlanDurum toEntity(IlanDurumDto ilanDurumDto);

    List<IlanDurum> toEntityList(List<IlanDurumDto> ilanDurumDtoList);

    List<IlanDurumDto> toDtoList(List<IlanDurum> ilanDurumList);


}
