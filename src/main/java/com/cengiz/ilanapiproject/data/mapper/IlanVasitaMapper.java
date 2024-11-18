package com.cengiz.ilanapiproject.data.mapper;


import com.cengiz.ilanapiproject.data.dto.IlanVasitaDto;
import com.cengiz.ilanapiproject.data.dto.IlanVasitaTalepDto;
import org.mapstruct.Mapper;

/**
 * @author Cengiz ÖZDEMİR
 * @created 14/11/2024
 */

@Mapper(componentModel = "spring")
public interface IlanVasitaMapper {

    IlanVasitaDto toDto(IlanVasitaTalepDto ilanVasitaTalepDto);
}
