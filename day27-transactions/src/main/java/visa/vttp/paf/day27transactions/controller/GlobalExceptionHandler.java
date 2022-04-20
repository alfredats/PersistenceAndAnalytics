package visa.vttp.paf.day27transactions.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value={Exception.class}) 
    protected ResponseEntity<String> handleGlobalException(
        HttpServletRequest req,
        Exception ex
    ) {
        ex.printStackTrace();
        JsonObject errorJSON = Json.createObjectBuilder().add("error", ex.getMessage()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorJSON.toString());
    }
    
}
