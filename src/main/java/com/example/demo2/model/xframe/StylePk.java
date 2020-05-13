package com.example.demo2.model.xframe;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
@Data
public class StylePk implements Serializable {
    @Id
    @Column(name = "PROJECTID")
    private String projectId;
    @Id
    @Column(name = "COMPONENT")
    private String component;
    @Id
    @Column(name = "STYLEID")
    private String styleId;
}
