package com.cg.model.dto.order;

import com.cg.model.Desk;
import com.cg.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OrderResDTO {
    private Long id;
    private BigDecimal totalAmount;
    private Desk desk;
    private User user;
}

