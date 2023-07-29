package com.cg.repository;

import com.cg.model.Order;
import com.cg.model.dto.order.OrderResDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT NEW com.cg.model.dto.order.OrderResDTO (" +
            "o.id, " +
            "o.totalAmount, " +
            "o.desk," +
            "o.user," +
            "o.createdAt " +
            ") " +
            "FROM Order AS o "
    )
    List<OrderResDTO> findAllOrderResDTO();
}
