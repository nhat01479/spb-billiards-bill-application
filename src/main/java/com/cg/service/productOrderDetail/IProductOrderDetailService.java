package com.cg.service.productOrderDetail;

import com.cg.model.Order;
import com.cg.model.ProductOrderDetail;
import com.cg.model.dto.order.product.ProductOrderDetailResDTO;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IProductOrderDetailService extends IGeneralService<ProductOrderDetail, Long> {
    List<ProductOrderDetailResDTO> getAllProductOrderDetailDTO(Order order);
}
