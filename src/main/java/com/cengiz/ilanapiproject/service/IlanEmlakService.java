package com.cengiz.ilanapiproject.service;

import com.cengiz.ilanapiproject.config.domain.RestResponse;
import com.cengiz.ilanapiproject.data.dto.IlanEmlakDto;
import com.cengiz.ilanapiproject.data.dto.IlanEmlakSorguDto;
import com.cengiz.ilanapiproject.data.dto.IlanEmlakTalepDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IlanEmlakService {

    ResponseEntity<RestResponse<IlanEmlakDto>> save(IlanEmlakTalepDto dto);

    List<IlanEmlakDto> findAllByAktifEmlakIlanDurum();

    List<IlanEmlakDto> getFilteredIlanEmlak(IlanEmlakSorguDto sorguDto);
}
