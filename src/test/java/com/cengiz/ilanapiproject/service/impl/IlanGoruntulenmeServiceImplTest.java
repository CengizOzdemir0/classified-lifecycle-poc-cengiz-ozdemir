package com.cengiz.ilanapiproject.service.impl;

import com.cengiz.ilanapiproject.data.dto.IlanGoruntulenmeDto;
import com.cengiz.ilanapiproject.data.entity.Ilan;
import com.cengiz.ilanapiproject.data.entity.IlanGoruntulenme;
import com.cengiz.ilanapiproject.data.enums.Mesajlar;
import com.cengiz.ilanapiproject.data.mapper.IlanGoruntulenmeMapper;
import com.cengiz.ilanapiproject.exception.IlanException;
import com.cengiz.ilanapiproject.repository.IlanGoruntulenmeRepository;
import com.cengiz.ilanapiproject.service.IlanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IlanGoruntulenmeServiceImplTest {

    @Mock
    private IlanGoruntulenmeRepository ilanGoruntulenmeRepository;

    @Mock
    private IlanGoruntulenmeMapper ilanGoruntulenmeMapper;

    @Mock
    private IlanService ilanService;

    @InjectMocks
    private IlanGoruntulenmeServiceImpl ilanGoruntulenmeService;

    private IlanGoruntulenmeDto ilanGoruntulenmeDto;
    private Ilan ilan;

    @BeforeEach
    void setUp() {
        ilanGoruntulenmeDto = new IlanGoruntulenmeDto();
        ilanGoruntulenmeDto.setFkIlanId(1L);
        ilanGoruntulenmeDto.setGoruntulenmeSayisi(0L);

        ilan = new Ilan();
        ilan.setId(1L);
    }

    @Test
    void kaydet_OncekiKayitVarIse_GoruntulenmeSayisiniArtirir() {
        IlanGoruntulenme ilanGoruntulenme = new IlanGoruntulenme();
        ilanGoruntulenme.setFkIlanId(1L);
        ilanGoruntulenme.setGoruntulenmeSayisi(1L);

        when(ilanService.findById(ilanGoruntulenmeDto.getFkIlanId())).thenReturn(ilan);
        when(ilanGoruntulenmeRepository.findByFkIlanId(ilanGoruntulenmeDto.getFkIlanId())).thenReturn(Optional.of(ilanGoruntulenme));
        when(ilanGoruntulenmeMapper.toDto(any(IlanGoruntulenme.class))).thenReturn(ilanGoruntulenmeDto);
        when(ilanGoruntulenmeRepository.save(any(IlanGoruntulenme.class))).thenReturn(ilanGoruntulenme);

        IlanGoruntulenmeDto sonuc = ilanGoruntulenmeService.save(ilanGoruntulenmeDto);

        assertNotNull(sonuc);
        assertEquals(1L, sonuc.getFkIlanId());
        assertEquals(2L, ilanGoruntulenme.getGoruntulenmeSayisi());
        verify(ilanGoruntulenmeRepository).save(ilanGoruntulenme);
    }

    @Test
    void kaydet_IlanBulunamazsa_ExceptionFirlatir() {
        when(ilanService.findById(ilanGoruntulenmeDto.getFkIlanId())).thenReturn(null);

        IlanException thrown = assertThrows(IlanException.class, () -> {
            ilanGoruntulenmeService.save(ilanGoruntulenmeDto);
        });

        assertEquals(Mesajlar.GNL_KAYIT_BULUNAMADI, thrown.getMesajlarEnum());
        verify(ilanGoruntulenmeRepository, never()).save(any(IlanGoruntulenme.class));
    }

    @Test
    void ilanIdyeGoreBul_Varsa_DtoDondurur() {
        IlanGoruntulenme ilanGoruntulenme = new IlanGoruntulenme();
        ilanGoruntulenme.setFkIlanId(1L);
        ilanGoruntulenme.setGoruntulenmeSayisi(1L);

        when(ilanGoruntulenmeRepository.findByFkIlanId(1L)).thenReturn(Optional.of(ilanGoruntulenme));
        when(ilanGoruntulenmeMapper.toDto(ilanGoruntulenme)).thenReturn(ilanGoruntulenmeDto);

        IlanGoruntulenmeDto sonuc = ilanGoruntulenmeService.findByIlanId(1L);

        assertNotNull(sonuc);
        assertEquals(1L, sonuc.getFkIlanId());
    }

    @Test
    void ilanIdyeGoreBul_KayitYoksa_NullDondurur() {
        when(ilanGoruntulenmeRepository.findByFkIlanId(1L)).thenReturn(Optional.empty());

        IlanGoruntulenmeDto sonuc = ilanGoruntulenmeService.findByIlanId(1L);

        assertNull(sonuc);
    }
}
