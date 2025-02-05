package com.palmen.supermarket.shared.exception;

import org.springframework.http.HttpStatus;

public record ErrorResponse(HttpStatus status, String message, String errorcode) {

}
