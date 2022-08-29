package com.gml.alizanza.models;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MutantsStatsDto implements Serializable{
    Double countMutantDna;
    Double countHumanDna;
    Double ratio;

    public void calcRatio() {
        Double totalRecords = countMutantDna + countHumanDna;
        Double mutantPercentage = (100 * countMutantDna / totalRecords);
        this.setRatio( mutantPercentage );
    }
}
