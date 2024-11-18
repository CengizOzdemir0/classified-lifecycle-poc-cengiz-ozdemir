package com.cengiz.ilanapiproject.service.impl;


import com.cengiz.ilanapiproject.config.domain.BeanUtil;
import com.cengiz.ilanapiproject.config.domain.ResponseHelper;
import com.cengiz.ilanapiproject.config.domain.RestResponse;
import com.cengiz.ilanapiproject.data.dto.IlanOnayDto;
import com.cengiz.ilanapiproject.data.entity.Ilan;
import com.cengiz.ilanapiproject.data.entity.IlanDurum;
import com.cengiz.ilanapiproject.data.enums.EIlanDurum;
import com.cengiz.ilanapiproject.data.enums.Mesajlar;
import com.cengiz.ilanapiproject.exception.IlanException;
import com.cengiz.ilanapiproject.repository.IlanRepository;
import com.cengiz.ilanapiproject.service.IlanDurumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class IlanServiceImplTest {

    @Mock
    private IlanRepository ilanRepository;

    @Mock
    private IlanDurumService ilanDurumService;

    @Mock
    private ApplicationContext applicationContext;

    @InjectMocks
    private IlanServiceImpl ilanService;

    private Ilan ilan;
    private IlanOnayDto ilanOnayDto;

    @BeforeEach
    void setUp() {
        ilan = new Ilan();
        ilan.setId(1L);
        ilan.setIlanDurumId(EIlanDurum.ONAY_BEKLIYOR.getDurum());
        ilan.setGuncelleyenKullaniciId(1L);
        ilan.setGuncellemeTarihi(LocalDateTime.now());

        ilanOnayDto = new IlanOnayDto();
        ilanOnayDto.setIlanId(1L);
        ilanOnayDto.setGuncelleyenKullaniciId(1L);

        BeanUtil.setApplicationContext(applicationContext);
    }

    @Test
    void onayla_IlanBulunamazsa_ExceptionFirlatir() {
        when(ilanRepository.findById(ilanOnayDto.getIlanId())).thenReturn(Optional.empty());

        IlanException thrown = assertThrows(IlanException.class, () -> {
            ilanService.onayla(ilanOnayDto);
        });

        assertEquals(Mesajlar.GNL_KAYIT_BULUNAMADI, thrown.getMesajlarEnum());
    }

    @Test
    void onayla_IlanDurumUygunDegilse_ExceptionFirlatir() {
        ilan.setIlanDurumId(EIlanDurum.PASIF.getDurum());
        when(ilanRepository.findById(ilanOnayDto.getIlanId())).thenReturn(Optional.of(ilan));

        IlanException thrown = assertThrows(IlanException.class, () -> {
            ilanService.onayla(ilanOnayDto);
        });

        assertEquals(Mesajlar.ONAYLAMAYA_UYGUN_DEGIL, thrown.getMesajlarEnum());
    }

    @Test
    void onayla_DurumGuncellendiginde_BasariliDondurur() {

        ilan.setIlanDurumId(EIlanDurum.ONAY_BEKLIYOR.getDurum());
        when(ilanRepository.findById(ilanOnayDto.getIlanId())).thenReturn(Optional.of(ilan));
        when(ilanRepository.save(any(Ilan.class))).thenReturn(ilan); // Mock the static method responseEntityOkFromData
        RestResponse<IlanOnayDto> restResponse = new RestResponse<>();
        ResponseEntity<RestResponse<IlanOnayDto>> mockResponseEntity = ResponseEntity.ok(restResponse);
        try (var mockedStatic = mockStatic(ResponseHelper.class)) {
            mockedStatic.when(() -> ResponseHelper.responseEntityOkFromData(any(IlanOnayDto.class))).thenReturn(mockResponseEntity);
            ResponseEntity<RestResponse<IlanOnayDto>> response = ilanService.onayla(ilanOnayDto);
            assertNotNull(response);
            assertEquals(mockResponseEntity.getBody(), response.getBody());
            assertEquals(EIlanDurum.AKTIF.getDurum(), ilan.getIlanDurumId());
            verify(ilanRepository, times(1)).save(any(Ilan.class));
            verify(ilanDurumService, times(1)).save(any(IlanDurum.class));
        }
    }

    @Test
    void pasifeAl_IlanBulunamazsa_ExceptionFirlatir() {
        when(ilanRepository.findById(ilanOnayDto.getIlanId())).thenReturn(Optional.empty());

        IlanException thrown = assertThrows(IlanException.class, () -> {
            ilanService.pasifeAl(ilanOnayDto);
        });

        assertEquals(Mesajlar.GNL_KAYIT_BULUNAMADI, thrown.getMesajlarEnum());
    }

    @Test
    void pasifeAl_DurumUygunDegilse_ExceptionFirlatir() {
        ilan.setIlanDurumId(EIlanDurum.PASIF.getDurum());
        when(ilanRepository.findById(ilanOnayDto.getIlanId())).thenReturn(Optional.of(ilan));

        IlanException thrown = assertThrows(IlanException.class, () -> {
            ilanService.pasifeAl(ilanOnayDto);
        });

        assertEquals(Mesajlar.ONAYLAMAMAYA_UYGUN_DEGIL, thrown.getMesajlarEnum());
    }

    @Test
    void pasifeAl_DurumGuncellendiginde_BasariliDondurur() {
        when(ilanRepository.findById(ilanOnayDto.getIlanId())).thenReturn(Optional.of(ilan));
        ilan.setIlanDurumId(EIlanDurum.AKTIF.getDurum());
        when(ilanRepository.save(any(Ilan.class))).thenReturn(ilan);
        RestResponse<IlanOnayDto> restResponse = new RestResponse<>();
        ResponseEntity<RestResponse<IlanOnayDto>> mockResponseEntity = ResponseEntity.ok(restResponse);
        try (var mockedStatic = mockStatic(ResponseHelper.class)) {
            mockedStatic.when(() -> ResponseHelper.responseEntityOkFromData(any(IlanOnayDto.class))).thenReturn(mockResponseEntity);
            ResponseEntity<RestResponse<IlanOnayDto>> response = ilanService.pasifeAl(ilanOnayDto);
            assertNotNull(response);
            assertEquals(mockResponseEntity.getBody(), response.getBody());
            assertEquals(EIlanDurum.PASIF.getDurum(), ilan.getIlanDurumId());
            verify(ilanRepository, times(1)).save(any(Ilan.class));
            verify(ilanDurumService, times(1)).save(any(IlanDurum.class));
        }
    }
}
