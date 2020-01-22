package org.eezer.service.converter;

import org.eezer.api.valueobject.Transport;
import org.eezer.service.domain.model.TransportModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TransportModel2TransportConverter implements Converter<TransportModel, Transport> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Transport convert(TransportModel source) {

        return Transport.builder()
            .transportId(source.getTransportId())
            .driverId(source.getDriverId())
            .vehicleId(source.getVehicleId())
            .passengerName(source.getPassengerName())
            .passengerPhone(source.getPassengerPhone())
            .gender(source.getGender())
            .reason(source.getReason())
            .distance(source.getDistance())
            .duration(source.getDuration())
            .started(source.getStarted())
            .ended(source.getEnded())
            .createdServerTime(source.getCreatedServerTime())
            .build();
    }
}
