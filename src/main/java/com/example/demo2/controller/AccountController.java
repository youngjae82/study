package com.example.demo2.controller;

import com.example.demo2.model.base.Account;
import com.example.demo2.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    private AccountService accountService;


    @GetMapping
    public ResponseEntity getAccount() {
        return ResponseEntity.ok(accountService.getAccount());
    }

    @PostMapping
    public ResponseEntity createAccount(@RequestBody Account account, Errors errors) {
        return ResponseEntity.ok(accountService.createAccount(account));

    }
}
