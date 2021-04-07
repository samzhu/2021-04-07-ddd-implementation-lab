package com.example.demo.cartms.application.internal.querygateways;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.example.demo.cartms.domain.queries.FindCartQuery;
import com.example.demo.cartms.interfaces.rest.dto.CartInfoDto;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class CartQueryService {
    private final QueryGateway queryGateway;
    private final EventStore eventStore;

    // 從 EventStore 重現聚合
    public CompletableFuture<CartInfoDto> findById(String cartNumber) {
        log.debug("findById {}", cartNumber);
        return this.queryGateway.query(
                new FindCartQuery(cartNumber),
                ResponseTypes.instanceOf(CartInfoDto.class)
        );
    }

    // 提供相關 Event
    public List<Object> listEvents(String accountNumber) {
        return this.eventStore
                .readEvents(accountNumber)
                .asStream()
                .map(Message::getPayload)
                .collect(Collectors.toList());
    }
    
}
