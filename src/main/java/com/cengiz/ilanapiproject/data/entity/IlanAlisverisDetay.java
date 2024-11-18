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
        name = "ilan_alisveris_detay",
        indexes = {
                @Index(name = "idx2_fk_ilan_id", columnList = "fk_ilan_id")
        }
)
@Getter
@Setter
public class IlanAlisverisDetay extends BaseEntity {

    @Column(name = "fk_ilan_id")
    private Long fkIlanId;

    @OneToOne
    @JoinColumn(name = "fk_ilan_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Ilan ilan;


    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "hafiza", nullable = false)
    private String hafiza;

    @Column(name = "garanti")
    private String garanti;

    @Column(name = "kimden", nullable = false)
    private String kimden;

    @Column(name = "durumu", nullable = false)
    private String durumu;

}
