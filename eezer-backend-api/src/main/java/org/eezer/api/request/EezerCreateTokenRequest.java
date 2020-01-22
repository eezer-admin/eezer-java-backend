package org.eezer.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EezerCreateTokenRequest {

    private String username;
    private String password;

}
