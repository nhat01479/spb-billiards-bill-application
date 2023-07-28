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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

//    @GetMapping
//    public ResponseEntity<?> getAll(@RequestParam ("kw") String kw, @RequestParam("page") int page,
//                                    @RequestParam(value = "limit", defaultValue = "2") int limit,
//                                    @RequestParam(value = "sort-by", defaultValue = "price") String sortBy,
//                                    @RequestParam(value = "sort", defaultValue = "asc") String sort
//                                    ) {
//
////        List<ProductDTO> productDTOS = productService.findAllProductDTO();
////        List<ProductDTO> productDTOS = productService.findAllByDeletedFalse(Sort.by("price").descending());
//        Pageable sortedBy =
//                PageRequest.of(page-1, limit, Sort.by("price").descending().and(Sort.by("title").descending()));
//
////        Pageable pageable = PageRequest.of(page-1, limit);
//        Page<ProductDTO> productDTOS = productService.findAllByDeletedFalse(sortedBy);
//
//        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
//    }
@GetMapping
public ResponseEntity<?> getAll() {
    List<ProductDTO> productDTOS = productService.findAllProductDTO();

    return new ResponseEntity<>(productDTOS, HttpStatus.OK);
}
    @GetMapping("/search")
    public ResponseEntity<?> getAll(@RequestParam ("keySearch") String keySearch) {
        System.out.println(keySearch);
        keySearch = "%" + keySearch + "%";
//        List<ProductDTO> productDTOS = productService.findAllProductDTO();
        List<ProductDTO> productDTOS = productService.findAllByDeletedFalseAndTitleLikeAndDescriptionLike(keySearch, keySearch);


        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }
    @GetMapping("/sorted")
    public ResponseEntity<?> getAllProductSorted(@RequestParam(value = "sort_by", defaultValue = "price") String sortBy,
                                                 @RequestParam(value = "direction", defaultValue = "asc") String direction ) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        List<ProductDTO> productDTOS = productService.findAllByDeletedFalse(sort);


        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getAllProductByCategory(@PathVariable String categoryId) {
//        List<ProductDTO> productDTOS = productService.findAllProductDTOByCategoryId(Long.valueOf(categoryId));
        List<ProductDTO> productDTOS = productService.findAllByDeletedFalseAndCategoryId(Long.valueOf(categoryId));

        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> addNew(@ModelAttribute ProductCreReqDTO productCreReqDTO, BindingResult bindingResult) {

        new ProductCreReqDTO().validate(productCreReqDTO, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }
        String title = productCreReqDTO.getTitle();
        if (productService.existsByTitle(title)) {
            throw new DataInputException("Tên sản phẩm đã tồn tại");
        }

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
    public ResponseEntity<?> update(@PathVariable String productId, ProductUpReqDTO productUpReqDTO, BindingResult bindingResult) {

        Product product = productService.findById(Long.valueOf(productId)).orElseThrow(() ->
                new DataInputException("Mã sản phẩm không tồn tại"));

        new ProductUpReqDTO().validate(productUpReqDTO, bindingResult);

        if (bindingResult.hasFieldErrors()){
            return appUtils.mapErrorToResponse(bindingResult);
        }

        String title = productUpReqDTO.getTitle();
        if (productService.existsByTitleAndIdNot(title, Long.valueOf(productId))){
            throw new EmailExistsException("Tên sản phẩm đã tồn tại");
        }

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
