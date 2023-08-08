package com.cg.model.dto.order.desk;

import com.cg.model.Desk;
import com.cg.model.dto.desk.DeskDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeskOrderDetailResDTO {
    private Long id;
    private DeskDTO desk;
    private BigDecimal priceTime;
    private String unit;
    private BigDecimal amount;
    private Date startAt;
    private Date endAt;

    public DeskOrderDetailResDTO(Long id, Desk desk, BigDecimal priceTime, String unit, BigDecimal amount, Date startAt, Date endAt) {
        this.id = id;
        this.desk = desk.toDeskDTO();
        this.priceTime = priceTime;
        this.unit = unit;
        this.amount = amount;
        this.startAt = startAt;
        this.endAt = endAt;
    }
}

