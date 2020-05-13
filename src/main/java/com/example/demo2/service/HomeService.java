package com.example.demo2.service;

import com.example.demo2.model.xframe.AccountUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {

    public List<AccountUser> getAllUser() {
        List<AccountUser> list = new ArrayList<AccountUser>();
        AccountUser kim = AccountUser.builder().userId("0").userName("kim").build();
        AccountUser choi = AccountUser.builder().userId("1").userName("choi").build();
        list.add(kim);
        list.add(choi);
        return list;
    }
}
