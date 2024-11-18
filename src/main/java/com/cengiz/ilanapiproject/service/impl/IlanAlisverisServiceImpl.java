package com.cengiz.ilanapiproject.service.impl;

import com.cengiz.ilanapiproject.base.rule.RuleBaslikAciklamaKontrol;
import com.cengiz.ilanapiproject.base.rule.engine.RuleEngine;
import com.cengiz.ilanapiproject.config.domain.ResponseHelper;
import com.cengiz.ilanapiproject.config.domain.RestResponse;
import com.cengiz.ilanapiproject.data.dto.IlanAlisverisDto;
import com.cengiz.ilanapiproject.data.dto.IlanAlisverisSorguDto;
import com.cengiz.ilanapiproject.data.dto.IlanAlisverisTalepDto;
import com.cengiz.ilanapiproject.data.entity.Ilan;
import com.cengiz.ilanapiproject.data.entity.IlanAlisverisDetay;
import com.cengiz.ilanapiproject.data.entity.IlanDurum;
import com.cengiz.ilanapiproject.data.enums.EIlanDurum;
import com.cengiz.ilanapiproject.data.enums.EIlanKategori;
import com.cengiz.ilanapiproject.data.enums.Mesajlar;
import com.cengiz.ilanapiproject.data.mapper.IlanAlisverisDetayMapper;
import com.cengiz.ilanapiproject.exception.IlanException;
import com.cengiz.ilanapiproject.repository.IlanAlisverisDetayRepository;
import com.cengiz.ilanapiproject.repository.IlanRepository;
import com.cengiz.ilanapiproject.service.BadWordsLoader;
import com.cengiz.ilanapiproject.service.IlanAlisverisService;
import com.cengiz.ilanapiproject.service.IlanDurumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * @author Cengiz ÖZDEMİR
 * @created 15/11/2024
 */

@Service
@RequiredArgsConstructor
public class IlanAlisverisServiceImpl implements IlanAlisverisService {

    private final IlanRepository ilanRepository;
    private final BadWordsLoader badWordsLoader;
    private final IlanDurumService ilanDurumService;
    private final IlanAlisverisDetayMapper ilanAlisverisDetayMapper;
    private final IlanAlisverisDetayRepository ilanAlisverisDetayRepository;

    @Transactional
    @Override
    public ResponseEntity<RestResponse<IlanAlisverisDto>> save(IlanAlisverisTalepDto dto) {
        if (badWordsLoader.containsbadWord(dto.getBaslik())) {
            throw new IlanException(Mesajlar.BAD_WORDS);
        }
        boolean koduExists = EIlanKategori.isKoduExists(dto.getFkKategoriId());
        if (!koduExists || EIlanKategori.ALISVERIS.getKodu() != dto.getFkKategoriId()) {
            HashMap<String, String> mesajArgs = new HashMap<>();
            mesajArgs.put(" | Kategori id yanlış: ", " : 3  Alisveris gönderebilirsiniz  ");
            RestResponse<IlanAlisverisDto> restResponse = new RestResponse<>();
            restResponse.addMesajWithArgs(Mesajlar.VLD_GECERSIZ_KEY, mesajArgs);
            return ResponseHelper.responseEntityFromResponse(restResponse);
        }


        RuleEngine ruleEngine = RuleEngine.build()
                .register(new RuleBaslikAciklamaKontrol(dto.getBaslik(), dto.getAciklama()))
                .setStopOnerror(true)
                .execute();
        if (ruleEngine.hasError()) {
            return ResponseHelper.responseEntityDataWithMesaj(ruleEngine.getRestReponseMesaj());
        }
        IlanAlisverisDto ilanAlisverisDto = ilanAlisverisDetayMapper.toDto(dto);
        Ilan savedIlan = ilanSave(ilanAlisverisDto);
        saveIlanDurum(savedIlan);

        IlanAlisverisDetay ilanAlisverisDetay = getIlanAlisverisDetay(ilanAlisverisDto, savedIlan.getId());
        ilanAlisverisDetayRepository.save(ilanAlisverisDetay);
        ilanAlisverisDto.setFkIlanId(savedIlan.getId());
        return ResponseHelper.responseEntityOkFromData(ilanAlisverisDto);
    }

    private Ilan ilanSave(IlanAlisverisDto dto) {
        boolean mukerrerIlan = ilanRepository.existsByBaslikAndAciklamaAndFkKategoriId(dto.getBaslik(), dto.getAciklama(), dto.getFkKategoriId());
        Ilan ilan = new Ilan();
        ilan.setFkKullaniciId(dto.getFkKullaniciId());
        ilan.setFkKategoriId(dto.getFkKategoriId());
        ilan.setBaslik(dto.getBaslik());
        ilan.setAciklama(dto.getAciklama());
        ilan.setFiyat(dto.getFiyat());
        ilan.setIlId(dto.getIlId());
        ilan.setIlceId(dto.getIlceId());
        ilan.setMahalleId(dto.getMahalleId());
        ilan.setKayitTarihi(LocalDateTime.now());
        ilan.setKaydedenKullaniciId(dto.getKaydedenKullaniciId());
        ilan.setIlanDurumId(mukerrerIlan ? EIlanDurum.MUKERRER.getDurum() : EIlanDurum.AKTIF.getDurum());
        ilan.setIlanBitisTarihi(LocalDateTime.now().plusWeeks(EIlanKategori.ALISVERIS.getKategoriYayinhaftasi()));
        return ilanRepository.save(ilan);
    }

    private void saveIlanDurum(Ilan savedIlan) {
        IlanDurum ilanDurum = new IlanDurum();
        ilanDurum.setDurumId(savedIlan.getIlanDurumId());
        ilanDurum.setFkIlanId(savedIlan.getId());
        ilanDurum.setKaydedenKullaniciId(savedIlan.getKaydedenKullaniciId());
        ilanDurum.setKayitTarihi(savedIlan.getKayitTarihi());
        ilanDurumService.save(ilanDurum);
    }

    private static IlanAlisverisDetay getIlanAlisverisDetay(IlanAlisverisDto dto, Long ilanId) {
        IlanAlisverisDetay ilanAlisveris = new IlanAlisverisDetay();
        ilanAlisveris.setFkIlanId(ilanId);
        ilanAlisveris.setDurumu(dto.getDurumu());
        ilanAlisveris.setGaranti(dto.getGaranti());
        ilanAlisveris.setKimden(dto.getKimden());
        ilanAlisveris.setHafiza(dto.getHafiza());
        ilanAlisveris.setModel(dto.getModel());
        ilanAlisveris.setKayitTarihi(dto.getKayitTarihi());
        ilanAlisveris.setKaydedenKullaniciId(dto.getKaydedenKullaniciId());
        return ilanAlisveris;
    }

    @Override
    public List<IlanAlisverisDto> findAllByAktifAlisverisIlanDurum() {
        return ilanAlisverisDetayRepository.findAllByAktifAlisverisIlanDurum();
    }

    @Override
    public List<IlanAlisverisDto> searchIlanAlisveris(IlanAlisverisSorguDto sorguDto) {
        return ilanAlisverisDetayRepository.findByIlanAlisveris(
                sorguDto.getFkKategoriId(),
                sorguDto.getBaslik(),
                sorguDto.getAciklama(),
                sorguDto.getFiyat(),
                sorguDto.getIlId(),
                sorguDto.getIlceId(),
                sorguDto.getMahalleId(),
                sorguDto.getModel(),
                sorguDto.getHafiza(),
                sorguDto.getGaranti(),
                sorguDto.getKimden(),
                sorguDto.getDurumu()
        );
    }
}



