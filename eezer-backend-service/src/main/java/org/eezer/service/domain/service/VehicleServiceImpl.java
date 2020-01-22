package org.eezer.service.domain.service;

import org.eezer.api.request.EezerAddVehicleRequest;
import org.eezer.api.request.EezerEditVehicleRequest;
import org.eezer.api.valueobject.Vehicle;
import org.eezer.service.domain.exception.RecordNotFoundException;
import org.eezer.service.domain.model.VehicleModel;
import org.eezer.service.domain.repository.VehicleRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * Default implementation of the {@link VehicleService} interface.
 */
@Service
public class VehicleServiceImpl implements VehicleService {

    @Resource
    private VehicleRepository vehicleRepository;

    @Resource
    private ConversionService conversionService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Vehicle addVehicle(@NotNull EezerAddVehicleRequest request) {

        VehicleModel vehicleModel = conversionService.convert(request, VehicleModel.class);
        vehicleRepository.save(vehicleModel);

        return conversionService.convert(vehicleModel, Vehicle.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeVehicle(@NotNull String vehicleId) {

        VehicleModel vehicle = vehicleRepository.getByVehicleId(vehicleId);

        if (vehicle != null) {
            vehicleRepository.delete(vehicle);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vehicle editVehicle(@NotNull String vehicleId, @NotNull EezerEditVehicleRequest request) {

        VehicleModel vehicle = vehicleRepository.getByVehicleId(vehicleId);

        if (vehicle != null) {

            VehicleModel editedVehicle = vehicle.toBuilder()
                    .country(request.getCountry() != null ? request.getCountry() : vehicle.getCountry())
                    .region(request.getRegion() != null ? request.getRegion() : vehicle.getRegion())
                    .organization(request.getOrganization() != null ? request.getOrganization() : vehicle.getOrganization())
                    .contact(request.getContact() != null ? request.getContact() : vehicle.getContact())
                    .email(request.getEmail() != null ? request.getEmail() : vehicle.getEmail())
                    .yearOfManufacture(request.getYearOfManufacture() != null ? request.getYearOfManufacture() : vehicle.getYearOfManufacture())
                    .address(request.getAddress() != null ? request.getAddress() : vehicle.getAddress())
                    .handoverDate(request.getHandoverDate() != null ? request.getHandoverDate() : vehicle.getHandoverDate())
                    .runningTime(request.getRunningTime() != null ? request.getRunningTime() : vehicle.getRunningTime())
                    .build();

            vehicleRepository.save(editedVehicle);
            return conversionService.convert(editedVehicle, Vehicle.class);
        }

        throw new RecordNotFoundException("No vehicle found with vehicleId " + vehicleId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Vehicle> getVehicles() {

        List<VehicleModel> vehicleModelList = vehicleRepository.findAll();
        return Arrays.asList(conversionService.convert(vehicleModelList, Vehicle[].class));
    }
}
