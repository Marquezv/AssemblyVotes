package com.vmarquezv.dev.assemblyVotes.commons.status.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.vmarquezv.dev.assemblyVotes.commons.CpfValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = CpfValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Cpf {
	
	  String message() default "Invalid Document";
	  Class<?>[] groups() default {};
	  Class<? extends Payload>[] payload() default {};
}
