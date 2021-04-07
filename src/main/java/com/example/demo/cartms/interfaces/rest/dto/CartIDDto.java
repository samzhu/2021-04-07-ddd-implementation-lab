package com.example.demo.cartms.interfaces.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "購物車資訊")
public class CartIDDto {
    @Schema(required = true, description = "購物車編號", example = "123456")
    private String cartNumber;
}
