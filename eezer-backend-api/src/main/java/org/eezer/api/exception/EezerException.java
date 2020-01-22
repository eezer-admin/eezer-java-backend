package org.eezer.api.exception;

import lombok.Getter;
import org.eezer.api.enums.EezerErrorCode;
import org.eezer.api.response.EezerErrorResponse;

/**
 * Exception containing information to structure a error response.
 */
@Getter
public class EezerException extends RuntimeException {

    EezerErrorResponse error;

    public EezerException(EezerErrorCode code, String messageExtra) {

        this.error = EezerErrorResponse.builder()
                .success(false)
                .message(code.name())
                .message_extra(messageExtra)
                .build();
    }
}