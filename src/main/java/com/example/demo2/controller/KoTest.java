package com.example.demo2.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class KoTest implements Test{

    @Override
    public String getText() {
        return "hello world~";
    }

}
