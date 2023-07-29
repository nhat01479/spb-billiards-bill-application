package com.cg.service.order;

import com.cg.model.Desk;
import com.cg.model.DeskCartDetail;
import com.cg.model.Order;
import com.cg.model.dto.order.OrderResDTO;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IOrderService extends IGeneralService <Order, Long> {
    OrderResDTO create (Desk desk);
    List<OrderResDTO> findAllOrderResDTO();
}
