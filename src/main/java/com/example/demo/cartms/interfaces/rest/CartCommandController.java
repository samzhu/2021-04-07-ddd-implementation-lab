package com.example.demo.cartms.interfaces.rest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import com.example.demo.cartms.application.internal.commandgateways.CartCommandService;
import com.example.demo.cartms.domain.commands.AddCartProductCommand;
import com.example.demo.cartms.domain.commands.CreateCartCommand;
import com.example.demo.cartms.interfaces.rest.dto.AddCartProductDto;
import com.example.demo.cartms.interfaces.rest.dto.CreateCartDto;
import com.example.demo.cartms.interfaces.rest.dto.CartIDDto;
import com.example.demo.cartms.interfaces.transform.CartAssembler;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/carts")
public class CartCommandController {
    private final CartCommandService cartCommandService;

    @Operation(summary = "Create Cart", description = "Create Cart need customerName", responses = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Validation failed") })
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CompletableFuture<CartIDDto> createCart(@Valid @RequestBody CreateCartDto createCartDto,
            BindingResult bindingResult) throws Exception {
        log.debug("createCart createCartDto={}", createCartDto);
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        // conver to command
        CreateCartCommand createCartCommand = CartAssembler.toCreateCartCommand(createCartDto);
        CompletableFuture<String> createCartFuture = cartCommandService.createCart(createCartCommand);
        // 當取得結果時, 至少執行沒發生錯誤, 可以回覆建立的編號
        return createCartFuture.thenApply(cartNumberFuture -> new CartIDDto(cartNumberFuture));
    }

    @Operation(summary = "Add Cart Product", description = "Add Cart Product", responses = {
            @ApiResponse(responseCode = "202", description = "Accepted"),
            @ApiResponse(responseCode = "400", description = "Validation failed") })
    @PostMapping(value = "/{cartNumber}/products")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public CompletableFuture<String> addCartProducts(@PathVariable(value = "cartNumber") String cartNumber,
            @Valid @RequestBody AddCartProductDto addCartProductDto) throws InterruptedException, ExecutionException {
        log.debug("addCartProducts, cartNumber={}, addCartProductDto={}", cartNumber, addCartProductDto);
        AddCartProductCommand addCartProductCommand = CartAssembler.toAddCartProductCommand(cartNumber,
                addCartProductDto);
        CompletableFuture<String> addCartProductFuture = cartCommandService.addCartProduct(addCartProductCommand);
        // 這個會回 void
        return addCartProductFuture;
    }
}
