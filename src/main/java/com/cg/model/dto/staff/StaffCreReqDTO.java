//package com.cg.model.dto.staff;
//
//import com.cg.model.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.experimental.Accessors;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//import java.math.BigDecimal;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@Accessors(chain = true)
//public class StaffCreReqDTO implements Validator {
//    private long id;
//    private String fullName;
//    private String email;
//    private String phone;
//    private String address;
//
//
//    public Staff toStaff() {
//        return new Staff()
//                .setId(null)
//                .setFullName(fullName)
//                .setEmail(email)
//                .setPhone(phone)
//                .setAddress(address)
//;
//    }
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return StaffCreReqDTO.class.isAssignableFrom(clazz);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        StaffCreReqDTO staffCreReqDTO = (StaffCreReqDTO) target;
//        String fullName = staffCreReqDTO.fullName;
//        String email = staffCreReqDTO.email;
//
//        if (fullName == null || fullName.trim().length() == 0) {
//            errors.rejectValue("fullName", "fullName.length", "Tên không được để trống");
//        } else {
//            if (!fullName.matches("([A-Z]+[a-z]*[ ]?)+$")) {
//                errors.rejectValue("fullName", "fullName.number", "Tên bắt đầu bằng chữ hoa, không được chứa số và ký tự đặc biệt");
//            } else {
//                if (fullName.trim().length() < 5 || fullName.trim().length() > 20) {
//                    errors.rejectValue("fullName", "fullName.length", "Tên từ 5-20 ký tự");
//                }
//            }
//        }
//        if (email == null || email.trim().length() == 0) {
//            errors.rejectValue("email", "email.length", "Email không được để trống");
//        } else {
//            if (!email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,9}$")) {
//                errors.rejectValue("email", "email.regex", "Email không hợp lệ");
//            } else {
//                if (email.trim().length() > 30) {
//                    errors.rejectValue("email", "email.maxlength", "Email tối đa 30 ký tự");
//                }
//            }
//        }
//    }
//}
