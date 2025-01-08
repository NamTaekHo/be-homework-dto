package com.springboot.coffee;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class CoffeePostDto {

    @Pattern(regexp = "^(?!\\s*$)[가-힣]+( [가-힣]+)*$")
    @NotNull
    private String korName;

//    @Pattern(regexp = "^(?!\\s*$)[a-zA-Z]+( [a-zA-Z]+)*$")
    @EngName
    @NotNull
    private String engName;

    @Min(100)
    @Max(50000)
    private int price;

}
