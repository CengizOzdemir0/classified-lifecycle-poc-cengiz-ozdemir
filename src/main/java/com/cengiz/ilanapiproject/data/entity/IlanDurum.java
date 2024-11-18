package com.cengiz.ilanapiproject.data.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Cengiz ÖZDEMİR
 * @created 13/11/2024
 */

@Entity
@Table(
        name = "ilan_durum",
        indexes = {
                @Index(name = "idx1_fk_ilan_id", columnList = "fk_ilan_id")
        }
)
@Getter
@Setter
public class IlanDurum extends BaseEntity {

    @Column(name = "fk_ilan_id", nullable = false)
    private Long fkIlanId;

    @OneToOne
    @JoinColumn(name = "fk_ilan_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Ilan ilan;

    @Column(name = "durum_id", nullable = false)
    private Integer durumId;


}
