package org.eezer.service.converter;

import org.eezer.api.enums.EezerGender;
import org.eezer.api.request.EezerStoreTransportRequest;
import org.eezer.service.domain.model.TransportModel;
import org.eezer.service.util.DateUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EezerStoreTransportRequest2TransportModelConverter implements Converter<EezerStoreTransportRequest, TransportModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public TransportModel convert(EezerStoreTransportRequest source) {

        return TransportModel.builder()
                .transportId(source.getTransportId())
                .driverId(source.getDriverId())
                .vehicleId(source.getVehicleId())
                .passengerName(source.getPassengerName())
                .passengerPhone(source.getPassengerPhone())
                .gender(EezerGender.valueOf(source.getGender()))
                .reason(source.getReason())
                .coordinates(source.getCoordinates())
                .distance(source.getDistance())
                .duration(source.getDuration())
                .started(source.getStarted())
                .ended(source.getEnded())
                .createdServerTime(DateUtil.getWellFormattedNowDate())
                .build();
    }
}
