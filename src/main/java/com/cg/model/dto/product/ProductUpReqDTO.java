package com.cg.model.dto.product;


import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.model.ProductAvatar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductUpReqDTO implements Validator{

    private String title;
    private String price;
    private String unit;
    private String description;

    private Long categoryId;

    private MultipartFile avatar;

    public Product toProduct(Product product, String slug, Category category, ProductAvatar productAvatar) {
        return new Product()
                .setId(product.getId())
                .setTitle(title)
                .setSlug(slug)
                .setPrice(BigDecimal.valueOf(Long.parseLong(price)))
                .setUnit(unit)
                .setDescription(description)
                .setCategory(category)
                .setAvatar(productAvatar)
                ;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductUpReqDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductUpReqDTO productUpReqDTO = (ProductUpReqDTO) target;
        String title = productUpReqDTO.getTitle();
        String priceStr = productUpReqDTO.getPrice();
        String unit = productUpReqDTO.getUnit();
//        boolean isExistsAvatar = productUpReqDTO.getAvatar().isEmpty();

        if (title == null || title.trim().length() == 0) {
            errors.rejectValue("title", "title.null", "Tên sản phẩm không được để trống");
        } else {
//            if (!title.matches("([A-Z]+[a-z]*[ ]?)+$")) {
//                errors.rejectValue("title", "title.number", "Tên bắt đầu bằng chữ hoa, không được chứa số và ký tự đặc biệt");
//            } else {
                if (title.trim().length() < 5 || title.trim().length() > 20) {
                    errors.rejectValue("title", "title.length", "Tên sản phẩm từ 5-20 ký tự");
                }
//            }
        }

        if (priceStr == null || priceStr.trim().length() == 0) {
            errors.rejectValue("price", "price.null", "Giá không được để trống");
        } else {
            if (!priceStr.matches("\\d+")) {
                errors.rejectValue("price", "price.regex", "Giá không đúng định dạng");
            } else {
                BigDecimal price = BigDecimal.valueOf(Long.parseLong(priceStr));

                if (price.compareTo(BigDecimal.valueOf(1000)) < 0) {
                    errors.rejectValue("price", "price.min", "Giá tối thiểu là 1.000");
                } else {
                    if (price.compareTo(BigDecimal.valueOf(100000000)) > 0) {
                        errors.rejectValue("price", "price.max", "Giá tối đa là 9.999.999");
                    }
                }
            }
        }

        if (unit == null || unit.trim().length() == 0) {
            errors.rejectValue("unit", "unit.null", "Vui lòng nhập đơn vị tính số lượng");
        } else {
            if (unit.trim().length() < 2 || unit.trim().length() > 10) {
                errors.rejectValue("unit", "unit.length", "Đơn vị tính số lượng từ 2-10 ký tự");
            }
        }
    }
}
