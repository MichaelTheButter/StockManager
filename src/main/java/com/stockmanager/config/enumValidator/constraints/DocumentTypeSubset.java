package com.stockmanager.config.enumValidator.constraints;

import com.stockmanager.config.enumValidator.DocumentTypeSubsetValidator;
import com.stockmanager.domain.document.DocumentType;
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
@Constraint(validatedBy = DocumentTypeSubsetValidator.class)
public @interface DocumentTypeSubset {
    DocumentType[] anyOf();
    String message() default "must be any of {anyOf}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
