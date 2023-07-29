package com.cg.service.cart;

import com.cg.model.*;
import com.cg.model.dto.cart.CartResDTO;
import com.cg.model.dto.cart.desk.DeskCartDetailReqDTO;
import com.cg.model.dto.cart.desk.DeskCartDetailResDTO;
import com.cg.model.dto.cart.product.ProductCartDetailReqDTO;
import com.cg.model.dto.cart.product.ProductCartDetailResDTO;
import com.cg.service.IGeneralService;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICartService extends IGeneralService<Cart, Long> {

    Optional<Cart> findByUser(User user);
    Optional<Cart> findByDesk(Long deskId, Boolean boo);

    Cart addToCart(ProductCartDetailReqDTO productCartDetailReqDTO, Product product, Desk desk, User user);
    Cart addDeskToCart(DeskCartDetailReqDTO deskCartDetailReqDTO, Desk desk, User user);
    Cart updateCart(ProductCartDetailReqDTO productCartDetailReqDTO, ProductCartDetail productCartDetail);
    CartResDTO getCart(Desk desk);
    List<DeskCartDetailResDTO> updateDeskCart(Desk desk);
    List<Cart> findCartByDesk(Long deskId, Boolean boo, Pageable pageable);
}
