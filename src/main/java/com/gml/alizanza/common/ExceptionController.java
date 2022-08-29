package com.gml.alizanza.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice(annotations = Controller.class)
public class ExceptionController implements ErrorController {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Map<String, String>> errorHandler (Exception ex){
        Map mapResponse = new HashMap<>();
        mapResponse.put("error", ex.getLocalizedMessage());
        return new ResponseEntity<Map<String, String>>( mapResponse, HttpStatus.FORBIDDEN );
    }
}
