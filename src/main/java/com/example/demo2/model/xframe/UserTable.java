package com.example.demo2.model.xframe;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor @NoArgsConstructor
//@IdClass(userTablePK.class)
//@NoArgsConstructor
@Table(name = "USERTABLE")
public class UserTable {
//    @Id
//    private String projectid;
//    @Id
//    private String userid;
//    Usertable(String username){
//
//    }
    @EmbeddedId
    private UserTablePk userTablePk;

    @Column(name = "USERPASSWORD")
    @JsonInclude(JsonInclude.Include.NON_NULL) // json 보낼때 히든처리
    private String userPassword;

    @Column(name = "USERNAME", nullable = true /*, updatable = false*/)
    private String userName;
    @Column(name = "USERLEVEL")
    private String userLevel;
    @Column(name = "MANAGERUSERID")
    private String managerUserId;

    //private String screenPermission;

}
//
//@Embeddable
//@Data
//class userTablePK implements Serializable {
//    private String projectid;
//    private String userid;
//}
