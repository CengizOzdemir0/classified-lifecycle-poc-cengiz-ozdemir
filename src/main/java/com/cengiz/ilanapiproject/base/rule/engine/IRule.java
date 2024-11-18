package com.cengiz.ilanapiproject.base.rule.engine;

import java.util.List;

/**
 * @author Cengiz ÖZDEMİR
 * @created 12/11/2024
 */

public interface IRule {

    /**
     * İş kuralın varsayılan ismi
     */
    String DEFAULT_NAME = "rule";
    /**
     * İş kuralın açıklaması
     */
    String DEFAULT_DESCRIPTION = "description";

    /**
     * İş kuralın adını getirir
     *
     * @return iş kuralın adı
     */
    String getName();

    /**
     * İş kuralın açıklmasını getirir
     *
     * @return iş kuralı açıklması
     */
    String getDescription();

    /**
     * İş kuralın çalışmasını yapar
     */
    List<RuleResult> execute();

}
