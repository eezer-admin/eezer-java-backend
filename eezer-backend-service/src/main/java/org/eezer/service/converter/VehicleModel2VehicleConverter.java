package org.eezer.service.converter;

import org.eezer.api.valueobject.Vehicle;
import org.eezer.service.domain.model.VehicleModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class VehicleModel2VehicleConverter implements Converter<VehicleModel, Vehicle> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Vehicle convert(VehicleModel source) {

        return Vehicle.builder()
            .vehicleId(source.getVehicleId())
            .country(source.getCountry())
            .region(source.getRegion())
            .organization(source.getOrganization())
            .contact(source.getContact())
            .email(source.getEmail())
            .yearOfManufacture(source.getYearOfManufacture())
            .address(source.getAddress())
            .handoverDate(source.getHandoverDate())
            .runningTime(source.getRunningTime())
            .createdTime(source.getCreatedTime())
            .build();
    }
}
