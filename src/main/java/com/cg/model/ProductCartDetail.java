package com.cg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product_cart_details")
public class ProductCartDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;
    private String title;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id", nullable = false)
    private Cart cart;

    private String unit;

    @Column(precision = 10, scale = 0, nullable = false)
    private BigDecimal price;

    private Long quantity;

    @Column(precision = 10, scale = 0, nullable = false)
    private BigDecimal amount;

    public ProductOrderDetail toProductOrderDetail (Order order) {
        return new ProductOrderDetail()
                .setId(null)
                .setProduct(product)
                .setTitle(title)
                .setOrder(order)
                .setUnit(unit)
                .setPrice(price)
                .setQuantity(quantity)
                .setAmount(amount)
                ;
    }

}