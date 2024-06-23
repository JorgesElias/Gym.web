package Gym.comercial.demo.infrastructure.adapters.input.rest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import Gym.comercial.demo.domain.exception.ClienteNotFoundException;
import Gym.comercial.demo.domain.exception.DuenoNotFoundException;

import Gym.comercial.demo.domain.exception.MembresiaNotFoundException;



import Gym.comercial.demo.domain.model.ErrorResponse;
import Gym.comercial.demo.utils.ErrorCatalog;



@RestControllerAdvice
public class GlobalControllerAdvice {
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
        ClienteNotFoundException.class, 
        MembresiaNotFoundException.class, 
        DuenoNotFoundException.class,
     
    })
    public ErrorResponse handleNotFoundException(RuntimeException exception) {
        ErrorCatalog errorCatalog;

        if (exception instanceof ClienteNotFoundException) {
            errorCatalog = ErrorCatalog.CLIENTE_NOT_FOUND;
        } else if (exception instanceof MembresiaNotFoundException) {
            errorCatalog = ErrorCatalog.MEMBRESIA_NOT_FOUND;
        } else if (exception instanceof DuenoNotFoundException) {
            errorCatalog = ErrorCatalog.DUENO_NOT_FOUND;
        }
     else {
            errorCatalog = ErrorCatalog.GENERIC_NOT_FOUND;
        }

        return ErrorResponse.builder()
            .code(errorCatalog.getCode())
            .message(errorCatalog.getMessage())
            .timestamp(LocalDateTime.now())
            .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        List<String> details = result.getFieldErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList());

        ErrorCatalog errorCatalog;
        String detailsString = details.toString().toLowerCase();
        if (detailsString.contains("cliente")) {
            errorCatalog = ErrorCatalog.INVALID_CLIENTE;
        } else if (detailsString.contains("membresia")) {
            errorCatalog = ErrorCatalog.INVALID_MEMBRESIA;
        } else if (detailsString.contains("dueno")) {
            errorCatalog = ErrorCatalog.INVALID_DUENO;
        } 
        else {
            errorCatalog = ErrorCatalog.GENERIC_BAD_REQUEST;
        }

        return ErrorResponse.builder()
            .code(errorCatalog.getCode())
            .message(errorCatalog.getMessage())
            .details(details)
            .timestamp(LocalDateTime.now())
            .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGenericError(Exception exception) {
        return ErrorResponse.builder()
            .code(ErrorCatalog.GENERIC_ERROR.getCode())
            .message(ErrorCatalog.GENERIC_ERROR.getMessage())
            .details(Collections.singletonList(exception.getMessage()))
            .timestamp(LocalDateTime.now())
            .build();
    }
}