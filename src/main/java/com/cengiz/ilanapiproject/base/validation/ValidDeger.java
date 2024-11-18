package com.cengiz.ilanapiproject.base.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Cengiz ÖZDEMİR
 * @created 15/11/2024
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidatorDeger.class)
public @interface ValidDeger {

    String message() default "Geçerli bir değer giriniz. (0' dan büyük)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
