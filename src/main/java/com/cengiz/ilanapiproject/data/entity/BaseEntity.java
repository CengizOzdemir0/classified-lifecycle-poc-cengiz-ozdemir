package com.cengiz.ilanapiproject.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Cengiz ÖZDEMİR
 * @created 12/11/2024
 */

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "kayit_tarihi", updatable = false)
    private LocalDateTime kayitTarihi;

    @Column(name = "kaydeden_kullanici_id")
    private Long kaydedenKullaniciId;

    @Column(name = "guncelleme_tarihi")
    private LocalDateTime guncellemeTarihi;

    @Column(name = "guncelleyen_kullanici_id")
    private Long guncelleyenKullaniciId;

}
