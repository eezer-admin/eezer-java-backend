package org.eezer.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EezerAddUserRequest {

    private String username;
    private String password;
    private String role; // EezerRole
    private String realName;
    private String phone;
    private String email;
    private String organization;
    private String other;
    private Set<String> vehicles;

}
