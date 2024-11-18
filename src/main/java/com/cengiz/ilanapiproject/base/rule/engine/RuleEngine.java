package com.cengiz.ilanapiproject.base.rule.engine;

import com.cengiz.ilanapiproject.data.enums.Mesajlar;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Cengiz ÖZDEMİR
 * @created 12/11/2024
 */

@Slf4j
public class RuleEngine {

    private List<RuleResult> ruleResults = new ArrayList<>();

    private boolean stopOnerror = true;

    private List<IRule> rules = new ArrayList<>();

    public RuleEngine(List<IRule> rules) {
        this.rules = rules;
    }

    public RuleEngine(IRule... rules) {
        Collections.addAll(this.rules, rules);
    }

    public static RuleEngine build() {
        return new RuleEngine();
    }

    public static RuleEngine build(IRule... rules) {
        return new RuleEngine(rules);
    }

    public static RuleEngine build(List<IRule> rules) {
        return new RuleEngine(rules);
    }


    public RuleEngine register(IRule rule) {
        Objects.requireNonNull(rule);
        this.rules.add(rule);
        return this;
    }


    public RuleEngine execute() {
        this.ruleResults = new ArrayList<>();
        if (rules.isEmpty()) {
            log.warn("Kural listesi boş");
            return this;
        }
        log(rules);
        log.debug("Kurallar çalıştırlmaya başlandı");
        for (IRule rule : rules) {
            final String name = (rule.getName().equals(IRule.DEFAULT_NAME) ? rule.getClass().getSimpleName() : rule.getName());
            log.debug("Kural : '{}' çalıştırılıyor", name);
            try {
                List<RuleResult> ruleExecuteResults = rule.execute();
                log.debug("Kural '{}' başarılı çalıştı", name);
                if (!ruleExecuteResults.isEmpty()) {
                    this.ruleResults.addAll(ruleExecuteResults);
                    if (stopOnerror) {
                        return this;
                    }
                }
            } catch (Exception ex) {
                log.error("Kural '" + name + "' hatalı çalştı", ex);
                this.ruleResults.add(new RuleResult(Mesajlar.GNL_RULE_HATALI_CALISTI));
            }
        }
        return this;
    }

    private void log(List<IRule> rules) {
        log.debug("Kayıtlı kurallar::");
        for (IRule rule : rules) {
            log.debug("Kural { name = '{}', description = '{}'}", rule.getName(), rule.getDescription());
        }
    }

    public RuleEngine setStopOnerror(boolean stopOnerror) {
        this.stopOnerror = stopOnerror;
        return this;
    }

    public boolean hasError() {
        return (!this.ruleResults.isEmpty());
    }


    public Mesajlar getRestReponseMesaj() {
        for (RuleResult ruleResult : this.ruleResults) {
            if (ruleResult.getMesajlar() != null) {
                return ruleResult.getMesajlar();
            }
        }
        return null;
    }


}
