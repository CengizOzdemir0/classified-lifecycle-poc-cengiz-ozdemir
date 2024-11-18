package com.cengiz.ilanapiproject.service.impl;

import com.cengiz.ilanapiproject.data.dto.MesajDto;
import com.cengiz.ilanapiproject.data.enums.MesajIcerik;
import com.cengiz.ilanapiproject.data.enums.Mesajlar;
import com.cengiz.ilanapiproject.service.MesajService;
import org.springframework.stereotype.Service;

/**
 * @author Cengiz ÖZDEMİR
 * @created 12/11/2024
 */
@Service
public class MesajServiceImpl implements MesajService {

    @Override
    public MesajDto getMesajByMesajEnum(Mesajlar mesajlarEnum) {
        // Normalde mesajları MesajIcerik enum' ından değil mesaj tablosunda olması bu mesajlarıda redis'den almam gerekir
        MesajDto mesajDto = new MesajDto();
        MesajIcerik mesajIcerik = MesajIcerik.valueOf(mesajlarEnum.name());
        mesajDto.setLhttpStatusId(mesajIcerik.getLhttpStatusId());
        mesajDto.setMesaj(mesajIcerik.getMesaj());
        mesajDto.setKodu(mesajIcerik.getKodu());
        mesajDto.setLseviyeTipi(mesajIcerik.getLseviyeTipi());
        mesajDto.setAdi(mesajIcerik.getAdi());
        return mesajDto;
    }
}
