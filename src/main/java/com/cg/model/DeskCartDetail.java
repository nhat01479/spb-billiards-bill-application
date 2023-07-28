package com.cg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "desk_cart_details")
public class DeskCartDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="desk_id", referencedColumnName = "id", nullable = false)
    private Desk desk;

    @ManyToOne
    @JoinColumn(name="cart_id", referencedColumnName = "id", nullable = false)
    private Cart cart;

    @CreationTimestamp
    @Column(name = "start_at", nullable = false, updatable = false)
    private Date startAt;

    @Column(name = "end_at")
    private Date endAt;

    private String unit;

    @Column(precision = 10, scale = 0, nullable = false)
    private BigDecimal priceTime;

    @Column(nullable = false)
    private BigDecimal amount;

}
