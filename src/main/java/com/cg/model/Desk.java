package com.cg.model;

import com.cg.model.dto.desk.DeskCreResDTO;
import com.cg.model.dto.desk.DeskDTO;
import com.cg.model.dto.desk.DeskUpResDTO;
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
@Table(name = "desks")
@Accessors(chain = true)
public class Desk extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal priceTime;

    @ManyToOne
    @JoinColumn(name="type_id", referencedColumnName = "id", nullable = false)
    private Type type;

    @Column(nullable = false)
    private boolean status;


    public DeskDTO toDeskDTO() {
        return new DeskDTO()
                .setId(id)
                .setName(name)
                .setPriceTime(priceTime)
                .setType(type)
                .setStatus(status)
                ;
    }

    public DeskCreResDTO todeskCreResDTO() {
        return new DeskCreResDTO()
                .setId(id)
                .setName(name)
                .setPriceTime(priceTime)
                .setType(type)
                .setStatus(status)
                ;
    }

    public DeskUpResDTO todeskUpResDTO() {
        return new DeskUpResDTO()
                .setId(id)
                .setName(name)
                .setPriceTime(priceTime)
                .setType(type)
                .setStatus(status)
                ;
    }
}
