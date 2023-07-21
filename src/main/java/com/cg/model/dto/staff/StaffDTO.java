package com.cg.model.dto.staff;

import com.cg.model.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class StaffDTO {
    private long id;
    private String fullname;
    private String email;
    private String phone;
    private String address;

    public StaffDTO(long id, String fullname, String email, String phone, String address) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
}
