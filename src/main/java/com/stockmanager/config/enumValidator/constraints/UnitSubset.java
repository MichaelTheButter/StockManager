package com.stockmanager.config.enumValidator.constraints;

import com.stockmanager.config.enumValidator.UnitSubSetValidator;
import com.stockmanager.domain.product.unit.Unit;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UnitSubSetValidator.class)
public @interface UnitSubset {
    Unit[] anyOf();
    String message() default "must be any of {anyOf}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
