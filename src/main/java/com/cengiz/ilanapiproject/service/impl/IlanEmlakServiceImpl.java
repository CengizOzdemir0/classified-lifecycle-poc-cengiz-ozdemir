package com.cengiz.ilanapiproject.service.impl;

import com.cengiz.ilanapiproject.base.rule.RuleBaslikAciklamaKontrol;
import com.cengiz.ilanapiproject.base.rule.engine.RuleEngine;
import com.cengiz.ilanapiproject.config.domain.ResponseHelper;
import com.cengiz.ilanapiproject.config.domain.RestResponse;
import com.cengiz.ilanapiproject.data.dto.IlanEmlakDto;
import com.cengiz.ilanapiproject.data.dto.IlanEmlakSorguDto;
import com.cengiz.ilanapiproject.data.dto.IlanEmlakTalepDto;
import com.cengiz.ilanapiproject.data.entity.Ilan;
import com.cengiz.ilanapiproject.data.entity.IlanDurum;
import com.cengiz.ilanapiproject.data.entity.IlanEmlakDetay;
import com.cengiz.ilanapiproject.data.enums.EIlanDurum;
import com.cengiz.ilanapiproject.data.enums.EIlanKategori;
import com.cengiz.ilanapiproject.data.enums.Mesajlar;
import com.cengiz.ilanapiproject.data.mapper.IlanEmlakDetayMapper;
import com.cengiz.ilanapiproject.exception.IlanException;
import com.cengiz.ilanapiproject.repository.IlanEmlakDetayRepository;
import com.cengiz.ilanapiproject.repository.IlanRepository;
import com.cengiz.ilanapiproject.service.BadWordsLoader;
import com.cengiz.ilanapiproject.service.IlanDurumService;
import com.cengiz.ilanapiproject.service.IlanEmlakService;
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
public class IlanEmlakServiceImpl implements IlanEmlakService {
    private final IlanRepository ilanRepository;
    private final BadWordsLoader badWordsLoader;
    private final IlanDurumService ilanDurumService;
    private final IlanEmlakDetayRepository ilanEmlakDetayRepository;
    private final IlanEmlakDetayMapper ilanEmlakDetayMapper;


    @Transactional
    @Override
    public ResponseEntity<RestResponse<IlanEmlakDto>> save(IlanEmlakTalepDto dto) {
        if (badWordsLoader.containsbadWord(dto.getBaslik())) {
            throw new IlanException(Mesajlar.BAD_WORDS);
        }
        boolean koduExists = EIlanKategori.isKoduExists(dto.getFkKategoriId());
        if (!koduExists || EIlanKategori.EMLAK.getKodu() != dto.getFkKategoriId()) {
            HashMap<String, String> mesajArgs = new HashMap<>();
            mesajArgs.put(" | Kategori id yanlış: ", " : 1  Emlak gönderebilirsiniz  ");
            RestResponse<IlanEmlakDto> restResponse = new RestResponse<>();
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
        IlanEmlakDto ilanEmlakDto = ilanEmlakDetayMapper.toDto(dto);
        Ilan savedIlan = ilanSave(ilanEmlakDto);
        saveIlanDurum(savedIlan);

        IlanEmlakDetay ilanEmlakDetay = getIlanEmlakDetay(ilanEmlakDto, savedIlan.getId());
        ilanEmlakDetayRepository.save(ilanEmlakDetay);
        ilanEmlakDto.setIlanId(savedIlan.getId());
        return ResponseHelper.responseEntityOkFromData(ilanEmlakDto);
    }

    private Ilan ilanSave(IlanEmlakDto dto) {
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
        ilan.setIlanBitisTarihi(LocalDateTime.now().plusWeeks(EIlanKategori.EMLAK.getKategoriYayinhaftasi()));
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

    private static IlanEmlakDetay getIlanEmlakDetay(IlanEmlakDto dto, Long ilanId) {
        IlanEmlakDetay ilanEmlakDetay = new IlanEmlakDetay();
        ilanEmlakDetay.setFkIlanId(ilanId);
        ilanEmlakDetay.setEmlakTipi(dto.getEmlakTipi());
        ilanEmlakDetay.setKimden(dto.getKimden());
        ilanEmlakDetay.setGabari(dto.getGabari());
        ilanEmlakDetay.setTakas(dto.isTakas());
        ilanEmlakDetay.setEmsal(dto.getEmsal());
        ilanEmlakDetay.setImarDurumu(dto.getImarDurumu());
        ilanEmlakDetay.setAdaNo(dto.getAdaNo());
        ilanEmlakDetay.setKrediyeUygunluk(dto.getKrediyeUygunluk());
        ilanEmlakDetay.setMetrekareFiyati(dto.getMetrekareFiyati());
        ilanEmlakDetay.setTapuDurumu(dto.getTapuDurumu());
        ilanEmlakDetay.setMetrekare(dto.getMetrekare());
        ilanEmlakDetay.setParselNo(dto.getParselNo());
        ilanEmlakDetay.setPaftaNo(dto.getPaftaNo());
        ilanEmlakDetay.setKayitTarihi(dto.getKayitTarihi());
        ilanEmlakDetay.setKaydedenKullaniciId(dto.getKaydedenKullaniciId());
        return ilanEmlakDetay;
    }

    @Override
    public List<IlanEmlakDto> findAllByAktifEmlakIlanDurum() {
        return ilanEmlakDetayRepository.findAllByAktifEmlakIlanDurum();
    }

    @Override
    public List<IlanEmlakDto> getFilteredIlanEmlak(IlanEmlakSorguDto sorguDto) {
        return ilanEmlakDetayRepository.findByIlanEmlak(
                sorguDto.getBaslik(),
                sorguDto.getAciklama(),
                sorguDto.getFiyat(),
                sorguDto.getIlId(),
                sorguDto.getIlceId(),
                sorguDto.getMahalleId(),
                sorguDto.getEmlakTipi(),
                sorguDto.getImarDurumu(),
                sorguDto.getMetrekare(),
                sorguDto.getMetrekareFiyati(),
                sorguDto.getAdaNo(),
                sorguDto.getParselNo(),
                sorguDto.getPaftaNo(),
                sorguDto.getEmsal(),
                sorguDto.getGabari(),
                sorguDto.getKrediyeUygunluk(),
                sorguDto.getTapuDurumu(),
                sorguDto.getKimden(),
                sorguDto.getTakas()
        );
    }


}
