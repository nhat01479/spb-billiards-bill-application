package com.cg.model.dto.order;

import com.cg.model.Desk;
import com.cg.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class OrderResDTO {
    private Long id;
    private BigDecimal totalAmount;
    private Desk desk;
    private User user;
    private Date createdAt;

    public OrderResDTO(Long id, BigDecimal totalAmount, Desk desk, User user, Date createdAt) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.desk = desk;
        this.user = user;
        this.createdAt = createdAt;
    }
}

