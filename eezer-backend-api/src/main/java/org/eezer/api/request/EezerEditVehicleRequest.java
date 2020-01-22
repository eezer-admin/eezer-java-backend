package org.eezer.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EezerEditVehicleRequest {

    private String country;
    private String region;
    private String organization;
    private String contact;
    private String email;
    private String yearOfManufacture;
    private String address;
    private String handoverDate;
    private String runningTime;

}
