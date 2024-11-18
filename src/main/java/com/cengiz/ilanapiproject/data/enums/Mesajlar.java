package com.cengiz.ilanapiproject.data.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Cengiz ÖZDEMİR
 * @created 15/11/2024
 */

@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Mesajlar {
    GNL_KAYIT_BULUNAMADI(HttpStatus.BAD_REQUEST, "GNL1005", MesajSeviye.ERROR),
    GNL_ISLEM_BASARILI(HttpStatus.OK, "GNL1009", MesajSeviye.INFO),
    LGN_ERISIM_ENGELLENDI(HttpStatus.FORBIDDEN, "LGN1006", MesajSeviye.ERROR),
    LGN_CIKIS_BASARISIZ(HttpStatus.BAD_REQUEST, "LGN1008", MesajSeviye.ERROR),
    LGN_CIKIS_BASARILI(HttpStatus.OK, "LGN1005", MesajSeviye.INFO),
    GNL_BEKLENMEYEN_HATA_OLUSTU(HttpStatus.INTERNAL_SERVER_ERROR, "GNL1000", MesajSeviye.ERROR),
    GNL_TC_KIMLIK_NO_GECERSIZ(HttpStatus.PRECONDITION_REQUERED, "VT2006", MesajSeviye.ERROR),
    GNL_RULE_HATALI_CALISTI(HttpStatus.BAD_REQUEST, "GNL1019", MesajSeviye.ERROR),
    GNL_AYNI_KAYIT_MEVCUT(HttpStatus.BAD_REQUEST, "VLD1000", MesajSeviye.ERROR),
    VTN_KAYIT_BULUNAMADI(HttpStatus.BAD_REQUEST, "GNL1025", MesajSeviye.ERROR),
    GNL_GECERSIZ_ISTEK(HttpStatus.BAD_REQUEST, "GNL1026", MesajSeviye.ERROR),
    VLD_NOT_NULL(HttpStatus.BAD_REQUEST, "VLD1005", MesajSeviye.ERROR),
    VLD_GECERSIZ_KEY(HttpStatus.BAD_REQUEST, "VLD1006", MesajSeviye.ERROR),
    BAD_WORDS(HttpStatus.BAD_REQUEST, "BAD1111", MesajSeviye.ERROR),
    BASLIK_GECERSIZ(HttpStatus.BAD_REQUEST, "BAD1112", MesajSeviye.ERROR),
    ACIKLAMA_GECERSIZ(HttpStatus.BAD_REQUEST, "BAD1113", MesajSeviye.ERROR),
    ONAYLAMAYA_UYGUN_DEGIL(HttpStatus.BAD_REQUEST, "BAD1114", MesajSeviye.ERROR),
    ONAYLAMAMAYA_UYGUN_DEGIL(HttpStatus.BAD_REQUEST, "BAD1115", MesajSeviye.ERROR),
    VLD_GECERSIZ_PAYLOAD(HttpStatus.BAD_REQUEST, "VLD1004", MesajSeviye.ERROR);


    private final HttpStatus httpStatus;
    private final String kodu;
    private final MesajSeviye seviye;

    public static Mesajlar of(String kodu) {
        for (Mesajlar inam : values()) {
            if (inam.kodu.equals(kodu) || inam.name().equals(kodu)) {
                return inam;
            }
        }
        return GNL_BEKLENMEYEN_HATA_OLUSTU;
    }

}
