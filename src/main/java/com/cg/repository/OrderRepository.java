package com.cg.repository;

import com.cg.model.Order;
import com.cg.model.dto.order.OrderResDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Date;
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
    @Query("SELECT NEW com.cg.model.dto.order.OrderResDTO (" +
            "o.id, " +
            "o.totalAmount, " +
            "o.desk," +
            "o.user," +
            "o.createdAt " +
            ") " +
            "FROM Order AS o where o.user.fullName like %:keySearch% or " +
            "o.desk.name like %:keySearch% ")
    List<OrderResDTO> findAllOrderResDTOByKeySearch(String keySearch);
//    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.createdAt >= LocalDateTime.now().minusDays(7)")
//    BigDecimal getTotalAmountInLast7Days(Long quantityDate);

}
