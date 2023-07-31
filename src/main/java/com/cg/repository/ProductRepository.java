package com.cg.repository;


import com.cg.model.Product;
import com.cg.model.dto.product.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select new com.cg.model.dto.product.ProductDTO (" +
            "p.id, " +
            "p.title, " +
            "p.price, " +
            "p.unit, " +
            "p.description, " +
            "p.category, " +
            "p.avatar) " +
            "from Product as p where p.deleted=false")
    List<ProductDTO> findAllProductDTO();
    List<ProductDTO> findAllByDeletedFalse(Sort sort);
    @Query("select new com.cg.model.dto.product.ProductDTO (" +
            "p.id, " +
            "p.title, " +
            "p.price, " +
            "p.unit, " +
            "p.description, " +
            "p.category, " +
            "p.avatar) " +
            "from Product as p " +
            "where (p.title like %:title%  or p.description like %:description% ) and p.deleted=false ")
    Page<ProductDTO> findAllByDeletedFalse(String title, String description, Pageable pageable);
    @Query("select new com.cg.model.dto.product.ProductDTO (" +
            "p.id, " +
            "p.title, " +
            "p.price, " +
            "p.unit, " +
            "p.description, " +
            "p.category, " +
            "p.avatar) " +
            "from Product as p " +
            "where (p.title like %:title%  or p.description like %:description% ) and p.deleted=false ")
    List<ProductDTO> findAllByDeletedFalseAndTitleLikeAndDescriptionLike(String title, String description);
    @Query("select new com.cg.model.dto.product.ProductDTO (" +
            "p.id, " +
            "p.title, " +
            "p.price, " +
            "p.unit, " +
            "p.description, " +
            "p.category, " +
            "p.avatar) " +
            "from Product as p where p.deleted=false and p.category.id = :categoryId")
    List<ProductDTO> findAllProductDTOByCategoryId(Long categoryId);
    List<ProductDTO> findAllByDeletedFalseAndCategoryId(Long categoryId);
    Boolean existsByTitle(String title);
    Boolean existsByTitleAndIdNot(String title, Long id);
}
