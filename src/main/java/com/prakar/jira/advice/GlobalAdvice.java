package com.prakar.jira.advice;

import com.prakar.jira.exception.ResourceNotFoundException;
import com.prakar.jira.response.ApiError;
import com.prakar.jira.response.ApiResponse;
import com.prakar.jira.response.Meta;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalAdvice {
    private static final Logger log = LoggerFactory.getLogger(GlobalAdvice.class);
    private ResponseEntity<ApiResponse<Object>> buildResponse(ApiError error,HttpServletRequest request){
        return ResponseEntity.status(
                error.getCode()).body(
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
        log.error(LocalDateTime.now() + " -- Req:"+request+"  -- logs:"+ex.getMessage());
        ApiError error = new ApiError(HttpStatus.NOT_FOUND, "RESOURCE NOT FOUND", ex.getMessage());
        return buildResponse(error,request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> MethodArgumentNotValidException(
            ResourceNotFoundException ex,
            HttpServletRequest request) {
        log.error(LocalDateTime.now() + " -- Req:"+request+"  -- logs:"+ex.getMessage());
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, "Argument Missing", ex.getMessage());
        return buildResponse(error,request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> RuntimeExc(
            Exception ex,
            HttpServletRequest request) {
        log.error(LocalDateTime.now() + " -- Req:"+request+"  -- logs:"+ex.getMessage() + " ----" + ex.getCause());
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Exception", "sorry something went wrong");
        return buildResponse(error,request);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApiResponse<Object>> RuntimeExc(
            DataAccessException ex,
            HttpServletRequest request) {
        log.error(LocalDateTime.now() + " -- Req:"+request+"  -- logs:"+ex.getMessage());
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "DataAccessException", "sorry something went wrong");
        return buildResponse(error,request);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Object>> RuntimeExc(
            BadCredentialsException ex,
            HttpServletRequest request) {
            log.error(LocalDateTime.now() + " -- Req:"+request+"  -- logs:"+ex.getMessage());
        ApiError error = new ApiError(HttpStatus.UNAUTHORIZED, "BadCredentialsException", "Invalid username or password");
        return buildResponse(error,request);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ApiResponse<Object>> RuntimeExc(
            AuthorizationDeniedException ex,
            HttpServletRequest request) {
        log.error(LocalDateTime.now() + " -- Req:"+request+"  -- logs:"+ex.getMessage());
        ApiError error = new ApiError(HttpStatus.UNAUTHORIZED, "AuthorizationDeniedException", "Authorization Required");
        return buildResponse(error,request);
    }



}
