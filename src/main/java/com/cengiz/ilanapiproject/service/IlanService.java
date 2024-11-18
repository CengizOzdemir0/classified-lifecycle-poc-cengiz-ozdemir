package com.cengiz.ilanapiproject.service;

import com.cengiz.ilanapiproject.config.domain.RestResponse;
import com.cengiz.ilanapiproject.data.dto.IlanDto;
import com.cengiz.ilanapiproject.data.dto.IlanOnayDto;
import com.cengiz.ilanapiproject.data.dto.IlanRaporDto;
import com.cengiz.ilanapiproject.data.entity.Ilan;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Cengiz ÖZDEMİR
 * @created 13/11/2024
 */

public interface IlanService {
    ResponseEntity<RestResponse<IlanOnayDto>> onayla(IlanOnayDto dto);

    ResponseEntity<RestResponse<IlanOnayDto>> pasifeAl(IlanOnayDto dto);

    List<IlanRaporDto> getIlanRaporByKategori();

    Ilan findById(Long id);


    IlanDto findByIdWDto(Long id);

    List<IlanDto> findAllByKategoriWithAktif(Integer fkKategori);

    List<IlanDto> findAllByKategoriWithAktifKullanici(Integer fkKategori, Long kullaniciId);
}
