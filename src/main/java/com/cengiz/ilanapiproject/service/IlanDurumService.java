package com.cengiz.ilanapiproject.service;

import com.cengiz.ilanapiproject.data.dto.IlanDurumDto;
import com.cengiz.ilanapiproject.data.entity.IlanDurum;

import java.util.List;

public interface IlanDurumService {


    IlanDurum save(IlanDurum ilanDurum);

    List<IlanDurumDto> findAll(Long fkIlanId);
}
