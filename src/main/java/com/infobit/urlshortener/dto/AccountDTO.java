package com.infobit.urlshortener.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AccountDTO implements Serializable {
    @JsonProperty("AccountId")
    private String accountId;

    public AccountDTO(String accountId) {
        this.accountId = accountId;
    }
}
