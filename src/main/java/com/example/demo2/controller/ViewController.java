package com.example.demo2.controller;


import com.example.demo2.model.xframe.AccountUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/a/")
@RequiredArgsConstructor
public class ViewController {

    @GetMapping
    public String home() {
        return "sample";
    }

    @GetMapping("model")
    public ModelAndView homeView() {
        ModelAndView modelAndView = new ModelAndView("sample");
        modelAndView.addObject("name", "youngjae");
        return modelAndView;
    }

    @GetMapping(value = "data")
    public @ResponseBody String homeString() {
        return "sample";
    }

    @GetMapping(value = "json")
    public @ResponseBody
    AccountUser getUser() {
        return AccountUser.builder().userId("0").userName("yjkim").build();
    }
}
