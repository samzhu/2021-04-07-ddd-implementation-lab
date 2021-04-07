package com.example.demo.cartms.interfaces.transform;

import com.example.demo.cartms.domain.commands.AddCartProductCommand;
import com.example.demo.cartms.domain.commands.CreateCartCommand;
import com.example.demo.cartms.domain.model.valueobjects.Customer;
import com.example.demo.cartms.interfaces.rest.dto.AddCartProductDto;
import com.example.demo.cartms.interfaces.rest.dto.CreateCartDto;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Assembler class to convert the Cart Resource Data to the Cart Model
 */
public class CartAssembler {

    public static CreateCartCommand toCreateCartCommand(CreateCartDto createDto) {
        return new CreateCartCommand(RandomStringUtils.randomNumeric(6), new Customer(createDto.getCustomerName()), 0);
    }

    public static AddCartProductCommand toAddCartProductCommand(String cartNumber, AddCartProductDto addCartProductDto) {
        // 假設這邊有去後端查詢商品價格為 100
        return new AddCartProductCommand(cartNumber, addCartProductDto.getProductId(), addCartProductDto.getProductName(), 100);
    }
}
