package com.cengiz.ilanapiproject.service;

import com.cengiz.ilanapiproject.data.dto.IlanFavoriDto;

import java.util.List;

/**
 * @author Cengiz ÖZDEMİR
 * @created 16/11/2024
 */

public interface IlanFavoriService {

    IlanFavoriDto save(IlanFavoriDto dto);

    List<IlanFavoriDto> findAllByIlanId(Long ilanId);

    List<IlanFavoriDto> findAllByKullaniciId(Long ilanId);
}
