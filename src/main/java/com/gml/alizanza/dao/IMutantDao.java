package com.gml.alizanza.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gml.alizanza.entity.RecordIsMutant;

@Repository()
public interface IMutantDao extends CrudRepository<RecordIsMutant, Long>{

 
    @Query(value = "SELECT COUNT(m) FROM RecordIsMutant m WHERE m.isMutant = ?1")
    Double countRecordsIsMutant(boolean isMutant);
}
