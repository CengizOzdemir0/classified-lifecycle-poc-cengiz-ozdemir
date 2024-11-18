package com.cengiz.ilanapiproject.data.enums;

/**
 * @author Cengiz ÖZDEMİR
 * @created 15/11/2024
 */

public enum EIlanDurum {
    AKTIF(1, "Aktif"),
    ONAY_BEKLIYOR(2, "Onay Bekliyor"),
    PASIF(3, "Pasif"),
    REDEDILDI(4, "Red edildi"),
    MUKERRER(5, "Mükerrer");

    private final Integer durum;
    private final String durumStr;


    EIlanDurum(Integer durum, String durumStr) {
        this.durum = durum;
        this.durumStr = durumStr;
    }

    public Integer getDurum() {
        return durum;
    }

    public String getDurumStr() {
        return durumStr;
    }
}
