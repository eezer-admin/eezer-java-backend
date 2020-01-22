package org.eezer.service.converter;

import org.eezer.api.enums.EezerRole;
import org.eezer.api.request.EezerAddUserRequest;
import org.eezer.service.domain.model.UserModel;
import org.eezer.service.util.DateUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EezerAddUserRequest2UserModelConverter implements Converter<EezerAddUserRequest, UserModel> {

    /**
     * {@inheritDoc}
     */
    public UserModel convert(EezerAddUserRequest source) {

        return UserModel.builder()
                .username(source.getUsername())
                .role(EezerRole.valueOf(source.getRole()))
                .realName(source.getRealName())
                .phone(source.getPhone())
                .email(source.getEmail())
                .organization(source.getOrganization())
                .other(source.getOther())
                .vehicles(source.getVehicles())
                // add created time to user model
                .createdTime(DateUtil.getWellFormattedNowDate())
                .build();
    }
}
