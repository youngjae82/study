package com.example.demo2.service;

import com.example.demo2.model.base.Account;
import com.example.demo2.repository.base.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {
    private AccountRepository accountRepository;



    public Object getAccount() {

        List<Account> all = accountRepository.findAll();

        return all;
    }

    public Object createAccount(Account account) {
        Account save = accountRepository.save(account);
        return save;
    }
}
