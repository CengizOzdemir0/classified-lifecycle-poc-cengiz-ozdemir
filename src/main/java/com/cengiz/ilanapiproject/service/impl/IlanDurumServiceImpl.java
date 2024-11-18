package com.cengiz.ilanapiproject.service.impl;

import com.cengiz.ilanapiproject.data.dto.IlanDurumDto;
import com.cengiz.ilanapiproject.data.entity.IlanDurum;
import com.cengiz.ilanapiproject.data.mapper.IlanDurumMapper;
import com.cengiz.ilanapiproject.repository.IlanDurumRepository;
import com.cengiz.ilanapiproject.service.IlanDurumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cengiz ÖZDEMİR
 * @created 13/11/2024
 */

@RequiredArgsConstructor
@Service
public class IlanDurumServiceImpl implements IlanDurumService {

    private final IlanDurumRepository ilanDurumRepository;
    private final IlanDurumMapper ilanDurumMapper;

    @Override
    public IlanDurum save(IlanDurum ilanDurum) {
        return ilanDurumRepository.save(ilanDurum);
    }

    @Override
    public List<IlanDurumDto> findAll(Long fkIlanId) {
        return ilanDurumMapper.toDtoList(ilanDurumRepository.findAllByFkIlanId(fkIlanId));
    }


}
