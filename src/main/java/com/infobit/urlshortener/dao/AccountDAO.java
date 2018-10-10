package com.infobit.urlshortener.dao;

import com.infobit.urlshortener.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDAO extends JpaRepository<Account, Long> {

    Account getAccountByAccountId(String accountId);

}
