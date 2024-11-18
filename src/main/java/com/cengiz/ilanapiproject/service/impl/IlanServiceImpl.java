package com.cengiz.ilanapiproject.service.impl;

import com.cengiz.ilanapiproject.config.domain.ResponseHelper;
import com.cengiz.ilanapiproject.config.domain.RestResponse;
import com.cengiz.ilanapiproject.data.dto.IlanDto;
import com.cengiz.ilanapiproject.data.dto.IlanOnayDto;
import com.cengiz.ilanapiproject.data.dto.IlanRaporDto;
import com.cengiz.ilanapiproject.data.entity.Ilan;
import com.cengiz.ilanapiproject.data.entity.IlanDurum;
import com.cengiz.ilanapiproject.data.enums.EIlanDurum;
import com.cengiz.ilanapiproject.data.enums.EIlanKategori;
import com.cengiz.ilanapiproject.data.enums.Mesajlar;
import com.cengiz.ilanapiproject.data.mapper.IlanMappper;
import com.cengiz.ilanapiproject.exception.IlanException;
import com.cengiz.ilanapiproject.repository.IlanRepository;
import com.cengiz.ilanapiproject.service.IlanDurumService;
import com.cengiz.ilanapiproject.service.IlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Cengiz ÖZDEMİR
 * @created 13/11/2024
 */

@Service
@RequiredArgsConstructor
public class IlanServiceImpl implements IlanService {

    private final IlanRepository ilanRepository;
    private final IlanDurumService ilanDurumService;
    private final IlanMappper ilanMappper;


    @Override
    public ResponseEntity<RestResponse<IlanOnayDto>> onayla(IlanOnayDto dto) {
        Optional<Ilan> ilan = ilanRepository.findById(dto.getIlanId());
        if (!ilan.isPresent()) {
            throw new IlanException(Mesajlar.GNL_KAYIT_BULUNAMADI);
        }
        Ilan ilanRes = ilan.get();
        if (!ilanRes.getIlanDurumId().equals(EIlanDurum.ONAY_BEKLIYOR.getDurum())) {
            throw new IlanException(Mesajlar.ONAYLAMAYA_UYGUN_DEGIL);
        }
        ilanRes.setIlanDurumId(EIlanDurum.AKTIF.getDurum());
        ilanRes.setGuncelleyenKullaniciId(dto.getGuncelleyenKullaniciId());
        ilanRes.setGuncellemeTarihi(LocalDateTime.now());
        ilanRepository.save(ilanRes);
        saveIlanDurum(ilanRes);
        return ResponseHelper.responseEntityOkFromData(dto);

    }

    @Override
    public ResponseEntity<RestResponse<IlanOnayDto>> pasifeAl(IlanOnayDto dto) {
        Optional<Ilan> ilan = ilanRepository.findById(dto.getIlanId());
        if (!ilan.isPresent()) {
            throw new IlanException(Mesajlar.GNL_KAYIT_BULUNAMADI);
        }
        Ilan ilanRes = ilan.get();
        boolean durumKontrol = ilanRes.getIlanDurumId().equals(EIlanDurum.ONAY_BEKLIYOR.getDurum()) || ilanRes.getIlanDurumId().equals(EIlanDurum.AKTIF.getDurum());
        if (!durumKontrol) {
            throw new IlanException(Mesajlar.ONAYLAMAMAYA_UYGUN_DEGIL);
        }
        ilanRes.setIlanDurumId(EIlanDurum.PASIF.getDurum());
        ilanRes.setGuncelleyenKullaniciId(dto.getGuncelleyenKullaniciId());
        ilanRes.setGuncellemeTarihi(LocalDateTime.now());
        ilanRepository.save(ilanRes);
        saveIlanDurum(ilanRes);
        return ResponseHelper.responseEntityOkFromData(dto);

    }

    private void saveIlanDurum(Ilan ilan) {
        IlanDurum ilanDurum = new IlanDurum();
        ilanDurum.setDurumId(ilan.getIlanDurumId());
        ilanDurum.setFkIlanId(ilan.getId());
        ilanDurum.setGuncelleyenKullaniciId(ilan.getGuncelleyenKullaniciId());
        ilanDurum.setGuncellemeTarihi(ilan.getGuncellemeTarihi());
        ilanDurum.setKaydedenKullaniciId(ilan.getKaydedenKullaniciId());
        ilanDurum.setKayitTarihi(ilan.getKayitTarihi());
        ilanDurumService.save(ilanDurum);
    }


    @Override
    public List<IlanRaporDto> getIlanRaporByKategori() {
        return ilanRepository.findIlanRaporByKategori();
    }

    @Override
    public Ilan findById(Long id) {
        return ilanRepository.findById(id).orElse(null);
    }

    @Override
    public IlanDto findByIdWDto(Long id) {
        return ilanMappper.toDto(ilanRepository.findById(id).orElse(null));
    }

    @Override
    public List<IlanDto> findAllByKategoriWithAktif(Integer fkKategori) {
        kategoriExist(fkKategori);
        return ilanMappper.toDtoList(ilanRepository.findByFkKategoriIdAndIlanDurumId(fkKategori, EIlanDurum.AKTIF.getDurum()));
    }

    @Override
    public List<IlanDto> findAllByKategoriWithAktifKullanici(Integer fkKategori, Long kullaniciId) {
        // Normalde auth olsaydi kullanici KullaniciHelper sinifindan alabilirdim.
        kategoriExist(fkKategori);
        return ilanMappper.toDtoList(ilanRepository.findByFkKategoriIdAndIlanDurumIdAndFkKullaniciId(fkKategori, EIlanDurum.AKTIF.getDurum(), kullaniciId));
    }

    private void kategoriExist(Integer fkKategori) {
        if (!EIlanKategori.isKoduExists(fkKategori)) {
            throw new IlanException(Mesajlar.GNL_KAYIT_BULUNAMADI);
        }
    }

}
