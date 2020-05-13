package com.example.demo2.model.xframe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

// 결합 인덱스(PK가 하나이상인경우)
/*
객체지향스럽고 깔끔하긴하지만 pk 정보를 가지고 올때 복잡한 부분이있음
*/
@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTablePk implements Serializable {
    @Column(name = "PROJECTID", nullable = false /*컬럼의 not null 조건 을 걸어주지만 유효성 검사는 않함.*/)
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String projectId;
    @Column(name = "USERID", nullable = false /*, unique = true, length = 100*/)
    private String userId;
}
