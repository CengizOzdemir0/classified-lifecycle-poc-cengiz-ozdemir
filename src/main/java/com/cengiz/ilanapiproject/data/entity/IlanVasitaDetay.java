package com.cengiz.ilanapiproject.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


/**
 * @author Cengiz ÖZDEMİR
 * @created 12/11/2024
 */

@Entity
@Table(
        name = "ilan_vasita_detay",
        indexes = {
                @Index(name = "idx4_fk_ilan_id", columnList = "fk_ilan_id")
        }
)
@Getter
@Setter
public class IlanVasitaDetay extends BaseEntity {


    @Column(name = "fk_ilan_id")
    private long fkIlanId;

    @OneToOne
    @JoinColumn(name = "fk_ilan_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Ilan ilan;

    @Column(name = "marka", nullable = false)
    private String marka;

    @Column(name = "seri", nullable = false)
    private String seri;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "yil", nullable = false)
    private int yil;

    @Column(name = "yakit", nullable = false)
    private String yakit;

    @Column(name = "vites", nullable = false)
    private String vites;

    @Column(name = "arac_durumu", nullable = false)
    private String aracDurumu;

    @Column(name = "kilometre", nullable = false)
    private Integer kilometre;

    @Column(name = "kasa_tipi", nullable = false)
    private String kasaTipi;

    @Column(name = "motor_gucu", nullable = false)
    private Integer motorGucu;

    @Column(name = "motor_hacmi", nullable = false)
    private Integer motorHacmi;

    @Column(name = "cekis", nullable = false)
    private String cekis;

    @Column(name = "kapi", nullable = false)
    private Integer kapi;

    @Column(name = "renk", nullable = false)
    private String renk;

    @Column(name = "garanti", nullable = false)
    private Boolean garanti;

    @Column(name = "agir_hasar_kayitli", nullable = false)
    private boolean agirHasarKayitli;

    @Column(name = "plaka_uyruk", nullable = false)
    private String plakaUyruk;

    @Column(name = "kimden", nullable = false)
    private String kimden;

    @Column(name = "takas", nullable = false)
    private Boolean takas;


}
