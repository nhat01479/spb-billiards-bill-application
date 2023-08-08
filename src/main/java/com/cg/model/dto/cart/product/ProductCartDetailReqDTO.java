package com.cg.model.dto.cart.product;

import com.cg.model.dto.desk.DeskCreReqDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCartDetailReqDTO implements Validator {

    private Long productId;

    private Long quantity;

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductCartDetailReqDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductCartDetailReqDTO productCartDetailReqDTO = (ProductCartDetailReqDTO) target;
            String quantityStr = String.valueOf(productCartDetailReqDTO.getQuantity());
            if (quantityStr == null || quantityStr.length() == 0) {
                errors.rejectValue("quantity", "quantity.length", "Vui lòng nhập số lượng");
            } else {
                if (!quantityStr.matches("\\d+")) {
                    errors.rejectValue("quantity", "quantity.matches", "Vui lòng nhập giá trị tiền bằng số");
                } else {
                    Long quantity = Long.parseLong(quantityStr);
                    if (quantity <= 0 || quantity > 100) {
                        errors.rejectValue("quantity", "quantity.minmax", "Số lượng từ 1 - 100");
                    }
                }
            }
    }
}
