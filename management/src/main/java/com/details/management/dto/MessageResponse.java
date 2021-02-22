package com.details.management.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.hibernate.annotations.NotFound;

@Data
public class MessageResponse {
    public MessageResponse(String message) {
        this.message = message;
    }

    public MessageResponse(String message, String errorCode, String errorDetails) {
        this.message = message;
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
    }

    @NotNull
    private String message;
    private String errorCode;
    private String errorDetails;
}
