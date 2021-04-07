package com.example.demo.cartms.domain.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCartProductCommand {
    @TargetAggregateIdentifier
    private String cartNumber;
    private String productId;
    private String productName;
    private Integer amount;
}
