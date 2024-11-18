package com.cengiz.ilanapiproject.base.rule.engine;


import com.cengiz.ilanapiproject.data.enums.Mesajlar;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cengiz ÖZDEMİR
 * @created 12/11/2024
 */

public class BasicRule implements IRule {

    protected String name;
    protected String description;

    public List<RuleResult> ruleResults = new ArrayList<>();

    public BasicRule() {
        this(IRule.DEFAULT_NAME, IRule.DEFAULT_DESCRIPTION);
    }

    public BasicRule(final String name) {
        this(name, IRule.DEFAULT_DESCRIPTION);
    }

    public BasicRule(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public void addMesajWithArgsToResult(Mesajlar mesajlar) {
        this.ruleResults.add(new RuleResult(mesajlar));
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public List<RuleResult> execute() {
        return null;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BasicRule basicRule = (BasicRule) o;
        if (!name.equals(basicRule.name)) {
            return false;
        }
        return !(description != null ? !description.equals(basicRule.description) : basicRule.description != null);

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "name:" + this.name + " description:" + description;
    }
}
