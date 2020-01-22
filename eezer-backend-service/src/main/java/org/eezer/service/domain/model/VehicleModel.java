package org.eezer.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Builder(toBuilder = true)
@Document
@ToString
@AllArgsConstructor
public class VehicleModel {

    @Id
    private String id;

    @NotNull
    @Indexed(unique = true)
    @Length(min = 2, max = 255)
    private String vehicleId;

    @NotNull
    private String country;

    @NotNull
    private String region;

    @NotNull
    private String organization;

    @NotNull
    private String contact;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String yearOfManufacture;

    private String address;

    private String handoverDate;

    private String runningTime;

    private String createdTime;

}
