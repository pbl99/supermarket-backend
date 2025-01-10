package com.palmen.supermarket.exception;

import org.springframework.http.HttpStatus;

public record ErrorResponse(HttpStatus status, String message, String errorcode) {

}
