package com.cg.model.dto.product;

import com.cg.model.Category;
import com.cg.model.ProductAvatar;
import com.cg.model.dto.category.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ProductDTO {
    private Long id;
    private String title;
    private BigDecimal price;
    private String unit;
    private String description;
    private CategoryDTO category;
    private ProductAvatarDTO avatar;

    public ProductDTO(Long id, String title, BigDecimal price, String unit, String description, Category category, ProductAvatar avatar) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.unit = unit;
        this.description = description;
        this.category = category.toCategoryDTO();
        this.avatar = avatar.toProductAvatarDTO();
    }
}
