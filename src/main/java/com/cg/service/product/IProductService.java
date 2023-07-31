package com.cg.service.product;


import com.cg.model.Product;
import com.cg.model.dto.product.ProductCreReqDTO;
import com.cg.model.dto.product.ProductDTO;
import com.cg.model.dto.product.ProductUpReqDTO;
import com.cg.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IProductService extends IGeneralService<Product, Long> {
    List<ProductDTO> findAllProductDTO();
    List<ProductDTO> findAllByDeletedFalse(Sort sort);
    Page<ProductDTO> findAllByDeletedFalse(String title, String description, Pageable pageable);
    List<ProductDTO> findAllProductDTOByCategoryId(Long categoryId);
    List<ProductDTO> findAllByDeletedFalseAndTitleLikeAndDescriptionLike(String title, String description);

    Boolean existsByTitle(String title);
    Boolean existsByTitleAndIdNot(String title, Long id);
    ProductDTO create(ProductCreReqDTO productCreReqDTO);
    public void softDelete(Product product);
    ProductDTO update(ProductUpReqDTO productUpReqDTO, Product product);

    List<ProductDTO> findAllByDeletedFalseAndCategoryId(Long categoryId);

}

