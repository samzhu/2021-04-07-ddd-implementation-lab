package com.example.demo.cartms.interfaces.rest.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "購物車商品新增資訊")
public class AddCartProductDto {
    @NotBlank(message = "商品 ID 為必填")
    @Schema(required = true, description = "商品ID", example = "123456")
    private String productId;
    @NotBlank(message = "商品名稱為必填")
    @Schema(required = true, description = "商品名稱", example = "等身公仔")
    private String productName;
}
