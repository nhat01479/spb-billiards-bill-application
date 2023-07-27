package com.cg.model.dto.user;

import com.cg.model.Role;
import com.cg.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class UserUpReqDTO implements Validator {
    private Long id;

    private String username;

    private String password;

    private String fullName;

    private String email;

    private String phone;

    private String address;

    private long roleId;

    public User toUser(long userId, Role role) {
        return new User()
                .setId(userId)
                .setUsername(username)
                .setPassword(password)
                .setFullName(fullName)
                .setEmail(email)
                .setPhone(phone)
                .setAddress(address)
                .setRole(role)
                ;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserUpReqDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserUpReqDTO userUpReqDTO = (UserUpReqDTO) target;
        String username = userUpReqDTO.username;
        String password = userUpReqDTO.password;
        String fullName = userUpReqDTO.fullName;
        String email = userUpReqDTO.email;


        if (username == null || username.trim().length() == 0) {
            errors.rejectValue("username", "username.length", "Tên đăng nhập không được để trống");
        } else {
            if (!username.matches("^[a-z0-9]+$")) {
                errors.rejectValue("username", "username.number", "Tên đăng nhập bằng chữ thường và số, không được chứa khoảng trắng, chữ viết hoa và ký tự đặc biệt");
            } else {
                if (username.trim().length() < 6 || username.trim().length() > 20) {
                    errors.rejectValue("username", "username.length", "Tên đăng nhập từ 6-20 ký tự");
                }
            }
        }
        if (password == null || password.trim().length() == 0) {
            errors.rejectValue("password", "password.length", "Password không được để trống");
        } else {
            if (!password.matches("^(?=.*\\S)[a-zA-Z0-9]{8,}$")) {
                errors.rejectValue("password", "password.number", "Password bằng chữ và số, không được chứa khoảng trắng và ký tự đặc biệt, độ dài từ 8 ký tự trở lên");
            }
        }
        if (fullName == null || fullName.trim().length() == 0) {
            errors.rejectValue("fullName", "fullName.length", "Tên không được để trống");
        } else {
            if (!fullName.matches("([A-Z]+[a-z]*[ ]?)+$")) {
                errors.rejectValue("fullName", "fullName.number", "Tên bắt đầu bằng chữ hoa, không được chứa số và ký tự đặc biệt");
            } else {
                if (fullName.trim().length() < 5 || fullName.trim().length() > 20) {
                    errors.rejectValue("fullName", "fullName.length", "Tên từ 5-20 ký tự");
                }
            }
        }
        if (email == null || email.trim().length() == 0) {
            errors.rejectValue("email", "email.length", "Email không được để trống");
        } else {
            if (!email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,9}$")) {
                errors.rejectValue("email", "email.regex", "Email không hợp lệ");
            } else {
                if (email.trim().length() > 30) {
                    errors.rejectValue("email", "email.maxlength", "Email tối đa 30 ký tự");
                }
            }
        }
    }
}

