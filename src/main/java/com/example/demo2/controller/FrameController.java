package com.example.demo2.controller;

import com.example.demo2.model.xframe.Screen;
import com.example.demo2.model.xframe.Style;
import com.example.demo2.model.xframe.UserInfoSVO;
import com.example.demo2.service.FrameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/xframe")
public class FrameController {

    @Autowired
    FrameService frameService;

    @GetMapping
    public String getHome() {
        return "AAAAAAA";
    }

    //http://localhost:8080/xframe/user
    @GetMapping("user")
    public Map<String, Object> getUser() {

        return frameService.getUserService();
    }

    //http://localhost:8080/xframe/user/admin
    @GetMapping("user/{id}")
    public Map<String, Object> getUser(@PathVariable("id") String userId) {
        return frameService.getUserService(userId);
    }
    //http://localhost:8080/xframe/style?id=KYJ
    @GetMapping("style")
    public Map<String, Object> getStyle(@RequestParam(value = "id", required = false, defaultValue = "TEST") String styleId) {
        return frameService.getStyle(styleId);
    }

    // 복합키인경우 하나만 id 로 지정해도 저장은 잘됨.
    @PostMapping("style")
    public Map<String, Object> createStyle(@RequestBody Style style) {
        return frameService.createStyle(style);
    }

    // 복합키인경우 하나만 id 로 지정해도 수정은 잘됨.
    @PutMapping("style")
    public Map<String, Object> updateStyle(@RequestBody Style style) {
        return frameService.updateSytle(style);
    }

    //http://localhost:8080/xframe/style/KYJ2
    @DeleteMapping("style/{id}")
    public void deleteStyle(@PathVariable("id") String style) {
        frameService.deleteStyle(style);
    }

    //http://localhost:8080/xframe/screen/
    @GetMapping("screen")
    public ResponseEntity getScreen() {
        return ResponseEntity.ok(frameService.getUser());
    }

    @GetMapping("screen/{id}")
    public ResponseEntity getScreenFromId(@PathVariable("id") String screenId) throws Exception {
        return ResponseEntity.ok(frameService.getScreenFromId(screenId));
    }

    // hateos 사용
    //http://localhost:8080/xframe/screen2/
    @GetMapping("screen2")
    public ResponseEntity getScreen2() {
        UserInfoSVO userInfoSVO = new UserInfoSVO();
        List<Screen> screen = frameService.getScreen();
        userInfoSVO.setScreen(screen);
        FrameResource frameResource = new FrameResource(userInfoSVO);
        //frameResource.add(new Link("/docs/account.html#resources-account-get").withRel("profile"));
        frameResource.add(linkTo(FrameController.class).slash("screen2").withRel("getScreen2"));
        return ResponseEntity.ok(frameResource);
    }
}
