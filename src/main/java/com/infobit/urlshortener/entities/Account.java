package com.infobit.urlshortener.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountId;
    private String password;

    public Account(String accountId, String password) {
        this.accountId = accountId;
        this.password = password;
    }
}
