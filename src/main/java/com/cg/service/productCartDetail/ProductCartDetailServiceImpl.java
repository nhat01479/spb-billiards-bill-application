package com.cg.service.productCartDetail;

import com.cg.model.Cart;
import com.cg.model.ProductCartDetail;
import com.cg.model.dto.cart.product.ProductCartDetailResDTO;
import com.cg.repository.ProductCartDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductCartDetailServiceImpl implements IProductCartDetailService {
    @Autowired
    private ProductCartDetailRepository productCartDetailRepository;

    @Override
    public List<ProductCartDetail> findAll() {
        return null;
    }

    @Override
    public List<ProductCartDetail> findAllByCart(Cart cart) {
        return productCartDetailRepository.findAllByCart(cart);
    }

    @Override
    public List<ProductCartDetailResDTO> getAllProductCartDetailResDTO(Cart cart) {
        return productCartDetailRepository.getAllCartDetailItemResDTO(cart);
    }
    @Override
    public ProductCartDetailResDTO getProductCartDetailItemResDTO(Cart cart, Long productId) {
        return productCartDetailRepository.getProductCartDetailItemResDTO(cart, productId);
    }

    @Override
    public Optional<ProductCartDetail> findById(Long id) {

        return productCartDetailRepository.findById(id);
    }

    @Override
    public ProductCartDetail save(ProductCartDetail productCartDetail) {
        return null;
    }

    @Override
    public void delete(ProductCartDetail productCartDetail) {
        productCartDetailRepository.delete(productCartDetail);
    }

    @Override
    public void deleteById(Long id) {

    }


}
