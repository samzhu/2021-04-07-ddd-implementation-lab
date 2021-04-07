package com.example.demo.cartms.domain.queryhandlers;

import com.example.demo.cartms.domain.model.aggregates.CartAggregate;
import com.example.demo.cartms.domain.queries.FindCartQuery;
import com.example.demo.cartms.interfaces.rest.dto.CartInfoDto;

import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.Repository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartAggregateQueryHandler {
    private final Repository<CartAggregate> eventRepository;

    @QueryHandler
    public CartInfoDto handle(FindCartQuery query) {
        log.debug("Handling FindCartQuery query: {}", query);
        CartInfoDto cartInfoDto = new CartInfoDto();
        Aggregate<CartAggregate> cartAggregate = eventRepository.load(query.getCartNumber());
        cartAggregate.execute(aggregate -> {
            cartInfoDto.setCartNumber(aggregate.getCartNumber());
            cartInfoDto.setCustomerName(aggregate.getCustomer().getCustomerName());
            cartInfoDto.setAmount(aggregate.getAmount());
            cartInfoDto.setCartProducts(aggregate.getCartProducts());
        });
        return cartInfoDto;
    }

}
