package com.example.demo.cartms.domain.events;

import java.util.Map;

import com.example.demo.cartms.domain.model.entites.CartProduct;
import com.example.demo.cartms.domain.model.valueobjects.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 購物車已建立事件
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartCreatedEvent {
    private String cartNumber;
    private Customer customer;
    private Integer amount;
    private Map<String, CartProduct> cartProducts;
}
