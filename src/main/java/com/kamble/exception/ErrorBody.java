package com.kamble.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
    Custom ERROR class to send to the Client.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorBody {
    private String message;
    private LocalDateTime dateTime;
    private String details;
}
