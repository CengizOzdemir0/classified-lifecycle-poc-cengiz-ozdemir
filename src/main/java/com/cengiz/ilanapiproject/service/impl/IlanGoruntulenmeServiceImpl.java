package com.cengiz.ilanapiproject.service.impl;


import com.cengiz.ilanapiproject.data.dto.IlanGoruntulenmeDto;
import com.cengiz.ilanapiproject.data.entity.Ilan;
import com.cengiz.ilanapiproject.data.entity.IlanGoruntulenme;
import com.cengiz.ilanapiproject.data.enums.Mesajlar;
import com.cengiz.ilanapiproject.data.mapper.IlanGoruntulenmeMapper;
import com.cengiz.ilanapiproject.exception.IlanException;
import com.cengiz.ilanapiproject.repository.IlanGoruntulenmeRepository;
import com.cengiz.ilanapiproject.service.IlanGoruntulenmeService;
import com.cengiz.ilanapiproject.service.IlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Cengiz ÖZDEMİR
 * @created 14/11/2024
 */

@Service
@RequiredArgsConstructor
public class IlanGoruntulenmeServiceImpl implements IlanGoruntulenmeService {

    private final IlanGoruntulenmeRepository ilanGoruntulenmeRepository;
    private final IlanGoruntulenmeMapper ilanGoruntulenmeMapper;
    private final IlanService ilanService;

    @Override
    public IlanGoruntulenmeDto save(IlanGoruntulenmeDto dto) {
        Ilan ilan = ilanService.findById(dto.getFkIlanId());
        if (ilan == null) {
            throw new IlanException(Mesajlar.GNL_KAYIT_BULUNAMADI);
        }
        Optional<IlanGoruntulenme> byFkIlanId = ilanGoruntulenmeRepository.findByFkIlanId(dto.getFkIlanId());
        if (byFkIlanId.isPresent()) {
            byFkIlanId.get().setGoruntulenmeSayisi(byFkIlanId.get().getGoruntulenmeSayisi() + 1L);
            return ilanGoruntulenmeMapper.toDto(ilanGoruntulenmeRepository.save(byFkIlanId.get()));
        }
        dto.setGoruntulenmeSayisi(1L);
        return ilanGoruntulenmeMapper.toDto(ilanGoruntulenmeRepository.save(ilanGoruntulenmeMapper.toEntity(dto)));
    }

    @Override
    public IlanGoruntulenmeDto findByIlanId(Long fkIlanId) {
        return ilanGoruntulenmeMapper.toDto(ilanGoruntulenmeRepository.findByFkIlanId(fkIlanId).orElse(null));
    }
}
