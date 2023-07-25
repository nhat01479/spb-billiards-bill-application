package com.cg.model.dto.desk;

import com.cg.model.Desk;
import com.cg.model.Type;
import com.cg.service.type.TypeServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class DeskUpReqDTO implements Validator {
    private TypeServiceImpl typeService;
    private String name;
    private String priceTime;
    private Long typeId;
    private boolean status;


    public Desk toDesk(Long deskId,Type type) {
        return new Desk()
                .setId(deskId)
                .setName(name)
                .setPriceTime(BigDecimal.valueOf(Long.parseLong(priceTime)))
                .setType(type)
                .setStatus(status);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return DeskUpReqDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DeskUpReqDTO deskUpReqDTO = (DeskUpReqDTO) target;
        String name = deskUpReqDTO.name;
        String priceTimeStr = deskUpReqDTO.priceTime;

        if (priceTimeStr == null) {
            errors.rejectValue("priceTime", "priceTime.length", "Tiền bàn là bắt buộc");
            return;
        }

        if (priceTimeStr.length() == 0) {
            errors.rejectValue("priceTime", "priceTime.length", "Vui lòng nhập giá tiền/giờ cho bàn");
        } else {
            if (!priceTimeStr.matches("\\d+")) {
                errors.rejectValue("priceTime", "priceTime.matches", "Vui lòng nhập giá trị tiền bằng chữ số");
            } else {
                BigDecimal priceTime = BigDecimal.valueOf(Long.parseLong(priceTimeStr));

                if (priceTime.compareTo(BigDecimal.valueOf(20000L)) < 0) {
                    errors.rejectValue("priceTime", "priceTime.min", "Số tiền nhập thấp nhất là 20.000 VND");
                } else {
                    if (priceTime.compareTo(BigDecimal.valueOf(500000L)) > 0) {
                        errors.rejectValue("priceTime", "priceTime.max", "Số tiền nhập tối đa là 500.000 VND");
                    }
                }
            }
        }


        if (name.length() == 0 || name == null) {
            errors.rejectValue("name", "name.empty", "Tên bàn là bắt buộc");
        } else {
            if (name.length() < 5 || name.length() > 20) {
                errors.rejectValue("name", "name.length", "Tên bàn tối thiếu 5 ký tự và tối đa 20 ký tự");
            }
        }
    }
}
