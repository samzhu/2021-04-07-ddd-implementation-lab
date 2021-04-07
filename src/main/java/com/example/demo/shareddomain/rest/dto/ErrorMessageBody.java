package com.example.demo.shareddomain.rest.dto;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "錯誤訊息結構")
public class ErrorMessageBody {
    @Schema(required = true, description = "時間戳記", example = "2021-03-31T15:26:40.7633+08:00")
    @JsonProperty("timestamp")
    @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime timestamp;
    @Schema(required = true, description = "http status", example = "403")
    @JsonProperty("status")
    private Integer status;
    @Schema(required = true, description = "error type", example = "Forbidden")
    @JsonProperty("error")
    private String error;
    @Schema(required = true, description = "error message", example = "Insufficient Balance: Cannot debit 500 from account number [423376]")
    @JsonProperty("message")
    private String message;
    @Schema(required = true, description = "request uri", example = "/api/xxx")
    @JsonProperty("path")
    private String path;
}
