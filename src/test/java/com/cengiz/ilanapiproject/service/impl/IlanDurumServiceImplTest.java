package com.cengiz.ilanapiproject.service.impl;

import com.cengiz.ilanapiproject.data.dto.IlanDurumDto;
import com.cengiz.ilanapiproject.data.entity.IlanDurum;
import com.cengiz.ilanapiproject.data.mapper.IlanDurumMapper;
import com.cengiz.ilanapiproject.repository.IlanDurumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IlanDurumServiceImplTest {

    @Mock
    private IlanDurumRepository ilanDurumRepository;

    @Mock
    private IlanDurumMapper ilanDurumMapper;

    @InjectMocks
    private IlanDurumServiceImpl ilanDurumService;

    private IlanDurum ilanDurum;
    private IlanDurumDto ilanDurumDto;

    @BeforeEach
    void setUp() {
        ilanDurum = new IlanDurum();
        ilanDurum.setId(1L);
        ilanDurum.setFkIlanId(1L);

        ilanDurumDto = new IlanDurumDto();
        ilanDurumDto.setFkIlanId(1L);
    }

    @Test
    void save_ShouldSaveIlanDurum() {
        when(ilanDurumRepository.save(any(IlanDurum.class))).thenReturn(ilanDurum);

        IlanDurum result = ilanDurumService.save(ilanDurum);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getFkIlanId());
        verify(ilanDurumRepository).save(ilanDurum);
    }

    @Test
    void findAll_ShouldReturnListOfIlanDurumDtos() {
        Long fkIlanId = 1L;
        IlanDurum ilanDurum1 = new IlanDurum();
        ilanDurum1.setId(1L);
        ilanDurum1.setFkIlanId(fkIlanId);

        IlanDurum ilanDurum2 = new IlanDurum();
        ilanDurum2.setId(2L);
        ilanDurum2.setFkIlanId(fkIlanId);

        IlanDurumDto ilanDurumDto1 = new IlanDurumDto();
        ilanDurumDto1.setFkIlanId(fkIlanId);

        IlanDurumDto ilanDurumDto2 = new IlanDurumDto();
        ilanDurumDto2.setFkIlanId(fkIlanId);

        when(ilanDurumRepository.findAllByFkIlanId(fkIlanId)).thenReturn(List.of(ilanDurum1, ilanDurum2));
        when(ilanDurumMapper.toDtoList(any())).thenReturn(List.of(ilanDurumDto1, ilanDurumDto2));

        List<IlanDurumDto> result = ilanDurumService.findAll(fkIlanId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(fkIlanId, result.get(0).getFkIlanId());
        assertEquals(fkIlanId, result.get(1).getFkIlanId());
        verify(ilanDurumRepository).findAllByFkIlanId(fkIlanId);
    }

    @Test
    void findAll_ShouldReturnEmptyListWhenNoRecords() {
        Long fkIlanId = 1L;
        when(ilanDurumRepository.findAllByFkIlanId(fkIlanId)).thenReturn(List.of());

        List<IlanDurumDto> result = ilanDurumService.findAll(fkIlanId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(ilanDurumRepository).findAllByFkIlanId(fkIlanId);
    }
}
