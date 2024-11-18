package com.cengiz.ilanapiproject.base.rule;

import com.cengiz.ilanapiproject.base.rule.engine.BasicRule;
import com.cengiz.ilanapiproject.base.rule.engine.RuleResult;
import com.cengiz.ilanapiproject.data.enums.Mesajlar;

import java.util.List;

/**
 * @author Cengiz ÖZDEMİR
 * @created 13/11/2024
 */

public class RuleBaslikAciklamaKontrol extends BasicRule {


    private String baslik;
    private String aciklama;


    public RuleBaslikAciklamaKontrol(String baslik, String aciklama) {
        this.baslik = baslik;
        this.aciklama = aciklama;
    }

    @Override
    public List<RuleResult> execute() {
        if (baslik.length() <= 10 || baslik.length() >= 50) {
            ruleResults.add(new RuleResult(Mesajlar.BASLIK_GECERSIZ));
        } else if (aciklama.length() <= 20 || aciklama.length() >= 200) {
            ruleResults.add(new RuleResult(Mesajlar.ACIKLAMA_GECERSIZ));
        }
        return ruleResults;
    }
}
