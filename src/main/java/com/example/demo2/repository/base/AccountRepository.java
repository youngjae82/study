package com.example.demo2.repository.base;

import com.example.demo2.model.base.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {

}
