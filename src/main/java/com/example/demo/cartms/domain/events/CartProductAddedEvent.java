package com.example.demo.cartms.domain.events;

import java.util.Map;

import com.example.demo.cartms.domain.model.entites.CartProduct;
import com.example.demo.cartms.domain.model.valueobjects.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 購物車商品已新增事件
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductAddedEvent {
    private String cartNumber;
    private Customer customer;
    private Integer amount;

    private String productId;
    private String productName;
    private Integer productAmount;
}
