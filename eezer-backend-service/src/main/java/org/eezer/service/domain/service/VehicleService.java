package org.eezer.service.domain.service;

import org.eezer.api.request.EezerAddVehicleRequest;
import org.eezer.api.request.EezerEditVehicleRequest;
import org.eezer.api.valueobject.Vehicle;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * The vehicle service is responsible of handling all vehicle requests,
 * such as add vehicle, remove vehicle and edit vehicle.
 */
public interface VehicleService {

    /**
     * Create and persist a new vehicle.
     *
     * @param request the new vehicle data
     *
     * @return the new vehicle object, if successful
     */
    Vehicle addVehicle(@NotNull EezerAddVehicleRequest request);

    /**
     * Remove an existing vehicle by it's vehicleId,
     *
     * @param vehicleId the vehicle id to remove
     */
    void removeVehicle(@NotNull String vehicleId);

    /**
     * Edit an existing vehicle.
     *
     * @param vehicleId the vehicle id to edit
     * @param request the edited vehicle data
     * @return the edited vehicle object
     */
    Vehicle editVehicle(@NotNull String vehicleId, @NotNull EezerEditVehicleRequest request);

    /**
     * Fetch all existing vehicles in the system.
     *
     * @return a list of all existing vehicles
     */
    List<Vehicle> getVehicles();

}
