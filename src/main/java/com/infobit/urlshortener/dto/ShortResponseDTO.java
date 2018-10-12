package com.infobit.urlshortener.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ShortResponseDTO {
    @JsonProperty("shortUrl")
    private String shortUrl;

    public ShortResponseDTO(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
