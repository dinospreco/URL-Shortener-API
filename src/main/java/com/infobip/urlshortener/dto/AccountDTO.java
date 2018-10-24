package com.infobip.urlshortener.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 *<h2>Account DTO</h2>
 * <p>
 *     DTO object for accepting JSON object when user is registering account
 * </p>
 */
@Data
public class AccountDTO {
    @JsonProperty("AccountId")
    private String accountId;

    public AccountDTO(String accountId) {
        this.accountId = accountId;
    }
}
