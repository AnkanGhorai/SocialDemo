package com.example.socialdemo.constrain;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Constraint(validatedBy = BirthDayValidator.class)
@Target({TYPE,FIELD,ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BirthDate {
    String message() default "{com.example.socialdemo.constrain.BirthDate.message}";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};
}
