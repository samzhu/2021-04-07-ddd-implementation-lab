package com.example.demo.cartms.application.internal.commandgateways;

import java.util.concurrent.CompletableFuture;

import com.example.demo.cartms.domain.commands.AddCartProductCommand;
import com.example.demo.cartms.domain.commands.CreateCartCommand;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartCommandService {
    private final CommandGateway commandGateway;

    public CompletableFuture<String> createCart(CreateCartCommand createCartCommand) {
        log.debug("createCart command={}", createCartCommand);
        return commandGateway.send(createCartCommand);
    }

    public CompletableFuture<String> addCartProduct(AddCartProductCommand addCartProductCommand) {
        log.debug("addCartProduct command={}", addCartProductCommand);
        return commandGateway.send(addCartProductCommand);
    }
}
