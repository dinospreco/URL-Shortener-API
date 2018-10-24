package com.infobip.urlshortener.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <h2>
 *     Registration DTO
 * </h2>
 * <p>
 *     DTO object that is returned in response of account registration
 * </p>
 */
@Getter
@NoArgsConstructor
@ToString
public class RegistrationDTO {

    private boolean success;
    private String description;
    private String password;

    public RegistrationDTO(boolean success, String description, String password) {
        this.success = success;
        this.description = description;
        this.password = password;
    }
}
