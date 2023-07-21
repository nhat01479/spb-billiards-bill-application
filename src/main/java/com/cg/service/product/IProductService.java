package com.cg.service.product;


import com.cg.model.Product;
import com.cg.model.dto.product.ProductCreReqDTO;
import com.cg.model.dto.product.ProductDTO;
import com.cg.model.dto.product.ProductUpReqDTO;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IProductService extends IGeneralService<Product, Long> {
    List<ProductDTO> findAllProductDTO();
    ProductDTO create(ProductCreReqDTO productCreReqDTO);
    public void softDelete(Product product);
    ProductDTO update(ProductUpReqDTO productUpReqDTO, Product product);



}

