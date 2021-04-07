package com.example.demo.cartms.domain.model.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProduct {
    private String productId;
    private String productName;
    private Integer amount;
}
