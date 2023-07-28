package com.cg.repository;

import com.cg.model.Desk;
import com.cg.model.ProductOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderDetailRepository extends JpaRepository<ProductOrderDetail, Long> {
}
