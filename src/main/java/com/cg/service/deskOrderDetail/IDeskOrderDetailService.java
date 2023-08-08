package com.cg.service.deskOrderDetail;

import com.cg.model.DeskOrderDetail;
import com.cg.model.Order;
import com.cg.model.dto.cart.desk.DeskCartDetailResDTO;
import com.cg.model.dto.order.desk.DeskOrderDetailResDTO;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IDeskOrderDetailService extends IGeneralService<DeskOrderDetail, Long> {
    List<DeskOrderDetailResDTO> getAllDeskOrderDetailDTO(Order order);
}
