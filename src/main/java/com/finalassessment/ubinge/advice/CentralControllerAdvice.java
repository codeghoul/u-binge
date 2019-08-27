package com.finalassessment.ubinge.advice;

import com.finalassessment.ubinge.exception.NotFoundException;
import com.finalassessment.ubinge.exception.OrderStatusException;
import com.finalassessment.ubinge.exception.PaymentModeException;
import com.finalassessment.ubinge.exception.PriceMismatchException;
import com.finalassessment.ubinge.pojo.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;


@RestControllerAdvice
@Slf4j
public class CentralControllerAdvice {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleConstraintViolation(
            MethodArgumentNotValidException ex, WebRequest request) {
        log.error("Error occurred due to Method Argument Not Valid");
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "Invalid Input Parameter", ex.getMessage());
        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest webRequest) {
        log.error("Error occurred due to something not found.");
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "Not Found", ex.getMessage());
        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({PriceMismatchException.class})
    public ResponseEntity<Object> handlePriceMismatchException(PriceMismatchException ex, WebRequest webRequest) {
        log.error("Error occurred due to mismatch in price.");
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "Price Mismatch", ex.getMessage());
        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({PaymentModeException.class})
    public ResponseEntity<Object> handlePaymentModeException(PaymentModeException ex, WebRequest webRequest) {
        log.error("Error occurred due to payment mode modification.");
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "Payment Mode Exception", ex.getMessage());
        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({OrderStatusException.class})
    public ResponseEntity<Object> handleOrderStatusException(OrderStatusException ex, WebRequest webRequest) {
        log.error("Error occurred due to order status modification.");
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "Order Status Exception", ex.getMessage());
        return new ResponseEntity<>(errorDetails, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }
}
