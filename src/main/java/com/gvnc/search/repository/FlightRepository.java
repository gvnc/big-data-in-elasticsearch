package com.gvnc.search.repository;

import com.gvnc.search.model.Flight;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface FlightRepository extends ElasticsearchRepository<Flight,String> {

}
