package com.example.demo.cartms.domain.model.aggregates;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.cartms.domain.commands.AddCartProductCommand;
import com.example.demo.cartms.domain.commands.CreateCartCommand;
import com.example.demo.cartms.domain.events.CartCreatedEvent;
import com.example.demo.cartms.domain.events.CartProductAddedEvent;
import com.example.demo.cartms.domain.model.entites.CartProduct;
import com.example.demo.cartms.domain.model.valueobjects.Customer;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * 購物車聚合
 * 
 * @CommandHandl 註解功能是您決策/業務邏輯的地方
 * @EventSourcingHandler 告訴框架關注什麼事件
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Slf4j
@Aggregate(snapshotTriggerDefinition = "snapshotTrigger") // Axon provided annotation for marking Cart as an Aggregate
public class CartAggregate {
    @AggregateIdentifier // Axon provided annotation for marking the cartNumber as the Aggregate
                         // Identifier
    private String cartNumber;
    private Customer customer;
    private Integer amount;

    private Map<String, CartProduct> cartProducts;

    @CommandHandler
    public CartAggregate(CreateCartCommand command) {
        log.debug("Aggregate CreateCartCommand aggregate={}, command={}", this, command);
        apply(new CartCreatedEvent(command.getCartNumber(), command.getCustomer(), command.getAmount(),
                new HashMap<String, CartProduct>()));
    }

    @EventSourcingHandler
    public void on(CartCreatedEvent event) {
        log.debug("Aggregate CartCreatedEvent aggregate={}, event={}", this, event);
        this.cartNumber = event.getCartNumber();
        this.customer = event.getCustomer();
        this.amount = event.getAmount();
        this.cartProducts = event.getCartProducts();
    }

    @CommandHandler
    public void on(AddCartProductCommand command) {
        log.debug("Aggregate AddCartProductCommand aggregate={}, command={}", this, command);
        // 在 CommandHandler 處理需要的商業邏輯 ex: 已加過的商品無法再加入
        if (this.cartProducts.get(command.getProductId()) != null) {
            throw new RuntimeException(String.format("無法加入 已存在之商品 id=%s", command.getProductId()));
        } else {
            this.amount = this.amount + command.getAmount();
            apply(new CartProductAddedEvent(command.getCartNumber(), this.customer, this.amount, command.getProductId(),
                    command.getProductName(), command.getAmount()));
        }
    }

    @EventSourcingHandler
    public void on(CartProductAddedEvent event) {
        log.debug("Aggregate CartProductAddedEvent aggregate={}, event={}", this, event);
        this.amount = event.getAmount();
        this.cartProducts.put(event.getProductId(),
                new CartProduct(event.getProductId(), event.getProductName(), event.getProductAmount()));
    }

}
