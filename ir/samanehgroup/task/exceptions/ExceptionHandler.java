package ir.samanehgroup.task.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleBadRequest(WebRequest request) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("Message", "Your input is wrong!");
        errorDetails.put("Details", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleRequestMethodNotSupport(WebRequest request) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("Message", "Your request method is wrong!");
        errorDetails.put("Details", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity<?> handleNoResourceFound(WebRequest request) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("Message", "Your url is wrong!");
        errorDetails.put("Details", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrity(WebRequest request) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("Message", "Your input is wrong!");
        errorDetails.put("Details", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(Exception ex, WebRequest request) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("Message", ex.getMessage());
        errorDetails.put("Details", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.EXPECTATION_FAILED);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElement(Exception ex, WebRequest request) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("Message", ex.getMessage());
        errorDetails.put("Details", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

}
