package com.example.demo2.model.xframe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScreenPk implements Serializable {
    @Id
    @Column(name = "PROJECTID")
    private String projectId;
    @Id
    @Column(name = "DIRECTORY")
    private String directory;
    @Id
    @Column(name = "SCREENID")
    private String screenId;
}
