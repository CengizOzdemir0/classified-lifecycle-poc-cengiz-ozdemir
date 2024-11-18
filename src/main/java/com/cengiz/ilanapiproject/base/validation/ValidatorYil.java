package com.cengiz.ilanapiproject.base.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Cengiz ÖZDEMİR
 * @created 15/11/2024
 */

public class ValidatorYil implements ConstraintValidator<ValidYil, Integer> {

    @Override
    public void initialize(ValidYil constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext context) {
        if (year == null) {
            return true;
        }
        return year >= 1900 && year <= 2100;
    }

}
