package org.eezer.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EezerResponse {

    private boolean success;
    private Object data;

}
