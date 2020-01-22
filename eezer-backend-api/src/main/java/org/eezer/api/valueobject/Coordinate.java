package org.eezer.api.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class Coordinate {

    @NotNull
    @NotEmpty
    private Double lat;

    @NotNull
    @NotEmpty
    private Double lng;

}
