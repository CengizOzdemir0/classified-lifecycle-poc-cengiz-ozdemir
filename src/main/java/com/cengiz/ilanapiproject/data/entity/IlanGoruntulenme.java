package com.cengiz.ilanapiproject.data.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Cengiz ÖZDEMİR
 * @created 14/11/2024
 */

@Entity
@Table(
        name = "ilan_goruntulenme",
        indexes = {
                @Index(name = "idx5_fk_ilan_id", columnList = "fk_ilan_id")
        }
)
@Getter
@Setter
public class IlanGoruntulenme {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fk_ilan_id")
    private Long fkIlanId;

    @Column(name = "goruntulenme_sayisi")
    private Long goruntulenmeSayisi;

}
