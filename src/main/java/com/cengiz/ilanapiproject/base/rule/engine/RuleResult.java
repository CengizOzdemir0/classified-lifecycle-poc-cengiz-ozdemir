package com.cengiz.ilanapiproject.base.rule.engine;

import com.cengiz.ilanapiproject.data.enums.Mesajlar;
import lombok.Data;

/**
 * @author Cengiz ÖZDEMİR
 * @created 12/11/2024
 */


@Data
public class RuleResult {

    private Mesajlar mesajlar;

    public RuleResult(Mesajlar mesajlar) {
        this.mesajlar = mesajlar;
    }

}
