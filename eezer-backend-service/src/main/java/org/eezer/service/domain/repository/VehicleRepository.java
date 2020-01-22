package org.eezer.service.domain.repository;

import org.eezer.service.domain.model.VehicleModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleRepository extends MongoRepository<VehicleModel, String> {

    /**
     * Get a vehicle by it's vehicleId.
     *
     * @param vehicleId the vehicleId to find
     * @return the vehicle object, if found
     */
    VehicleModel getByVehicleId(String vehicleId);

}
