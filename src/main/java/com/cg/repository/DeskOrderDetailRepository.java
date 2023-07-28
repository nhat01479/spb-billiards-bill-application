package com.cg.repository;

import com.cg.model.Desk;
import com.cg.model.DeskOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeskOrderDetailRepository extends JpaRepository<DeskOrderDetail, Long> {
}
