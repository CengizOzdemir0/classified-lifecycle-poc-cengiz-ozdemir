package com.cengiz.ilanapiproject.data.mapper;


import com.cengiz.ilanapiproject.data.dto.IlanAlisverisDto;
import com.cengiz.ilanapiproject.data.dto.IlanAlisverisTalepDto;
import org.mapstruct.Mapper;

/**
 * @author Cengiz ÖZDEMİR
 * @created 15/11/2024
 */

@Mapper(componentModel = "spring")
public interface IlanAlisverisDetayMapper {

    IlanAlisverisDto toDto(IlanAlisverisTalepDto ilanAlisverisTalepDto);
}
