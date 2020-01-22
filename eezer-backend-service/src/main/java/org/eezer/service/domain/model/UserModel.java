package org.eezer.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.eezer.api.enums.EezerRole;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Builder(toBuilder = true)
@Document
@ToString
@AllArgsConstructor
public class UserModel {

    @Id
    private String id;

    @NotNull
    @Indexed(unique = true)
    @Length(min = 3, max = 255)
    private String username;

    @NotNull
    @Setter
    @Length(min = 8, max = 255)
    private String password;

    @NotNull
    private EezerRole role;

    @NotNull
    @Length(min = 3, max = 255)
    private String realName;

    @NotNull
    private String phone;

    @Email
    @NotNull
    private String email;

    private String organization;

    private String other;

    private Set<String> vehicles;

    private String createdTime;
}
