package com.cg.api;


import com.cg.exception.DataInputException;
import com.cg.exception.EmailExistsException;
import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.model.dto.product.ProductCreReqDTO;
import com.cg.model.dto.product.ProductDTO;
import com.cg.model.dto.product.ProductUpReqDTO;
import com.cg.service.category.ICategoryService;
import com.cg.service.product.IProductService;
import com.cg.ultis.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductAPI {

    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<ProductDTO> productDTOS = productService.findAllProductDTO();

        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> addNew(@ModelAttribute ProductCreReqDTO productCreReqDTO) {

        ProductDTO productDTO = productService.create(productCreReqDTO);

        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable String productId) {

        Product product = productService.findById(Long.valueOf(productId)).orElseThrow(() ->
                new DataInputException("Mã sản phẩm không tồn tại"));

        ProductDTO productDTO = product.toProductDTO();

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<?> update(@PathVariable String productId, @ModelAttribute ProductUpReqDTO productUpReqDTO, BindingResult bindingResult) {

        Product product = productService.findById(Long.valueOf(productId)).orElseThrow(() ->
                new DataInputException("Mã sản phẩm không tồn tại"));

        new ProductUpReqDTO().validate(productUpReqDTO, bindingResult);

        if (bindingResult.hasFieldErrors()){
            return appUtils.mapErrorToResponse(bindingResult);
        }

//        String title = productCreReqDTO.getTitle();
//        if (true){
//            throw new EmailExistsException("Email đã tồn tại");
//        }

        ProductDTO productDTO = productService.update(productUpReqDTO, product);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);

    }

    @GetMapping("/get-all-category")
    public ResponseEntity<?> getAllCategory() {
        List<Category> categories = categoryService.findAll();

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> delete(@PathVariable String productId) {
        Product product = productService.findById(Long.valueOf(productId)).orElseThrow(() ->
                new DataInputException("Mã sản phẩm không tồn tại"));

        productService.softDelete(product);


        return new ResponseEntity<>(product.toProductDTO(), HttpStatus.OK);

    }
}
