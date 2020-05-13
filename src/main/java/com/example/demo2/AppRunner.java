package com.example.demo2;

import com.example.demo2.model.xframe.UserTable;
import com.example.demo2.repository.xframe.FrameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppRunner implements ApplicationRunner {

    private final FrameRepository frameRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Optional<UserTable> user = frameRepository.findByUserTablePk_UserIdAndUserTablePk_ProjectId("admin", "GMES20_DEV");
//        if(user.isPresent()) {
//            return;
//        }

        String encode = passwordEncoder.encode("1qaz2wsx");

        UserTable userInfo = UserTable.builder().userName("admin").userPassword(passwordEncoder.encode("1qaz2wsx")).build();
        // 여기서 save 를 수행함.

        BCryptPasswordEncoder bcr = new BCryptPasswordEncoder();
        String encode1 = bcr.encode("1234");
        System.out.println(encode1);
    }


}
