package com.infobip.urlshortener.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * <h2>Short Request DTP</h2>
 * <p>
 *     DTO object for accepting request for shortening request
 * </p>
 *
 * @see com.infobip.urlshortener.dto.ShortResponseDTO
 */
@Data
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
