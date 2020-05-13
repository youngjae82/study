package com.example.demo2.model.xframe;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
/*
 복합키 인데 하나만 설정해서 사용해봄... select 시에는 별문제 없음.
 Id 어노테이션을 두개를 설정하면 현재상태로는 에러 발생함.
 */
public class Module {
    //@Id
    @Column(name = "PROJECTID")
    private String projectId;

    @Id
    @Column(name = "MODULEID")
    private String moduleId;
    @Column(name = "CREATETIME")
    private String createTime;
    @Column(name = "CREATEUSERID")
    private String createUserId;
    @Column(name = "MODIFYTIME")
    private String modifyTime;
    @Column(name = "MODIFYUSERID")
    private String modifyUserId;
}
