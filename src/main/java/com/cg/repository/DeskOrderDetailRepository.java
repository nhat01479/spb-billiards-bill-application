package com.cg.repository;


import com.cg.model.DeskOrderDetail;
import com.cg.model.Order;
import com.cg.model.dto.order.desk.DeskOrderDetailResDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeskOrderDetailRepository extends JpaRepository<DeskOrderDetail, Long> {
    @Query("SELECT NEW com.cg.model.dto.order.desk.DeskOrderDetailResDTO (" +
            "des.id, " +
            "des.desk, " +
            "des.priceTime, " +
            "des.unit, " +
            "des.amount, " +
            "des.startAt," +
            "des.endAt" +
            ") " +
            "FROM DeskOrderDetail AS des " +
            "WHERE des.order = :order"
    )
    List<DeskOrderDetailResDTO> getAllDeskOrderDetailDTO(@Param ("order") Order order);
}
