package com.example.demo2.com;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Data
public class Audit {
    @Column(name = "CREATED_ON")
    private LocalDateTime createdOn;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "UPDATED_ON")
    private LocalDateTime updatedOn;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
}
