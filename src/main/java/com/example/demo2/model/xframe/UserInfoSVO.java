package com.example.demo2.model.xframe;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoSVO {
    private List<UserTable> userTable;
    private List<Screen> screen;
    private List<Style> style;
    private List<Module> module;
}
