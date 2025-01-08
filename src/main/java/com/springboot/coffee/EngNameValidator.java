package com.springboot.coffee;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EngNameValidator implements ConstraintValidator<EngName, String>{

    private String pattern;

    @Override
    public void initialize(EngName constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.isEmpty()){
            return false;
        }
        return s.matches(pattern);
    }
}
