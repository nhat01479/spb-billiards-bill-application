package com.cg.model.dto.user;

import com.cg.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class UserDTO {
    private Long id;

    private String username;

    private String password;

    private String fullName;

    private String email;

    private String phone;

    private String address;

    private Role role;

    public UserDTO(Long id, String username, String password, String fullName, String email, String phone, String address, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }
}
