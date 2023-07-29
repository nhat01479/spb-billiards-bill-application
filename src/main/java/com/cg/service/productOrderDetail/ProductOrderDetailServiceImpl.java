package com.cg.service.productOrderDetail;

import com.cg.model.Order;
import com.cg.model.ProductOrderDetail;
import com.cg.model.dto.order.product.ProductOrderDetailResDTO;
import com.cg.repository.ProductOrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class ProductOrderDetailServiceImpl implements IProductOrderDetailService{
    @Autowired
    private ProductOrderDetailRepository productOrderDetailRepository;
    @Override
    public List<ProductOrderDetail> findAll() {
        return null;
    }

    @Override
    public Optional<ProductOrderDetail> findById(Long id) {
        return productOrderDetailRepository.findById(id);
    }

    @Override
    public ProductOrderDetail save(ProductOrderDetail productOrderDetail) {
        return productOrderDetailRepository.save(productOrderDetail);
    }

    @Override
    public void delete(ProductOrderDetail productOrderDetail) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<ProductOrderDetailResDTO> getAllProductOrderDetailDTO(Order order) {

        return productOrderDetailRepository.getAllProductOrderDetailDTO(order);
    }
}
