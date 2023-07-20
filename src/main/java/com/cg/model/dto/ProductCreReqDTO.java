package com.cg.model.dto;


import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.model.ProductAvatar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductCreReqDTO {

    private String title;
    private BigDecimal price;
    private String unit;
    private String description;

    private Long categoryId;

    private MultipartFile avatar;

    public Product toProduct(String slug, Category category, ProductAvatar productAvatar) {
        return new Product()
                .setId(null)
                .setTitle(title)
                .setSlug(slug)
                .setPrice(price)
                .setUnit(unit)
                .setDescription(description)
                .setCategory(category)
                .setAvatar(productAvatar)

                ;
    }

}
