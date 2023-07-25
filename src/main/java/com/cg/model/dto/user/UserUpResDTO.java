package com.cg.model.dto.user;

import com.cg.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class UserUpResDTO {
    private Long id;

    private String username;

    private String password;

    private String fullName;

    private String email;

    private String phone;

    private String address;

    private Role role;
}
