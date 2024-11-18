package com.cengiz.ilanapiproject.service.impl;

import com.cengiz.ilanapiproject.base.rule.RuleBaslikAciklamaKontrol;
import com.cengiz.ilanapiproject.base.rule.engine.RuleEngine;
import com.cengiz.ilanapiproject.config.domain.ResponseHelper;
import com.cengiz.ilanapiproject.config.domain.RestResponse;
import com.cengiz.ilanapiproject.data.dto.IlanVasitaDto;
import com.cengiz.ilanapiproject.data.dto.IlanVasitaSorguDto;
import com.cengiz.ilanapiproject.data.dto.IlanVasitaTalepDto;
import com.cengiz.ilanapiproject.data.entity.Ilan;
import com.cengiz.ilanapiproject.data.entity.IlanDurum;
import com.cengiz.ilanapiproject.data.entity.IlanVasitaDetay;
import com.cengiz.ilanapiproject.data.enums.EIlanDurum;
import com.cengiz.ilanapiproject.data.enums.EIlanKategori;
import com.cengiz.ilanapiproject.data.enums.Mesajlar;
import com.cengiz.ilanapiproject.data.mapper.IlanVasitaMapper;
import com.cengiz.ilanapiproject.exception.IlanException;
import com.cengiz.ilanapiproject.repository.IlanRepository;
import com.cengiz.ilanapiproject.repository.IlanVasitaDetayRepository;
import com.cengiz.ilanapiproject.service.BadWordsLoader;
import com.cengiz.ilanapiproject.service.IlanDurumService;
import com.cengiz.ilanapiproject.service.IlanVasitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * @author Cengiz ÖZDEMİR
 * @created 12/11/2024
 */

@Service
@RequiredArgsConstructor
public class IlanVasitaServiceImpl implements IlanVasitaService {


    private final IlanRepository ilanRepository;
    private final IlanVasitaDetayRepository ilanVasitaDetayRepository;
    private final BadWordsLoader badWordsLoader;
    private final IlanDurumService ilanDurumService;
    private final IlanVasitaMapper ilanVasitaMapper;

    @Transactional
    @Override
    public ResponseEntity<RestResponse<IlanVasitaDto>> save(IlanVasitaTalepDto dto) {
        if (badWordsLoader.containsbadWord(dto.getBaslik())) {
            throw new IlanException(Mesajlar.BAD_WORDS);
        }
        boolean koduExists = EIlanKategori.isKoduExists(dto.getFkKategoriId());
        if (!koduExists || EIlanKategori.VASITA.getKodu() != dto.getFkKategoriId()) {
            HashMap<String, String> mesajArgs = new HashMap<>();
            mesajArgs.put(" | Kategori id yanlış: ", " : 2  Vasıta gönderebilirsiniz  ");
            RestResponse<IlanVasitaDto> restResponse = new RestResponse<>();
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
        IlanVasitaDto ilanVasitaDto = ilanVasitaMapper.toDto(dto);
        Ilan savedIlan = ilanSave(ilanVasitaDto);
        saveIlanDurum(savedIlan);

        IlanVasitaDetay ilanVasitaDetay = getIlanVasitaDetay(ilanVasitaDto, savedIlan.getId());
        ilanVasitaDetayRepository.save(ilanVasitaDetay);
        ilanVasitaDto.setIlanId(savedIlan.getId());
        return ResponseHelper.responseEntityOkFromData(ilanVasitaDto);
    }

    private Ilan ilanSave(IlanVasitaDto dto) {
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
        ilan.setIlanDurumId(mukerrerIlan ? EIlanDurum.MUKERRER.getDurum() : EIlanDurum.ONAY_BEKLIYOR.getDurum());
        ilan.setIlanBitisTarihi(LocalDateTime.now().plusWeeks(EIlanKategori.VASITA.getKategoriYayinhaftasi()));
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

    private static IlanVasitaDetay getIlanVasitaDetay(IlanVasitaDto dto, Long ilanId) {
        IlanVasitaDetay ilanVasitaDetay = new IlanVasitaDetay();
        ilanVasitaDetay.setFkIlanId(ilanId);
        ilanVasitaDetay.setMarka(dto.getMarka());
        ilanVasitaDetay.setSeri(dto.getSeri());
        ilanVasitaDetay.setModel(dto.getModel());
        ilanVasitaDetay.setYil(dto.getYil());
        ilanVasitaDetay.setYakit(dto.getYakit());
        ilanVasitaDetay.setVites(dto.getVites());
        ilanVasitaDetay.setAracDurumu(dto.getAracDurumu());
        ilanVasitaDetay.setKilometre(dto.getKilometre());
        ilanVasitaDetay.setKasaTipi(dto.getKasaTipi());
        ilanVasitaDetay.setMotorGucu(dto.getMotorGucu());
        ilanVasitaDetay.setMotorHacmi(dto.getMotorHacmi());
        ilanVasitaDetay.setCekis(dto.getCekis());
        ilanVasitaDetay.setKapi(dto.getKapi());
        ilanVasitaDetay.setRenk(dto.getRenk());
        ilanVasitaDetay.setGaranti(dto.isGaranti());
        ilanVasitaDetay.setAgirHasarKayitli(dto.isAgirHasarKayitli());
        ilanVasitaDetay.setPlakaUyruk(dto.getPlakaUyruk());
        ilanVasitaDetay.setKimden(dto.getKimden());
        ilanVasitaDetay.setTakas(dto.isTakas());
        ilanVasitaDetay.setKayitTarihi(dto.getKayitTarihi());
        ilanVasitaDetay.setKaydedenKullaniciId(dto.getKaydedenKullaniciId());
        return ilanVasitaDetay;
    }

    @Override
    public List<IlanVasitaDto> findAllByAktifIlanDurum() {
        return ilanVasitaDetayRepository.findAllByAktifIlanDurum();
    }

    @Override
    public List<IlanVasitaDto> searchIlanVasita(IlanVasitaSorguDto sorguDto) {
        return ilanVasitaDetayRepository.findByIlanVasita(
                sorguDto.getBaslik(),
                sorguDto.getAciklama(),
                sorguDto.getFiyat(),
                sorguDto.getIlId(),
                sorguDto.getIlceId(),
                sorguDto.getMahalleId(),
                sorguDto.getMarka(),
                sorguDto.getSeri(),
                sorguDto.getModel(),
                sorguDto.getYil(),
                sorguDto.getYakit(),
                sorguDto.getVites(),
                sorguDto.getAracDurumu(),
                sorguDto.getKilometre(),
                sorguDto.getKasaTipi(),
                sorguDto.getMotorGucu(),
                sorguDto.getMotorHacmi(),
                sorguDto.getCekis(),
                sorguDto.getKapi(),
                sorguDto.getRenk(),
                sorguDto.getGaranti(),
                sorguDto.getAgirHasarKayitli(),
                sorguDto.getPlakaUyruk(),
                sorguDto.getKimden(),
                sorguDto.getTakas()
        );
    }


}
