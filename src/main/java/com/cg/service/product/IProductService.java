package com.cg.service.product;


import com.cg.model.Product;
import com.cg.model.dto.ProductCreReqDTO;
import com.cg.service.IGeneralService;

public interface IProductService extends IGeneralService<Product, Long> {

    void create(ProductCreReqDTO productCreReqDTO);
    public void softDelete(Product product);

}

