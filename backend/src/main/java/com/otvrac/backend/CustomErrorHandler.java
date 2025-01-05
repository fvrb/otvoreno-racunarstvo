package com.otvrac.backend;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorHandler implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<ApiResponse<Object>> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        String message = "Resource not found";

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            httpStatus = HttpStatus.valueOf(statusCode);

            if (httpStatus == HttpStatus.METHOD_NOT_ALLOWED) {
                message = "HTTP method not supported";
            } else if (httpStatus == HttpStatus.NOT_IMPLEMENTED) {
                message = "Method not implemented for requested resource";
            }
        }

        return ResponseEntity.status(httpStatus).body(
                new ApiResponse<>(httpStatus.getReasonPhrase(), message, null)
        );
    }
}
