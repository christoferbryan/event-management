package com.events.eventmanagement.response;

import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@ToString
public class Response<T> {
    private int statusCode;
    private String message;
    private boolean success = false;
    private T data;

    public Response(int statCode, String statusDesc){
        this.statusCode = statCode;
        this.message = statusDesc;

        if(statusCode == HttpStatus.OK.value()){
            success = true;
        }
    }

    public static <T> ResponseEntity<Response<T>> failedResponse(int statusCode, String message, T data){
        Response<T> response = new Response<>(statusCode, message);
        response.setSuccess(false);
        response.setData(data);

        return ResponseEntity.status(statusCode).body(response);
    }

    public static <T> ResponseEntity<Response<T>> failedResponse(String message){
        return failedResponse(HttpStatus.BAD_REQUEST.value(), message, null);
    }

    public static <T> ResponseEntity<Response<T>> failedResponse(T data){
        return failedResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", data);
    }

    public static <T> ResponseEntity<Response<T>> failedResponse(int statusCode, String message){
        return failedResponse(statusCode, message, null);
    }

    public static <T> ResponseEntity<Response<T>> successResponse(int statusCode, String message, T data){
        Response<T> response = new Response<>(statusCode, message);
        response.setSuccess(true);
        response.setData(data);

        return ResponseEntity.status(statusCode).body(response);
    }

    public static <T> ResponseEntity<Response<T>> successResponse(String message){
        return successResponse(HttpStatus.OK.value(), message, null);
    }

    public static <T> ResponseEntity<Response<T>> successResponse(String message, T data){
        return successResponse(HttpStatus.OK.value(), message, data);
    }
}
