package org.eezer.service.converter;

import org.eezer.api.request.EezerAddVehicleRequest;
import org.eezer.service.domain.model.VehicleModel;
import org.eezer.service.util.DateUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EezerAddVehicleRequest2VehicleModelConverter implements Converter<EezerAddVehicleRequest, VehicleModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleModel convert(EezerAddVehicleRequest source) {

       return  VehicleModel.builder()
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
               // add created time to model
               .createdTime(DateUtil.getWellFormattedNowDate())
               .build();
    }
}
