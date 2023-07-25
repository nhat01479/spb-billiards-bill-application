package com.cg.model.dto.desk;

import com.cg.model.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class DeskDTO {
    private long id;
    private String name;
    private BigDecimal priceTime;
    private Type type;
    private boolean status;

    public DeskDTO(long id, String name, BigDecimal priceTime, Type type, boolean status) {
        this.id = id;
        this.name = name;
        this.priceTime = priceTime;
        this.type = type;
        this.status = status;
    }

}
