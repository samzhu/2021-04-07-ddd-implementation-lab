package com.example.demo.cartms.interfaces.rest.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "購物車建立資訊")
public class CreateCartDto {
    @NotBlank(message = "客戶姓名 為必填")
    @Schema(required = true, description = "客戶姓名", example = "浩哥宇宙")
    private String customerName;
}
