package com.example.demo2.controller;

import com.example.demo2.model.xframe.UserInfoSVO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

//Resource 를 위해 haetos 를 추가해야함.
public class FrameResource extends EntityModel<UserInfoSVO> {
    public FrameResource(UserInfoSVO UserInfoSVO, Link... links) {
        super(UserInfoSVO, links);

    }
}
