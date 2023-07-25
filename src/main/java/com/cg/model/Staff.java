//package com.cg.model;
//
//import com.cg.model.dto.staff.StaffCreResDTO;
//import com.cg.model.dto.staff.StaffDTO;
//import com.cg.model.dto.staff.StaffUpResDTO;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.experimental.Accessors;
//
//import javax.persistence.*;
//import javax.persistence.criteria.Root;
//
//
//@NoArgsConstructor
//@Getter
//@Setter
//@Accessors(chain = true)
//@Entity
//@Table(name = "staffs")
//public class Staff extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "full_name", nullable = false)
//    private String fullName;
//
//    @Column(nullable = false)
//    private String email;
//
//    @Column(nullable = false)
//    private String phone;
//
//    private String address;
//
//    public Staff(Long id, String fullName, String email, String phone, String address) {
//        this.id = id;
//        this.fullName = fullName;
//        this.email = email;
//        this.phone = phone;
//        this.address = address;
//    }
//
//    public StaffDTO tostaffDTO() {
//        return new StaffDTO()
//                .setId(id)
//                .setFullName(fullName)
//                .setEmail(email)
//                .setPhone(phone)
//                .setAddress(address)
//                ;
//    }
//
//    public StaffCreResDTO tostaffCreResDTO() {
//        return new StaffCreResDTO()
//                .setId(id)
//                .setFullName(fullName)
//                .setEmail(email)
//                .setPhone(phone)
//                .setAddress(address)
//                ;
//    }
//
//    public StaffUpResDTO tostaffUpResDTO() {
//        return new StaffUpResDTO()
//                .setId(id)
//                .setFullName(fullName)
//                .setEmail(email)
//                .setPhone(phone)
//                .setAddress(address)
//                ;
//    }
//}
