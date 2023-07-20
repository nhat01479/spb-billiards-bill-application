package com.cg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "time_order_detail")
@Accessors(chain = true)
public class TimeOrderDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="desk_id", referencedColumnName = "id", nullable = false)
    private Desk desk;

    @ManyToOne
    @JoinColumn(name="order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    @Column(name = "start_at", nullable = false, updatable = false)
    private Date startAt;

    @Column(name = "end_at", nullable = false, updatable = false)
    private Date endAt;
}
