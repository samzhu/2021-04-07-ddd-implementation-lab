package com.example.demo.cartms.interfaces.rest.dto;

import java.util.Map;

import com.example.demo.cartms.domain.model.entites.CartProduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartInfoDto {
    private String cartNumber;
    private String customerName;
    private Integer amount;
    private Map<String, CartProduct> cartProducts;
}
