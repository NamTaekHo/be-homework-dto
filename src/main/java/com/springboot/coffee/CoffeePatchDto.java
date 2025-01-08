package com.springboot.coffee;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class CoffeePatchDto {
    private long coffeId;

    @Pattern(regexp = "^(?!\\s*$)[가-힣]+( [가-힣]+)*$")
    private String korName;

//    @Pattern(regexp = "^(?!\\s*$)[a-zA-Z]+( [a-zA-Z]+)*$")
    @EngName
    private String engName;

    @Min(100)
    @Max(50000)
    private Integer price;
}
