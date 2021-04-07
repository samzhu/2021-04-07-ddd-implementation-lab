package com.example.demo.cartms.domain.commands;

import com.example.demo.cartms.domain.model.valueobjects.Customer;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 建立購物車命令
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCartCommand {
    @TargetAggregateIdentifier //Identifier to indicate on which Aggregate does the Command needs to be processed on
    private String cartNumber;
    private Customer customer;
    private Integer amount;
}
