package com.infobip.urlshortener.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <h2>Short Response DTO</h2>
 * <p>
 *     DTO object returned as a response for shortening url request
 * </p>
 * @see com.infobip.urlshortener.dto.ShortRequestDTO
 */
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
