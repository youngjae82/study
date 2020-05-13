package com.example.demo2.model.base;

import com.example.demo2.com.Audit;
import com.example.demo2.com.AuditListener;
import com.example.demo2.com.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditListener.class)
@Table(name = "ACCOUNT" , schema = "GMES20DEV")
public class Account implements Auditable {
    @Id
    @GeneratedValue
    @Column(name = "ACCOUNT_ID")
    private Long id;

    @NotNull
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "PASSWORD")
    private String password;
    @NotNull
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Embedded
    @JsonIgnore
    private Audit audit;
}
