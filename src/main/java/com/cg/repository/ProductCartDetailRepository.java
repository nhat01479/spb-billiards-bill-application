package com.cg.repository;

import com.cg.model.Cart;
import com.cg.model.Product;
import com.cg.model.ProductCartDetail;
import com.cg.model.dto.cart.product.ProductCartDetailResDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductCartDetailRepository extends JpaRepository<ProductCartDetail, Long> {
    Optional<ProductCartDetail> findByCartAndProduct(Cart cart, Product product);

    List<ProductCartDetail> findAllByCart(Cart cart);


    @Query("SELECT NEW com.cg.model.dto.cart.product.ProductCartDetailResDTO (" +
            "pdt.id, " +
            "pdt.product, " +
            "pdt.price, " +
            "pdt.unit, " +
            "pdt.quantity, " +
            "pdt.amount" +
            ") " +
            "FROM ProductCartDetail AS pdt " +
            "WHERE pdt.cart = :cart"
    )
    List<ProductCartDetailResDTO> getAllCartDetailItemResDTO(@Param("cart") Cart cart);
    @Query("SELECT NEW com.cg.model.dto.cart.product.ProductCartDetailResDTO (" +
            "pdt.id, " +
            "pdt.product, " +
            "pdt.price, " +
            "pdt.unit, " +
            "pdt.quantity, " +
            "pdt.amount" +
            ") " +
            "FROM ProductCartDetail AS pdt " +
            "WHERE pdt.cart = :cart and pdt.product.id = :productId"
    )
    ProductCartDetailResDTO getProductCartDetailItemResDTO(@Param("cart") Cart cart, Long productId);
}
