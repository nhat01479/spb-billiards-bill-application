package com.cg.model.dto.cart.desk;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeskCartDetailReqDTO {
    private Long deskId;

    private Date startAt;
}
