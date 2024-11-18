package com.cengiz.ilanapiproject.service;

import com.cengiz.ilanapiproject.data.dto.IlanGoruntulenmeDto;

public interface IlanGoruntulenmeService {

    IlanGoruntulenmeDto save(IlanGoruntulenmeDto dto);

    IlanGoruntulenmeDto findByIlanId(Long fkIlanId);
}
