package org.eezer.api.request;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EezerEditUserRequest {

    // Cannot edit username, password or role.

    private String realName;
    private String phone;
    private String email;
    private String organization;
    private String other;
    private Set<String> vehicles;

}