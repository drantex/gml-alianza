package com.gml.alizanza.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gml.alizanza.common.HandlerException;
import com.gml.alizanza.dao.IMutantDao;
import com.gml.alizanza.entity.RecordIsMutant;
import com.gml.alizanza.models.MutantsStatsDto;
import com.gml.alizanza.services.interfaces.IMutants;
import com.google.gson.Gson;

@Service
public class MutanServcieImpl implements IMutants {

    public static final char A = 'A';
    public static final char C = 'C';
    public static final char T = 'T';
    public static final char G = 'G';

    private static final String DNAA = "AAAA";
    private static final String DNAC = "CCCC";
    private static final String DNAT = "TTTT";
    private static final String DNAG = "GGGG";

    private static final int MINLENGTH = 4;

    @Autowired
    private IMutantDao mutantDao;

    @Override
    @Transactional(readOnly = false)
    public boolean isMutant(String[] dna) throws HandlerException {
        isValidDnaSecuence(dna);

        boolean isMutant = searcSecuenceDna(dna);
        insertRecord(dna, isMutant);

        return isMutant;
    }

    /**
     * Metodo encargado de validar que exista una secuencia valida,
     * que todos los items de la secuencia correspondan a valores conocidos y sean
     * del mismo tamaño y que la matriz sea cuadrada
     * 
     * @param dna Objeto de la secuencia envíado en el request de la petición
     * @return void
     * @throws Exception Indica error en caso de encontrar fallos en la secuencia.
     */
    private void isValidDnaSecuence(String[] dna) throws HandlerException {

        if (dna.length == 0) {
            throw new HandlerException("NOT_SECUENCE_EXIST");
        }

        int lengthSecuence = dna[0].length();
        int lengthDna = dna.length;

        if (lengthSecuence != lengthDna) {
            throw new HandlerException("SECUENCE_ANORMAL_LENGTH");
        }

        String secuence;
        for (int i = 0; i < lengthDna; i++) {

            secuence = dna[i];
            if (secuence.length() != lengthSecuence) {
                throw new HandlerException("SECUENCE_ANORMAL_LENGTH");
            }

            char[] chars = secuence.toCharArray();
            char letter;
            for (int ic = 0, lenC = chars.length; ic < lenC; ic++) {
                letter = chars[ic];
                if (letter != A && letter != C && letter != G && letter != T) {
                    throw new HandlerException("CHARACTER_SECUENCE_ANORMAL");
                }
            }

        }
    }

    /**
     * Metodo encargado de buscar las secuencias de manera horizontal, vertical y
     * diagonal sobre la matriz.
     * 
     * @param dna Vector con las secuencias envíadas en el request de la petición
     * @return boolean
     * @throws Exception Indica error en caso de encontrar fallos en la secuencia.
     */
    private boolean searcSecuenceDna(String[] dna) {

        int totalSecuence = 0;

        boolean isSearchHorizontal = (dna[0].length() >= MINLENGTH && dna.length > 1)
                || (dna[0].length() >= MINLENGTH * 2);
        if (isSearchHorizontal) {
            totalSecuence = getHorizontalSecuence(dna, totalSecuence);
            if (totalSecuence >= 2) {
                return true;
            }
        }

        boolean isSearchVertical = (dna.length >= MINLENGTH && dna[0].length() > 1) || (dna.length >= MINLENGTH * 2);

        if (isSearchVertical) {
            totalSecuence = getVerticalSecuence(dna, totalSecuence);
            if (totalSecuence >= 2) {
                return true;
            }
        }
        
        boolean isSearchDiagonal = dna[0].length() >= MINLENGTH && dna.length >= MINLENGTH;
        if (isSearchDiagonal) {
            totalSecuence = getDiagonalSecuence(dna, totalSecuence);
            if (totalSecuence >= 2) {
                return true;
            }
        }

        return false;
    }

    /**
     * Metodo encargado de buscar las secuencias que se encuentren en la matriz de
     * manera horizontal
     * 
     * @param dna Vector con las secuencias envíadas en el request de la petición
     * @param totalSecuence catidad de secuencias encontradas
     * @return retorna la cantidad de secuencias encontradas en la matriz de manera
     *         horizontal
     */
    private int getHorizontalSecuence(String[] dna, int totalSecuence) {
        String dnaSecuence;
        for (int i = 0, len = dna.length; i < len; i++) {
            dnaSecuence = dna[i];

            totalSecuence += validSecuenceDnaInSecuence(dnaSecuence);

            if (totalSecuence > 1) {
                return totalSecuence;
            }
        }
        return totalSecuence;
    }

    /**
     * Metodo encargado de buscar las secuencias que se encuentren en la matriz de
     * manera vertical,
     * para esto se procede a voltear la matriz y buscamos ahora si de manera horizontal.
     * 
     * @param dna Vector con las secuencias envíadas en el request de la petición
     * @param totalSecuence catidad de secuencias encontradas
     * @return retorna la cantidad de secuencias encontradas en la matriz de manera
     *         vertical
     */
    private int getVerticalSecuence(String[] dna, int totalSecuence) {

        String[] verticalDna = new String[dna.length];
        String dnaSecuence;
        for (int i = 0, len = dna.length; i < len; i++) {
            dnaSecuence = dna[i];
            for (int j = 0, lenJ = dnaSecuence.length(); j < lenJ; j++) {
                verticalDna[j] = verticalDna[j] == null ? Character.toString(dnaSecuence.charAt(j)) : verticalDna[j] + Character.toString(dnaSecuence.charAt(j));
            }

        }

        return getHorizontalSecuence(verticalDna, totalSecuence);
    }

    /**
     * Metodo encargado de buscar las secuencias que se encuentren en la matriz de
     * manera diagonal.
     * 
     * @param dna Vector con las secuencias envíadas en el request de la petición
     * @param totalSecuence catidad de secuencias encontradas
     * @return retorna la cantidad de secuencias encontradas en la matriz de manera
     *         diagonal
     */
    private int getDiagonalSecuence( String[] dna, int totalSecuence ) {

       String dnaSecuence;
       String diagonalSup = "";
       String diagonalInf = "";

       for ( int i = 0, lenDna = dna.length, j = lenDna - 1; i < lenDna; i++, j-- ) {
        dnaSecuence = dna[i];
        diagonalSup += Character.toString(dnaSecuence.charAt( i ));
        diagonalInf += Character.toString(dnaSecuence.charAt( j ));
       }

       totalSecuence += validSecuenceDnaInSecuence( diagonalSup );
       totalSecuence += validSecuenceDnaInSecuence( diagonalInf );

        return totalSecuence;
    }

    /**
     * Metodo encargado de realizar la busqueda de las secuencias sobre la
     * información enviada,
     * para ello se procede a remplazar la secuencia en la cadena envíada y comparar
     * el tamaño para cada secuencia,
     * de esta manera se logra identificar si existia por lo menos una secuencia
     * sobre la cadena en cuestión.
     * 
     * @param dnaSecuence String contenido en una de las filas de la matriz
     * @return
     */
    private int validSecuenceDnaInSecuence(String dnaSecuence) {
        int totalSecuence = 0;
        int dnaLengthSecuence = dnaSecuence.length();

        totalSecuence += (dnaLengthSecuence - dnaSecuence.replace(DNAA, "").length()) / 4;
        totalSecuence += (dnaLengthSecuence - dnaSecuence.replace(DNAC, "").length()) / 4;
        totalSecuence += (dnaLengthSecuence - dnaSecuence.replace(DNAT, "").length()) / 4;
        totalSecuence += (dnaLengthSecuence - dnaSecuence.replace(DNAG, "").length()) / 4;

        return totalSecuence;
    }

    /**
     * Metodo encargado de persistir en base de datos H2 el registro con el estado de mutante y su secuencia de ADN.
     * @param dna Objeto de la secuencia envíado en el request de la petición
     * @param isMutant Indica si el registro será mutante o no.
     */
    private void insertRecord(String[] dna, boolean isMutant) {
        RecordIsMutant mutantRecord = new RecordIsMutant();
        Gson gson = new Gson();
        mutantRecord.setDna(  gson.toJson( dna ) );
        mutantRecord.setIsMutant(isMutant);
        mutantDao.save(mutantRecord);
    }

    @Override
    public MutantsStatsDto getStats() {
        MutantsStatsDto stats = new MutantsStatsDto();
        stats.setCountMutantDna( mutantDao.countRecordsIsMutant( true ) );
        stats.setCountHumanDna( mutantDao.countRecordsIsMutant( false ) );
        stats.calcRatio();
        return stats;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecordIsMutant> getRecords() {
        return (List<RecordIsMutant>) mutantDao.findAll();
    }

}
