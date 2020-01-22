package org.eezer.api.valueobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.eezer.api.enums.EezerRole;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class User {

    private String username;
    private EezerRole role;
    private String realName;
    private String phone;
    private String email;
    private String organization;
    private String other;
    private Set<String> vehicles;
    private String createdTime;

}
