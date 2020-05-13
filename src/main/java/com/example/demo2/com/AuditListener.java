package com.example.demo2.com;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.Objects;

public class AuditListener {

    @PrePersist
    public void setCreateOn(Auditable auditable) {
        Audit audit = auditable.getAudit();
        if(Objects.isNull(audit)) {
            audit = new Audit();
            auditable.setAudit(audit);
        }
        audit.setCreatedOn(LocalDateTime.now());
        audit.setCreatedBy("create user");
    }

    @PreUpdate
    public void setUpdateOn(Auditable auditable) {
        Audit audit = auditable.getAudit();
        if(Objects.isNull(audit)) {
            audit = new Audit();
            auditable.setAudit(audit);
        }
        audit.setUpdatedOn(LocalDateTime.now());
        audit.setUpdatedBy("update user");
    }
}
