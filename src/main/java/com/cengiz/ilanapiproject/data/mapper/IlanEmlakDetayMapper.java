package com.cengiz.ilanapiproject.data.mapper;


import com.cengiz.ilanapiproject.data.dto.IlanEmlakDto;
import com.cengiz.ilanapiproject.data.dto.IlanEmlakTalepDto;
import org.mapstruct.Mapper;

/**
 * @author Cengiz ÖZDEMİR
 * @created 15/11/2024
 */

@Mapper(componentModel = "spring")
public interface IlanEmlakDetayMapper {

    IlanEmlakDto toDto(IlanEmlakTalepDto ilanEmlakTalepDto);
}
