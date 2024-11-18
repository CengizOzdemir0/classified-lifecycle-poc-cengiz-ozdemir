package com.cengiz.ilanapiproject.data.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author Cengiz ÖZDEMİR
 * @created 12/11/2024
 */

@Entity
@Table(
        name = "ilan",
        indexes = {
                @Index(name = "idx_fk_kategori_id", columnList = "fk_kategori_id"),
                @Index(name = "idx_fk_kullanici_id", columnList = "fk_kullanici_id")
        }
)
@Getter
@Setter
public class Ilan extends BaseEntity {

    @Column(name = "fk_kullanici_id")
    private Integer fkKullaniciId;

    @Column(name = "fk_kategori_id")
    private Integer fkKategoriId;

    @Column(name = "baslik")
    private String baslik;

    @Column(name = "aciklama")
    private String aciklama;

    @Column(name = "fiyat")
    private Integer fiyat;

    @Column(name = "ilan_bitis_tarihi")
    private LocalDateTime ilanBitisTarihi;

    @Column(name = "ilan_durum_id")
    private Integer ilanDurumId;

    @Column(name = "il_id")
    private Integer ilId;
    @Column(name = "ilce_id")
    private Integer ilceId;
    @Column(name = "mahalle_id")
    private Integer mahalleId;


}
