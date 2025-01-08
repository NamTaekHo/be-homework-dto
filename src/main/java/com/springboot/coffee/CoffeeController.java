package com.springboot.coffee;

import com.springboot.member.MemberPostDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/coffees")
@Validated
public class CoffeeController {

    private final Map<Long, Map<String, Object>> coffees = new HashMap<>();

    @PostConstruct
    public void init() {
        Map<String, Object> coffee1 = new HashMap<>();
        long coffeeId = 1L;
        coffee1.put("coffeeId", coffeeId);
        coffee1.put("korName", "바닐라 라떼");
        coffee1.put("engName", "Vanilla Latte");
        coffee1.put("price", 4500);

        coffees.put(coffeeId, coffee1);
    }

    // 임시 서비스로직 구현
    public CoffeePatchDto coffeePatchService(long coffeeId, CoffeePatchDto coffeePatchDto){
        Map<String, Object> originData = coffees.get(coffeeId);
        if(originData == null){
            return null;
        }
        CoffeePatchDto dto = coffeePatchDto;

        Map<String, Object> patchData = new HashMap<>();
        patchData.put("coffeeId", coffeeId);
        patchData.put("korName", dto.getKorName());
        patchData.put("engName", dto.getEngName());
        patchData.put("price", dto.getPrice() == null ? originData.get("price") : dto.getPrice());

        patchData.forEach((key, value) -> {
            if(value != null){
                originData.put(key, value);
            }
        });

        CoffeePatchDto patchedDto = new CoffeePatchDto();
        patchedDto.setCoffeId(coffeeId);
        patchedDto.setKorName(String.valueOf(originData.get("korName")));
        patchedDto.setEngName(String.valueOf(originData.get("engName")));
        patchedDto.setPrice(((Integer) originData.get("price")));

        return patchedDto;
    }

    // 1. DTO 클래스 및 유효성 검증을 적용하세요.
    @PostMapping
    public ResponseEntity postCoffee(@RequestBody @Valid CoffeePostDto coffeePostDto) {
        return new ResponseEntity<>(coffeePostDto, HttpStatus.CREATED);
    }

    // 2. DTO 클래스 및 유효성 검증을 적용하세요.
    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(@PathVariable("coffee-id") @Valid @Min(1) long coffeeId,
                                      @RequestBody @Valid CoffeePatchDto coffeePatchDto) {
        coffeePatchDto.setCoffeId(coffeeId);
        return new ResponseEntity<>(coffeePatchService(coffeeId, coffeePatchDto), HttpStatus.OK);
    }

    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id") long coffeeId) {
        System.out.println("# coffeeId: " + coffeeId);

        // not implementation

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCoffees() {
        System.out.println("# get Coffees");

        // not implementation

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") @Min(1) long coffeeId) {
        // No need business logic

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
