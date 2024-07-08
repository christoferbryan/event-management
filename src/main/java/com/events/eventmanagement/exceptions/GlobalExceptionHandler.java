package com.events.eventmanagement.exceptions;

import com.events.eventmanagement.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.UnknownHostException;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler{
    @ExceptionHandler(DataNotFoundException.class)
    public final ResponseEntity<Response<String>> handleDataNotFoundException(DataNotFoundException ex){
        return Response.failedResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(InputException.class)
    public final ResponseEntity<Response<String>> handleInputException(InputException ex){
        return Response.failedResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Response<String>> handleValidationException(MethodArgumentNotValidException ex){
        log.error(ex.getMessage(), ex);
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        return Response.failedResponse(HttpStatus.BAD_REQUEST.value(), "Unable to process the request: "+ errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Response<String>> handleAllExceptions(Exception ex) {

        log.error(ex.getMessage(), ex.getCause(), ex);

        if (ex.getCause() instanceof UnknownHostException) {
            return Response.failedResponse(HttpStatus.BAD_REQUEST.value(), "Unable to process the request: " + ex.getLocalizedMessage());
        }

        return Response.failedResponse(HttpStatus.BAD_REQUEST.value(), "Unable to process the request: " + ex.getMessage());
    }
}
