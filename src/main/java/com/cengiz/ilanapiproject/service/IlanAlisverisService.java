package com.cengiz.ilanapiproject.service;

import com.cengiz.ilanapiproject.config.domain.RestResponse;
import com.cengiz.ilanapiproject.data.dto.IlanAlisverisDto;
import com.cengiz.ilanapiproject.data.dto.IlanAlisverisSorguDto;
import com.cengiz.ilanapiproject.data.dto.IlanAlisverisTalepDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IlanAlisverisService {


    ResponseEntity<RestResponse<IlanAlisverisDto>> save(IlanAlisverisTalepDto dto);

    List<IlanAlisverisDto> findAllByAktifAlisverisIlanDurum();

    List<IlanAlisverisDto> searchIlanAlisveris(IlanAlisverisSorguDto sorguDto);
}
