package com.cg.service.productCartDetail;

import com.cg.model.Cart;
import com.cg.model.ProductCartDetail;
import com.cg.model.dto.cart.product.ProductCartDetailResDTO;
import com.cg.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductCartDetailService extends IGeneralService<ProductCartDetail, Long> {

    List<ProductCartDetail> findAllByCart(Cart cart);

    List<ProductCartDetailResDTO> getAllProductCartDetailResDTO(Cart cart);
    ProductCartDetailResDTO getProductCartDetailItemResDTO(Cart cart, Long productId);
}