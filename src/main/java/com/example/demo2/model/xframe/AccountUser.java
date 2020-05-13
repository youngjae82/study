package com.example.demo2.model.xframe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AccountUser {
    private String userId;
    private String userName;
}
