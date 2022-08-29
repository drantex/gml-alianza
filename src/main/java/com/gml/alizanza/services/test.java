package com.gml.alizanza.services;

import org.springframework.util.StringUtils;

public class test {

    private final String DNAA = "AAAA";
    private final String DNAC = "CCCC";
    private final String DNAT = "TTTT";
    private final String DNAG = "GGGG";

    public static void main(String[] args) {
        String[] test = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        getVerticalSecuence( test );
        // String dna;
        // int totalSecuence = 0;
        // for( int i = 0, len = test.length; i < len; i++) {
        //     dna = test[ i ];
            
        //     totalSecuence += ( dna.length() - dna.replace( "AAAA", "" ).length() ) / 4;
        //     totalSecuence += ( dna.length() - dna.replace( "CCCC", "" ).length() ) / 4;
        //     totalSecuence += ( dna.length() - dna.replace( "TTTT", "" ).length() ) / 4;
        //     totalSecuence += ( dna.length() - dna.replace( "GGGG", "" ).length() ) / 4;
        // }
        System.out.println( "Total secuencia: " + test.toString() );
    }


    private static int getVerticalSecuence( String[] dna ) {
        int totalSecuence = 0;
        String[] verticalDna = new String[ dna.length ];
        String dnaSecuence;
        for ( int i = 0, len = dna.length; i < len; i++) {
            dnaSecuence = dna[ i ];
            for ( int j = 0, lenJ = dnaSecuence.length(); j < lenJ; j++ ){

                if ( verticalDna[ j ] == null ) {
                    verticalDna[ j ] = Character.toString( dnaSecuence.charAt(j) );
                } else {
                    verticalDna[ j ] = verticalDna[ j ] + Character.toString( dnaSecuence.charAt(j) );
                }
            }

        }
        return totalSecuence;
    }
    
}
