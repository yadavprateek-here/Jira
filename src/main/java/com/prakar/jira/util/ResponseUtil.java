package com.prakar.jira.util;

import com.prakar.jira.response.ApiResponse;
import com.prakar.jira.response.Meta;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ResponseUtil {

    public static <T> ResponseEntity<ApiResponse<T>> ok(T data, HttpServletRequest request) {
        return buildResponse(data, request, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(T data, HttpServletRequest request) {
        return buildResponse(data, request, HttpStatus.CREATED);
    }

    public static <T> ResponseEntity<ApiResponse<T>> buildResponse(
            T data,
            HttpServletRequest request,
            HttpStatus status) {

        return ResponseEntity.status(status).body(
                new ApiResponse<>(
                        true,
                        data,
                        null,
                        new Meta(request.getRequestURI())
                )
        );
    }
}