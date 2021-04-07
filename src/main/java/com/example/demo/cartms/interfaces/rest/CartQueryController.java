package com.example.demo.cartms.interfaces.rest;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.example.demo.cartms.application.internal.querygateways.CartQueryService;
import com.example.demo.cartms.interfaces.rest.dto.CartInfoDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/carts")
@Api(value = "Cart Queries", description = "Cart Query Events API")
public class CartQueryController {
    private final CartQueryService cartQueryService;

    @GetMapping("/{cartNumber}")
    public CompletableFuture<CartInfoDto> findById(@PathVariable("cartNumber") String cartNumber) {
        return this.cartQueryService.findById(cartNumber);
    }

    @GetMapping("/{cartNumber}/events")
    public List<Object> listEvents(@PathVariable(value = "cartNumber") String cartNumber) {
        return this.cartQueryService.listEvents(cartNumber);
    }
}
