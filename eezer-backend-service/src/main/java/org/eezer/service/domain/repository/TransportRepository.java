package org.eezer.service.domain.repository;

import org.eezer.service.domain.model.TransportModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TransportRepository extends MongoRepository<TransportModel, String> {

    /**
     * Get a transport by transport id.
     *
     * @param transportId the transportId to find
     * @return the transport object, if found
     */
    TransportModel getByTransportId(String transportId);

    /**
     * Find all transport but remove the coordinates from the result.
     *
     * @return all transports.
     */
    @Query(value = "{}", fields = "{ '-_id': 0,  '-__v': 0, '-coordinates': 0 }")
    List<TransportModel> findAll();
}
