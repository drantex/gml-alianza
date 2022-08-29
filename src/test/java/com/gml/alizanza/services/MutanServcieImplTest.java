package com.gml.alizanza.services;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.gml.alizanza.common.HandlerException;
import com.gml.alizanza.dao.IMutantDao;
import com.gml.alizanza.entity.RecordIsMutant;

public class MutanServcieImplTest {

	@InjectMocks
    private MutanServcieImpl mutants;

	private RecordIsMutant record;

	@Mock
    private IMutantDao mutantDao;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks( this );
		record = new RecordIsMutant();
		record.setDna("dna");
		record.setId( 1L );
		record.setIsMutant( true );
	}

	@Test
	public void shouldIsMutant() {
		when( mutantDao.save( any(RecordIsMutant.class))).thenReturn( null );
		mutants = new MutanServcieImpl();
		String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		boolean isMutant = false;;

		try {
			isMutant = mutants.isMutant(dna);
		} catch (HandlerException e) {
			e.printStackTrace();
		}

		Assertions.assertTrue( isMutant );

		// TODO: assert scenario
	}

	@Test
	public void shouldGetStats() {
		// MutantsStatsDto actualValue = mutanServcieImpl.getStats();
		
		// TODO: assert scenario
	}

	@Test
	public void shouldGetRecords() {
		when( mutantDao.findAll()).thenReturn( Arrays.asList( record ));
		Assertions.assertNotNull( mutants.getRecords());
	}
}
