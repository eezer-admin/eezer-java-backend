package org.eezer.api.valueobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.eezer.api.enums.EezerGender;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Transport {

    // We don't return coordinates with /all.
    // There is a separate call for that, named /coords

    private String transportId;
    private String driverId;
    private String vehicleId;
    private String passengerName;
    private String passengerPhone;
    private EezerGender gender;
    private String reason;
    private Integer distance;
    private Integer duration;
    private String started;
    private String ended;
    private String createdServerTime;

}
