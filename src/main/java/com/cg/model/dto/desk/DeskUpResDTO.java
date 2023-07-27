package com.cg.model.dto.desk;

import com.cg.model.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class DeskUpResDTO {
    private long id;
    private String name;
    private BigDecimal priceTime;
    private String unit = "Giờ";
    private Type type;
    private boolean status;
}
