package com.gml.alizanza.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gml.alizanza.common.GenericResponse;
import com.gml.alizanza.common.HandlerException;
import com.gml.alizanza.models.DnaDTO;
import com.gml.alizanza.services.interfaces.IMutants;


@RestController
@RequestMapping("/")
public class MutantsController {
    
    @Autowired
    private IMutants mutants;

    @PostMapping("/mutant")
    public ResponseEntity<GenericResponse> isMutant(@RequestBody(required = true) DnaDTO dna) {
        GenericResponse response = new GenericResponse();
        try {
            boolean isMutant = mutants.isMutant( dna.getDna());
            response.setObject( isMutant );

            if ( isMutant ) {
                response.setCode( HttpStatus.OK );
                response.setMessage( "Is Mutant" );
            } else {
                response.setCode( HttpStatus.FORBIDDEN );
                response.setMessage( "Is Not Mutant" );
            }
        } catch (HandlerException e) {
            response.setCode( HttpStatus.FORBIDDEN );
            response.setMessage( e.getMessage() );
        }
        
        return new ResponseEntity<GenericResponse>( response, response.getCode() );
    }

    @GetMapping("/stats")
    public ResponseEntity<GenericResponse> getstats() {
        GenericResponse response = new GenericResponse();
        try {
            response.setObject( mutants.getStats() );
            response.setCode( HttpStatus.OK );
        } catch (HandlerException e) {
            response.setCode( HttpStatus.FORBIDDEN );
            response.setMessage( e.getMessage() );
        }
        
        return new ResponseEntity<GenericResponse>( response, response.getCode() );
    }

    @GetMapping("/getRecords")
    public ResponseEntity<GenericResponse> getRecords() {
        GenericResponse response = new GenericResponse();
        try {
            response.setObject( mutants.getRecords() );
            response.setCode( HttpStatus.OK );

        } catch (HandlerException e) {
            response.setCode( HttpStatus.FORBIDDEN );
            response.setMessage( e.getMessage() );
        }
        
        return new ResponseEntity<GenericResponse>( response, response.getCode() );
    }
}
