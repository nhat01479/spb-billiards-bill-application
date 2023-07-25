//package com.cg.model.dto.user;
//
//
//import com.cg.model.Role;
//import com.cg.model.User;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//public class UserRegisterReqDTO {
//
//    private String username;
//    private String password;
//    private String fullName;
//    private String email;
//    private String phone;
//    private String address;
//    private Long roleId;
//
//    public User toUser(Role role) {
//        return new User()
//                .setId(null)
//                .setUsername(username)
//                .setPassword(password)
//                .setFullName(fullName)
//                .setEmail(email)
//                .setPhone(phone)
//                .setAddress(address)
//                .setRole(role)
//                ;
//    }
//}
