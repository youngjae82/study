package com.example.demo2.model.xframe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SCREEN")
@IdClass(ScreenPk.class)
public class Screen {

    @Id
    @Column(name = "PROJECTID")
    private String projectId;
    @Id
    @Column(name = "DIRECTORY")
    private String directory;
    @Id
    @Column(name = "SCREENID")
    private String screenId;

    @Column(name = "TITLE")
    private String title;
    @Column(name = "CREATETIME")
    private String createTime;
    @Column(name = "CREATEUSERID")
    private String createUserId;
    @Column(name = "MODIFYTIME")
    private String modifyTime;
    @Column(name = "MODIFYUSERID")
    private String modifyUserId;

    @Column(name = "DATA")
    private String data;
    @Column(name = "JAVASCRIPT")
    private String javaScript;
}
