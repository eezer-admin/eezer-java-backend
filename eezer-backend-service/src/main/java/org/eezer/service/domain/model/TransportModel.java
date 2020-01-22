package org.eezer.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.eezer.api.enums.EezerGender;
import org.eezer.api.valueobject.Coordinate;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@Document
@ToString
@AllArgsConstructor
public class TransportModel {

    @Id
    private String id;

    @NotNull
    @Indexed(unique = true)
    @Length(min = 10, max = 255)
    private String transportId;

    @NotNull
    @Length(min = 3, max = 255)
    private String driverId;

    @NotNull
    @Length(min = 2, max = 255)
    private String vehicleId;

    private String passengerName;
    private String passengerPhone;

    @NotNull
    private EezerGender gender;

    private String reason;

    @NotNull
    private List<Coordinate> coordinates;

    @NotNull
    @Min(0)
    private Integer distance;

    @NotNull
    @Min(0)
    private Integer duration;

    private String started;
    private String ended;

    private String createdServerTime;
}
