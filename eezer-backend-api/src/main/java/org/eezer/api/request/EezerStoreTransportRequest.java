package org.eezer.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.eezer.api.valueobject.Coordinate;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EezerStoreTransportRequest {

    private String transportId;
    private String driverId;
    private String vehicleId;
    private String passengerName;
    private String passengerPhone;
    private String gender; // EezerGender
    private String reason;
    private List<Coordinate> coordinates;
    private Integer distance;
    private Integer duration;
    private String started;
    private String ended;

}
