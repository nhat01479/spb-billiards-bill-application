package com.cg.repository;

import com.cg.model.*;
import com.cg.model.dto.cart.desk.DeskCartDetailResDTO;
import com.cg.model.dto.cart.product.ProductCartDetailResDTO;
import com.cg.model.dto.desk.DeskDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DeskCartDetailRepository extends JpaRepository<DeskCartDetail, Long> {
    Optional<DeskCartDetail> findByCartAndDesk(Cart cart, Desk desk);

    List<DeskCartDetail> findAllByCart(Cart cart);


    @Query("SELECT NEW com.cg.model.dto.cart.desk.DeskCartDetailResDTO (" +
            "des.id, " +
            "des.desk, " +
            "des.priceTime, " +
            "des.unit, " +
            "des.amount, " +
            "des.startAt," +
            "des.endAt" +
            ") " +
            "FROM DeskCartDetail AS des " +
            "WHERE des.cart = :cart"
    )
    List<DeskCartDetailResDTO> getAllCartDetailItemResDTO(@Param("cart") Cart cart);
}
