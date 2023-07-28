package com.cg.model.dto.order.product;

import com.cg.model.Product;
import com.cg.model.dto.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderDetailResDTO {
    private Long id;
    private ProductDTO product;
    private BigDecimal price;
    private String unit;
    private Long quantity;
    private BigDecimal amount;

    public ProductOrderDetailResDTO(Long id, Product product, BigDecimal price, String unit, Long quantity, BigDecimal amount) {
        this.id = id;
        this.product = product.toProductDTO();
        this.price = price;
        this.unit = unit;
        this.quantity = quantity;
        this.amount = amount;
    }
}
