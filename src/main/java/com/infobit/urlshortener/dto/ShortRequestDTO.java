package com.infobit.urlshortener.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ShortRequestDTO {
    @JsonProperty("url")
    private String url;
    @JsonProperty("redirectType")
    private int redirectType;

    public ShortRequestDTO(String url) {
        this.url = url;
        this.redirectType = 301;
    }

    public ShortRequestDTO(String url, int redirectType) {
        this.url = url;
        this.redirectType = redirectType;
    }
}
