package com.gml.alizanza.services.interfaces;

import java.util.List;

import com.gml.alizanza.common.HandlerException;
import com.gml.alizanza.entity.RecordIsMutant;
import com.gml.alizanza.models.MutantsStatsDto;

public interface IMutants {
    public boolean isMutant(String[] dna) throws HandlerException;
    public MutantsStatsDto getStats() throws HandlerException;
    public List<RecordIsMutant> getRecords() throws HandlerException;
}
