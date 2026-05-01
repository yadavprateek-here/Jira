package com.prakar.jira.advice;

import com.prakar.jira.exception.ResourceNotFoundException;
import com.prakar.jira.response.ApiError;
import com.prakar.jira.response.ApiResponse;
import com.prakar.jira.response.Meta;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalAdvice {

    private ResponseEntity<ApiResponse<Object>> buildResponse(ApiError error,HttpServletRequest request){
        return ResponseEntity.status(
                HttpStatus.INTERNAL_SERVER_ERROR).body(
                        new ApiResponse<>(false,
                                            null,
                                                error,
                                                new Meta(request.getRequestURI()
                                                )
                        )
        );

    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> ResourceNotFoundException(
            ResourceNotFoundException ex,
            HttpServletRequest request) {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND.toString(), "RESOURCE NOT FOUND", ex.getMessage());
        return buildResponse(error,request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> MethodArgumentNotValidException(
            ResourceNotFoundException ex,
            HttpServletRequest request) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.toString(), "Argument Missing", ex.getMessage());
        return buildResponse(error,request);
    }

}
