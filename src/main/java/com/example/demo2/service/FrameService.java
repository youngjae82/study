package com.example.demo2.service;

import com.example.demo2.model.xframe.*;
import com.example.demo2.pmd.PMDRunner;
import com.example.demo2.repository.xframe.FrameRepository;
import com.example.demo2.repository.xframe.ModuleRepository;
import com.example.demo2.repository.xframe.ScreenRepository;
import com.example.demo2.repository.xframe.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.annotation.processing.FilerException;
import javax.transaction.Transactional;
import java.io.File;
import java.util.*;

@Service
//@AllArgsConstructor
@RequiredArgsConstructor
//@NoArgsConstructor
public class FrameService implements UserDetailsService {

    private final FrameRepository frameRepository;
    private final ScreenRepository screenRepository;
    private final ModuleRepository moduleRepository;
    private final StyleRepository styleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserTable> byUserTablePk_userId = frameRepository.findByUserTablePk_UserIdAndUserTablePk_ProjectId(username, "GMES20_DEV");
        UserTable userTable = byUserTablePk_userId.orElseThrow(() -> new UsernameNotFoundException(username));
        // 사용자의 정보를 user 에 아이디, 이름,  권한객체를 담아서 리턴한다.
        User user = new User(userTable.getUserTablePk().getUserId(), "{bcrypt}$2a$10$falUcZP39CkloWBJnFcnYO.Opb3nzN3PTObXujR8eYmrY.PonQNNG"/*userTable.getUserPassword()*/, authorities());

        return user;
    }

    // Spring Security 에서 권한 객체는 GrantedAuthority 인터페이스를 구현한 클래스 객체로 만든다.
    private Collection<? extends GrantedAuthority> authorities() {
        //return Set.of(new SimpleGrantedAuthority("ROLE_USER"));
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER")); // ROLE_USER 권한이있다면 이 문자열값을 SimpleGrantedAuthority 생성자에 파라미터에 넣어서 권한 객체생성을한다.
        return roles;
    }

    public Map<String, Object> getUserService() {
        Map<String, Object> map = new HashMap<>();
        // 1. findby 를 이용한 사용자 명검색
        List<UserTable> admin = frameRepository.findByUserName("권세영");
        map.put("users", admin);

        //## PK가 하나이상인 경우 api 정의
        // 2. findby 를 이용한 사용자아이디 + 프로젝트명 검색
        final List<UserTable> gmes20_dev = frameRepository.findByUserTablePk(UserTablePk.builder().userId("sy7.kwon").projectId("GMES20_DEV").build());
        map.put("project_users", gmes20_dev);

        // 3. findby 를 이용한 사용자이이디(PK) 하나로만 검색
//        Optional<UserTable> userTables = frameRepository.findByUserTablePk_UserId("sy7.kwon");
//        map.put("users_id", userTables);

        // 4. findby 를 이용한 타이틀명 검색
        List<Screen> byTitle = screenRepository.findByTitle("WEB File Upload");
        map.put("screen", byTitle);

        // 5. findby 를 이용한 프로젝트(pk) + screenid(pk) 검색
        List<Screen> byProjectIdAndScreenId = screenRepository.findByProjectIdAndScreenId("GMES20_DEV", "S2500UM0");
        map.put("project_screen", byProjectIdAndScreenId);

        // 6. 복합키 테이블이지만 복합키 설정을 하지 않고 식별자 하나만 선언해서 PK 검색
        List<Module> gmesModule = moduleRepository.findByProjectIdAndModuleId("GMES20_DEV","CCNConstant");
        map.put("modules", gmesModule);

        return map;
    }

    public Map<String, Object> getUserService(String userId) {
        Map<String, Object> map = new HashMap<>();
        List<UserTable> userTables = frameRepository.findByUserTablePk_UserId(userId);
        map.put("users_id", userTables);
        return map;
    }
//LabelScan
    public Map<String, Object> getStyle(String styleId) {
        Map<String, Object> map = new HashMap<>();
        List<Style> labelScan = styleRepository.findByStyleId(styleId);
        map.put("style", labelScan);
        return map;
    }

    public Map<String, Object> createStyle(Style style) {
        Map<String, Object> map = new HashMap<>();

        Style save = styleRepository.save(style);
        map.put("save_style", save);

        return map;
    }

/*    update
            style
    set
        commentfield=?,
        component=?,
        projectid=?
    where
        styleid=?
    Entity class 에 PK 가 style 만 설정되어있어 save 시에 style만 조건절에 들어가게 되어 문제가 발생할 수 있음.
    */
    public Map<String, Object> updateSytle(Style style) {
        Map<String, Object> map = new HashMap<>();

        List<Style> byStyleId = styleRepository.findByStyleId(style.getStyleId());
        if(byStyleId.isEmpty()) {
            return map;
        }

        Style save = styleRepository.save(style);
        map.put("save_style", save);
        return map;
    }

    /*
    delete
            from
    style
            where
    styleid=?
    Entity class 에 PK 가 style 만 설정되어있어 save 시에 style만 조건절에 들어가게 되어 문제가 발생할 수 있음.
    */
    // No EntityManager with actual transaction available for current thread - cannot reliably process 'remove' call
    // 에러 발생시 Transactional 어노테이션을 붙여서 해결
    @Transactional
    public void deleteStyle(String style) {


        List<Style> byStyleId = styleRepository.findByStyleId(style);
        if(byStyleId.isEmpty()) {
            return;
        }
        
        styleRepository.deleteByStyleId(style);

        return;
    }

    public List<Screen> getScreen() {
        return screenRepository.findByScreenId("S2500UM0");
    }

    public Map<String, Object> getScreenFromId(String screenId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<Screen> byScreenId = screenRepository.findByProjectIdAndScreenId("GMES20_DEV",screenId);
        List<String> strings = xFrameUIFiles(byScreenId);

        map.put("check", strings);
        return map;
    }

    public List<UserTable> getUser() {
        return frameRepository.findByUserUserId("jhmes.baek");
    }


    public List<String> xFrameUIFiles(List<Screen> screens) throws Exception {
        String filePath = "C:\\G-MES2.0\\";
        String javaScript = screens.get(0).getJavaScript();
        String screenId = screens.get(0).getScreenId();
        String fileName = filePath + screenId + ".js";
        File file = new File(fileName);
        byte[] bytes = javaScript.getBytes();
        try {
            FileCopyUtils.copy(bytes, file);
        } catch(Exception ex) {

        }
        List<String> checkFiles = new ArrayList<String>();
        checkFiles.add(fileName.replace("/", "\\\\"));
        return PMDRunner.analyzeXframeUIFiles(checkFiles);
    }
}
