package com.cengiz.ilanapiproject.service.impl;


import com.cengiz.ilanapiproject.data.dto.IlanFavoriDto;
import com.cengiz.ilanapiproject.data.mapper.IlanFavoriMapper;
import com.cengiz.ilanapiproject.repository.IlanFavoriRepository;
import com.cengiz.ilanapiproject.service.IlanFavoriService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Cengiz ÖZDEMİR
 * @created 16/11/2024
 */

@Service
@RequiredArgsConstructor
public class IlanFavoriServiceImpl implements IlanFavoriService {

    private final IlanFavoriRepository ilanFavoriRepository;
    private final IlanFavoriMapper ilanFavoriMapper;


    @Override
    public IlanFavoriDto save(IlanFavoriDto dto) {
        dto.setKayitTarihi(LocalDateTime.now());
        return ilanFavoriMapper.toDto(ilanFavoriRepository.save(ilanFavoriMapper.toEntity(dto)));
    }

    @Override
    public List<IlanFavoriDto> findAllByIlanId(Long ilanId) {
        return ilanFavoriMapper.toDtoList(ilanFavoriRepository.findByFkIlanId(ilanId));
    }

    @Override
    public List<IlanFavoriDto> findAllByKullaniciId(Long ilanId) {
        return ilanFavoriMapper.toDtoList(ilanFavoriRepository.findByFkKullaniciId(ilanId));
    }


}
