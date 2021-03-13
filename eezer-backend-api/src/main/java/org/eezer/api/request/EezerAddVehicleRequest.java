package org.eezer.api.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EezerAddVehicleRequest {

    private String vehicleId;
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
