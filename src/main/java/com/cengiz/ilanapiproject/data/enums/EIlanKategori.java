package com.cengiz.ilanapiproject.data.enums;


/**
 * @author Cengiz ÖZDEMİR
 * @created 15/11/2024
 */

public enum EIlanKategori {


    EMLAK(1, "EMLAK", 4),
    VASITA(2, "VASITA", 3),
    ALISVERIS(3, "ALISVERIS", 8),
    DIGER(4, "DIGER", 8);

    private final int kodu;
    private final String name;
    private final int kategoriYayinhaftasi;

    EIlanKategori(int kodu, String name, int kategoriYayinhaftasi) {
        this.kodu = kodu;
        this.name = name;
        this.kategoriYayinhaftasi = kategoriYayinhaftasi;
    }

    public int getKodu() {
        return kodu;
    }

    public String getName() {
        return name;
    }

    public int getKategoriYayinhaftasi() {
        return kategoriYayinhaftasi;
    }

    public static boolean isKoduExists(int kod) {
        for (EIlanKategori kategori : EIlanKategori.values()) {
            if (kategori.getKodu() == kod) {
                return true;
            }
        }
        return false;
    }


}
