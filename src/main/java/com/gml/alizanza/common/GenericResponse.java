package com.gml.alizanza.common;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class GenericResponse implements Serializable {
    private HttpStatus code;
    private String message;
    private Object object;
}
