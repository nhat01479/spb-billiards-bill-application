package com.cg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product_order_detail")
@Accessors(chain = true)
public class ProductOrderDetail extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="product_id", referencedColumnName = "id", nullable = false)
    private Product product;
    private String title;

    @ManyToOne
    @JoinColumn(name="order_id", referencedColumnName = "id", nullable = false)
    private Order order;
    private String unit;


    @Column(precision = 10, scale = 0, nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private BigDecimal amount;
}

