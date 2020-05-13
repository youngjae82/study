package com.example.demo2.controller;

import com.example.demo2.model.xframe.AccountUser;
import com.example.demo2.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class HomeController {


    private final HomeService homeService;

    @Autowired
    private Test test;

//    @Autowired
//    private KoTest koTest;

    @GetMapping
    public String hello() {

        String s = test.getText();
        System.out.println(s);

//        String ss = koTest.getText();
//        System.out.println(ss);

        return s;
    }


    @GetMapping(value = "user")
    public @ResponseBody Map<String, Object> getAllUser() throws Exception{
        List<AccountUser> allUser = homeService.getAllUser();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", allUser);

        return map;
    }
}
