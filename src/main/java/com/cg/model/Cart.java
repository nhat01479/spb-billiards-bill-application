package com.cg.model;

import com.cg.model.BaseEntity;
import com.cg.model.User;
import com.cg.model.dto.cart.CartResDTO;
import com.cg.model.dto.desk.DeskDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 10, scale = 0, nullable = false)
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name="desk_id", referencedColumnName = "id", nullable = false)
    private Desk desk;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public Cart(Long id, BigDecimal totalAmount, Desk desk, User user, Date createAt) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.desk = desk;
        this.user = user;
    }
    public CartResDTO toCartResDTO() {
        return new CartResDTO()
                .setId(id)
                .setTotalAmount(totalAmount)
                .setDesk(desk)
                .setUser(user)
                ;
    }
    public Order toOrder() {
        return new Order()
                .setId(null)
                .setUser(user)
                .setDesk(desk)
                .setTotalAmount(totalAmount)
                ;
    }
}
