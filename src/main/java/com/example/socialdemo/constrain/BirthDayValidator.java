package com.example.socialdemo.constrain;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class BirthDayValidator implements ConstraintValidator<BirthDate, LocalDate> {

    @Override
        public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        return LocalDate.now().getYear() - date.getYear()>=18;
    }
}
