package com.cg.model;

import com.cg.model.dto.order.OrderResDTO;
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
@Table(name = "orders")
@Accessors(chain = true)
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="desk_id", referencedColumnName = "id", nullable = false)
    private Desk desk;

    @Column(precision = 10, scale = 0, nullable = false)
    private BigDecimal totalAmount;

    private boolean status;
    public OrderResDTO toOrderResDTO () {
        return new OrderResDTO()
                .setId(id)
                .setUser(user)
                .setDesk(desk)
                .setTotalAmount(totalAmount)
                ;
    }
}
