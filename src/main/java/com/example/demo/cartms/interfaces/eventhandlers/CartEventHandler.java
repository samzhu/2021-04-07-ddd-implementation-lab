package com.example.demo.cartms.interfaces.eventhandlers;

import com.example.demo.cartms.domain.events.CartCreatedEvent;
import com.example.demo.cartms.domain.events.CartProductAddedEvent;
import com.example.demo.cartms.domain.model.aggregates.CartAggregate;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Event Handlers for all events by Aggregate
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CartEventHandler {
    private final Repository<CartAggregate> eventRepository;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @EventHandler
    public void on(CartCreatedEvent event) {
        String sql = "INSERT INTO cart (cart_number, customer, amount, created_by, created_date, last_modified_by, last_modified_date) "
                + " VALUES (:cartNumber, :customer, :amount, :customer, CURRENT_TIMESTAMP, :customer, CURRENT_TIMESTAMP)";
        try {
            log.debug("read Aggregate from EventStore start, aggregateID = {}", event.getCartNumber());
            Aggregate<CartAggregate> cartAggregate = eventRepository.load(event.getCartNumber());
            log.debug("read Aggregate from EventStore End, aggregate identifier = {}",
                    cartAggregate.identifierAsString());
            cartAggregate.execute(cart -> {
                MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
                sqlParameterSource.addValue("cartNumber", cart.getCartNumber());
                sqlParameterSource.addValue("customer", cart.getCustomer().getCustomerName());
                sqlParameterSource.addValue("amount", cart.getAmount());
                namedParameterJdbcTemplate.update(sql, sqlParameterSource);
                log.debug("save cart success");
            });
        } catch (AggregateNotFoundException exception) {
            log.error("can't find cart cartNumber={}", event.getCartNumber(), exception);
        }
    }

    @EventHandler
    public void on(CartProductAddedEvent event) {
        String sqlAddProduct = "INSERT INTO cart_product (cart_number, product_id, product_name, amount, created_by, created_date, last_modified_by, last_modified_date) "
                + " VALUES (:cartNumber, :productId, :productName, :amount, :customer, CURRENT_TIMESTAMP, :customer, CURRENT_TIMESTAMP)";
        String sqlUpdateCart = "UPDATE cart SET amount=:amount, last_modified_date=CURRENT_TIMESTAMP WHERE cart_number=:cartNumber";
        try {
            log.debug("read Aggregate from EventStore start, aggregateID = {}", event.getCartNumber());
            Aggregate<CartAggregate> cartAggregate = eventRepository.load(event.getCartNumber());
            log.debug("read Aggregate from EventStore End, aggregate identifier = {}",
                    cartAggregate.identifierAsString());
            cartAggregate.execute(cart -> {
                MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
                sqlParameterSource.addValue("cartNumber", cart.getCartNumber());
                sqlParameterSource.addValue("productId", event.getProductId());
                sqlParameterSource.addValue("productName", event.getProductName());
                sqlParameterSource.addValue("customer", cart.getCustomer().getCustomerName());
                sqlParameterSource.addValue("amount", event.getProductAmount());
                namedParameterJdbcTemplate.update(sqlAddProduct, sqlParameterSource);
                log.debug("save cart_product success");
                sqlParameterSource = new MapSqlParameterSource();
                sqlParameterSource.addValue("cartNumber", cart.getCartNumber());
                sqlParameterSource.addValue("amount", event.getAmount());
                namedParameterJdbcTemplate.update(sqlUpdateCart, sqlParameterSource);
            });
        } catch (AggregateNotFoundException exception) {
            log.error("can't find cart cartNumber={}", event.getCartNumber(), exception);
        }
    }
}
