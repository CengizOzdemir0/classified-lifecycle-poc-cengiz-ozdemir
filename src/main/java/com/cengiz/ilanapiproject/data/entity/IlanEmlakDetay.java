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
        name = "ilan_emlak_detay",
        indexes = {
                @Index(name = "idx3_fk_ilan_id", columnList = "fk_ilan_id")
        }
)
@Getter
@Setter
public class IlanEmlakDetay extends BaseEntity {


    @Column(name = "emlak_tipi", nullable = false)
    private String emlakTipi;

    @Column(name = "imar_durumu", nullable = false)
    private String imarDurumu;

    @Column(name = "metrekare", nullable = false)
    private Integer metrekare;

    @Column(name = "metrekare_fiyati", nullable = false)
    private Double metrekareFiyati;

    @Column(name = "ada_no")
    private String adaNo;

    @Column(name = "parsel_no")
    private String parselNo;

    @Column(name = "pafta_no")
    private String paftaNo;

    @Column(name = "emsal")
    private Double emsal;

    @Column(name = "gabari")
    private Double gabari;

    @Column(name = "krediye_uygunluk")
    private String krediyeUygunluk;

    @Column(name = "tapu_durumu")
    private String tapuDurumu;

    @Column(name = "kimden", nullable = false)
    private String kimden;

    @Column(name = "takas", nullable = false)
    private Boolean takas;

    @Column(name = "fk_ilan_id")
    private Long fkIlanId;

    @OneToOne
    @JoinColumn(name = "fk_ilan_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Ilan ilan;

}
