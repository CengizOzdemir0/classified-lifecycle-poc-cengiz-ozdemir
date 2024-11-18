package com.cengiz.ilanapiproject.base.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Cengiz ÖZDEMİR
 * @created 15/11/2024
 */

public class ValidatorDeger implements ConstraintValidator<ValidDeger, Integer> {

    @Override
    public void initialize(ValidDeger constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer deger, ConstraintValidatorContext context) {
        if (deger == null) {
            return true;
        }
        return deger >= 1;
    }
}
