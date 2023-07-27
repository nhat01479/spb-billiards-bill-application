package com.cg.model.dto.cart.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCartDetailReqDTO {

    private Long productId;

    private Long quantity;
}
