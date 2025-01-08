package com.springboot.coffee;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = EngNameValidator.class)
public @interface EngName {
    String message() default "영어 이름은 앞 뒤 공백 없이 문자 사이 공백은 한 칸만 가능합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String pattern() default "^(?!\\s*$)[a-zA-Z]+( [a-zA-Z]+)*$";


}
