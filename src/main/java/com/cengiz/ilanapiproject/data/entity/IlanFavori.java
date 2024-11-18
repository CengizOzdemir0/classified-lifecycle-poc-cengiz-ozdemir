package com.cengiz.ilanapiproject.data.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Cengiz ÖZDEMİR
 * @created 16/11/2024
 */

@Entity
@Table(
        name = "ilan_favori",
        indexes = {
                @Index(name = "idx6_fk_ilan_id", columnList = "fk_ilan_id"),
                @Index(name = "idx6_fk_kullanici_id", columnList = "fk_kullanici_id")
        }
)
@Getter
@Setter
public class IlanFavori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fk_ilan_id", nullable = false)
    private Long fkIlanId;

    @Column(name = "fk_kullanici_id", nullable = false)
    private Long fkKullaniciId;

    @Column(name = "kayit_tarihi", updatable = false)
    private LocalDateTime kayitTarihi;


}
