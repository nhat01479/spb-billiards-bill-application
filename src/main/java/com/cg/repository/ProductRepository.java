package com.cg.repository;


import com.cg.model.Product;
import com.cg.model.dto.product.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select new com.cg.model.dto.product.ProductDTO (p.id, p.title, p.price, p.unit, p. description, p.category, p.avatar) from Product as p")
    List<ProductDTO> findAllProductDTO();
}
