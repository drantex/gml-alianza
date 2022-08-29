package com.gml.alizanza.services;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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

@RunWith(MockitoJUnitRunner.class)
public class MutanServcieImplTest {

	private MutanServcieImpl mutanServcieImpl;

	@Before
	public void setup() {
		this.mutanServcieImpl = new MutanServcieImpl();
	}

	@Test
	public void shouldIsMutant() {
		// TODO: initialize args
		String[] dna;

		boolean actualValue = mutanServcieImpl.isMutant(dna);

		// TODO: assert scenario
	}

	@Test
	public void shouldGetStats() {
		MutantsStatsDto actualValue = mutanServcieImpl.getStats();

		// TODO: assert scenario
	}

	@Test
	public void shouldGetRecords() {
		List<RecordIsMutant> actualValue = mutanServcieImpl.getRecords();

		// TODO: assert scenario
	}
}
