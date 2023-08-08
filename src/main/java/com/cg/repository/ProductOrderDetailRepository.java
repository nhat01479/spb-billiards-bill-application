package com.cg.repository;

import com.cg.model.Desk;
import com.cg.model.Order;
import com.cg.model.ProductOrderDetail;
import com.cg.model.dto.order.product.ProductOrderDetailResDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductOrderDetailRepository extends JpaRepository<ProductOrderDetail, Long> {
    @Query("SELECT NEW com.cg.model.dto.order.product.ProductOrderDetailResDTO (" +
            "pdt.id, " +
            "pdt.product, " +
            "pdt.price, " +
            "pdt.unit, " +
            "pdt.quantity, " +
            "pdt.amount" +
            ") " +
            "FROM ProductOrderDetail AS pdt " +
            "WHERE pdt.order = :order"
    )
    List<ProductOrderDetailResDTO> getAllProductOrderDetailDTO(Order order);

}
