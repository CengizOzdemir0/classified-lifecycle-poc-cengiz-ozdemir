package com.cengiz.ilanapiproject.data.mapper;


import com.cengiz.ilanapiproject.data.dto.IlanFavoriDto;
import com.cengiz.ilanapiproject.data.entity.IlanFavori;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Cengiz ÖZDEMİR
 * @created 16/11/2024
 */

@Mapper(componentModel = "spring")
public interface IlanFavoriMapper {

    IlanFavoriDto toDto(IlanFavori ilanFavori);

    IlanFavori toEntity(IlanFavoriDto ilanFavoriDto);

    List<IlanFavori> toEntityList(List<IlanFavoriDto> ilanFavoriDtos);

    List<IlanFavoriDto> toDtoList(List<IlanFavori> ilanFavoris);
}
