package org.eezer.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EezerVersionResponse {

    private String message;
    private String version;

}
