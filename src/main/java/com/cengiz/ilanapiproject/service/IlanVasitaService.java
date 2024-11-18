package com.cengiz.ilanapiproject.service;

import com.cengiz.ilanapiproject.config.domain.RestResponse;
import com.cengiz.ilanapiproject.data.dto.IlanVasitaDto;
import com.cengiz.ilanapiproject.data.dto.IlanVasitaSorguDto;
import com.cengiz.ilanapiproject.data.dto.IlanVasitaTalepDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Cengiz ÖZDEMİR
 * @created 12/11/2024
 */

public interface IlanVasitaService {

    ResponseEntity<RestResponse<IlanVasitaDto>> save(IlanVasitaTalepDto dto);

    List<IlanVasitaDto> findAllByAktifIlanDurum();

    List<IlanVasitaDto> searchIlanVasita(IlanVasitaSorguDto sorguDto);
}
