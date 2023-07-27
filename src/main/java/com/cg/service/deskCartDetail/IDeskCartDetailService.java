package com.cg.service.deskCartDetail;

import com.cg.model.Cart;
import com.cg.model.DeskCartDetail;
import com.cg.model.ProductCartDetail;
import com.cg.model.dto.cart.desk.DeskCartDetailResDTO;
import com.cg.model.dto.cart.product.ProductCartDetailResDTO;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IDeskCartDetailService extends IGeneralService<DeskCartDetail, Long> {
    List<DeskCartDetail> findAllByCart(Cart cart);

    List<DeskCartDetailResDTO> getAllDeskCartDetailResDTO(Cart cart);

}
