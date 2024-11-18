package com.cengiz.ilanapiproject.data.enums;

/**
 * @author Cengiz ÖZDEMİR
 * @created 15/11/2024
 */

public enum MesajIcerik {


    GNL_KAYIT_BULUNAMADI(400, "GNL_KAYIT_BULUNAMADI", "GNL1005", "Kayit bulunamadi", 3),
    GNL_ISLEM_BASARILI(200, "GNL_ISLEM_BASARILI", "BSRLI", "ISLEM BAŞARILI", 1),
    VLD_GECERSIZ_KEY(400, "VLD_GECERSIZ_KEY", "BSRLI", "Hatali veri gönderimi", 1),
    VLD_GECERSIZ_PAYLOAD(400, "VLD_GECERSIZ_PAYLOAD", "BSRLI", "Hatali veri gönderimi", 1),
    GNL_BEKLENMEYEN_HATA_OLUSTU(500, "GNL_BEKLENMEYEN_HATA_OLUSTU", "ERR", "ISLEM BASARISIZ", 3),
    BASLIK_GECERSIZ(400, "BASLIK_GECERSIZ", "ERR", "Başlık min 10 - max 50 karakter olabilir.", 3),
    ACIKLAMA_GECERSIZ(400, "ACIKLAMA_GECERSIZ", "ERR", "Açıklama min 20 - max 200 karakter olabilir.", 3),
    ONAYLAMAYA_UYGUN_DEGIL(400, "ONAYLAMAYA_UYGUN_DEGIL", "ERR", "İlanın durumu onaylamaya uygun değildir.", 3),
    ONAYLAMAMAYA_UYGUN_DEGIL(400, "ONAYLAMAMAYA_UYGUN_DEGIL", "ERR", "İlanın durumu Deaktif için uygun değildir.", 3),
    BAD_WORDS(400, "GNL_BEKLENMEYEN_HATA_OLUSTU", "ERR", "Başlık yasaklı kelimeleri içeriyor.", 3);

    private final Integer lhttpStatusId;
    private final String adi;
    private final String kodu;
    private final String mesaj;
    private final Integer lseviyeTipi;

    MesajIcerik(Integer lhttpStatusId, String adi, String kodu, String mesaj, Integer lseviyeTipi) {
        this.lhttpStatusId = lhttpStatusId;
        this.adi = adi;
        this.kodu = kodu;
        this.mesaj = mesaj;
        this.lseviyeTipi = lseviyeTipi;
    }

    public Integer getLhttpStatusId() {
        return lhttpStatusId;
    }

    public String getAdi() {
        return adi;
    }

    public String getKodu() {
        return kodu;
    }

    public String getMesaj() {
        return mesaj;
    }

    public Integer getLseviyeTipi() {
        return lseviyeTipi;
    }


    public static String getMessageByAdi(String adi) {
        for (MesajIcerik mesajIcerik : MesajIcerik.values()) {
            if (mesajIcerik.getAdi().equalsIgnoreCase(adi)) {
                return mesajIcerik.getMesaj();
            }
        }
        return "Belirtilen adi ile eşleşen mesaj bulunamadı.";
    }
}
