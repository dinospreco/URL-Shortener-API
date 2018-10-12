package com.infobit.urlshortener.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalUrl;
    private String shortUrl;
    private Long visits;
    private int redirectType;

    @ManyToOne
    private User user;

    public Url(String originalUrl, String shortUrl,int redirectType, User user) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.redirectType = redirectType;
        this.user = user;
    }
}
