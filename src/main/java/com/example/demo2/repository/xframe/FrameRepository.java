package com.example.demo2.repository.xframe;

import com.example.demo2.model.xframe.UserTable;
import com.example.demo2.model.xframe.UserTablePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FrameRepository extends JpaRepository<UserTable, UserTablePk> {

    // api 명은 테이블명이 아닌 클래스에 정의한 변수명과 동일해야함... 대소문자 주의
    List<UserTable> findByUserName(String name);

    // 파라미터 명은 테이블 컬럼명 또는 클래스 변수명 과 달라도 상관없음.
    List<UserTable> findByUserTablePk(UserTablePk value);

    //List<UserTable> findByUserTablePk_UserId(String userId);
    List<UserTable> findByUserTablePk_UserId(String userId);

    Optional<UserTable> findByUserTablePk_UserIdAndUserTablePk_ProjectId(String userId, String projectId);


    @Query(value = "select * from USERTABLE c where 1=1 and c.userid = :userId", nativeQuery = true)
    List<UserTable> findByUserUserId(@Param("userId") String userId);
}
