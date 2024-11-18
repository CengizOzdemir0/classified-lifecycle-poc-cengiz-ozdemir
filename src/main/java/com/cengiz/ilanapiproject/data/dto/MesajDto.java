package com.cengiz.ilanapiproject.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


/**
 * @author Cengiz ÖZDEMİR
 * @created 12/11/2024
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MesajDto {


    private Long id;
    private Integer lhttpStatusId;
    private String adi;
    private String kodu;
    private String mesaj;
    private Integer lseviyeTipi;
    private Boolean aktif;
    private LocalDateTime kayitZamani;
    private Long kaydedenFkKullaniciRolId;

}
