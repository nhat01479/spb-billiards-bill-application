package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.model.*;
import com.cg.model.dto.cart.CartResDTO;
import com.cg.model.dto.cart.desk.DeskCartDetailReqDTO;
import com.cg.model.dto.cart.desk.DeskCartDetailResDTO;
import com.cg.model.dto.cart.product.ProductCartDetailReqDTO;
import com.cg.model.dto.cart.product.ProductCartDetailResDTO;
import com.cg.service.cart.ICartService;
import com.cg.service.desk.IDeskService;
import com.cg.service.deskCartDetail.IDeskCartDetailService;
import com.cg.service.product.IProductService;
import com.cg.service.productCartDetail.IProductCartDetailService;
import com.cg.service.user.IUserService;
import com.cg.ultis.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartAPI {
    @Autowired
    private ICartService cartService;

    @Autowired
    private IProductCartDetailService productCartDetailService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IProductService productService;
    @Autowired
    private IDeskService deskService;
    @Autowired
    private IDeskCartDetailService deskCartDetailService;

    @Autowired
    private AppUtils appUtils;
//    @GetMapping
//    public ResponseEntity<?> getAllProductCartDetails() {
//        String username = appUtils.getPrincipalUsername();
//
//        Optional<User> userOptional = userService.findByUsername(username);
//
//        Optional<Cart> cartOptional = cartService.findByUser(userOptional.get());
//
//        if (cartOptional.isEmpty()) {
//            return new ResponseEntity<>(null, HttpStatus.OK);
//        }
//
//        List<ProductCartDetailResDTO> cartDetailResDTOS = productCartDetailService.getAllProductCartDetailResDTO(cartOptional.get());
//
//        return new ResponseEntity<>(cartDetailResDTOS, HttpStatus.OK);
//    }
    @GetMapping("/get-cart-by-desk/{deskId}")
    public ResponseEntity<?> getCart(@PathVariable ("deskId") String deskIdStr) {
        Long deskId = Long.parseLong(deskIdStr);
        Desk desk = deskService.findById(deskId).orElseThrow(()-> {
            throw  new DataInputException("Desk không tồn tại");
        });

        CartResDTO cartResDTO = cartService.getCart(desk);

        return new ResponseEntity<>(cartResDTO, HttpStatus.OK);
    }
        @GetMapping("/{deskId}")
    public ResponseEntity<?> getAllProductCartDetails(@PathVariable ("deskId") String deskIdStr) {
        Long deskId = Long.parseLong(deskIdStr);
        Desk desk = deskService.findById(deskId).get();

        if (desk.isStatus()) {
            Optional<Cart> cartOptional = cartService.findByDesk(desk.getId(), true);

            if (cartOptional.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.OK);
            }

            Cart cart = cartOptional.get();

            List<ProductCartDetailResDTO> productCartDetailResDTOS = productCartDetailService.getAllProductCartDetailResDTO(cart);

            return new ResponseEntity<>(productCartDetailResDTOS, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get-desk-cart-details/{deskId}")
    public ResponseEntity<?> getAllDeskCartDetails(@PathVariable ("deskId") String deskIdStr) {

        Long deskId = Long.parseLong(deskIdStr);
        Desk desk = deskService.findById(deskId).get();

        if (desk.isStatus()) {
            Optional<Cart> cartOptional = cartService.findByDesk(desk.getId(), true);

            if (cartOptional.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.OK);
            }

            Cart cart = cartOptional.get();

            List<DeskCartDetailResDTO> deskCartDetailResDTOS = deskCartDetailService.getAllDeskCartDetailResDTO(cart);

            return new ResponseEntity<>(deskCartDetailResDTOS, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add-to-cart/{deskId}")
    public ResponseEntity<?> addToCart(@RequestBody ProductCartDetailReqDTO productCartDetailReqDTO,
                                       @PathVariable ("deskId") String deskIdStr) {

        String username = appUtils.getPrincipalUsername();

        Optional<User> userOptional = userService.findByUsername(username);

        Long deskId = Long.valueOf(deskIdStr);
        Desk desk = deskService.findById(deskId).orElseThrow(() -> {
            throw new DataInputException("Mã số bàn không tồn tại");
        });

        Long productId = productCartDetailReqDTO.getProductId();
        Optional<Product> productOptional = productService.findById(productId);

        if (productOptional.isEmpty()) {
            throw new DataInputException("Product invalid");
        }

        Product product = productOptional.get();

        Cart cart = cartService.addToCart(productCartDetailReqDTO, product, desk, userOptional.get());
        ProductCartDetailResDTO productCartDetailResDTO = productCartDetailService.getProductCartDetailItemResDTO(cart, productId);

//        List<ProductCartDetailResDTO> productCartDetailResDTOS = productCartDetailService.getAllProductCartDetailResDTO(cart);

        return new ResponseEntity<>(productCartDetailResDTO, HttpStatus.OK);
    }


    @PostMapping("/add-desk-to-cart")
    public ResponseEntity<?> addDeskToCart(@RequestBody DeskCartDetailReqDTO deskCartDetailReqDTO) {

        String username = appUtils.getPrincipalUsername();

        Optional<User> userOptional = userService.findByUsername(username);

        Long deskId = deskCartDetailReqDTO.getDeskId();
        Optional<Desk> deskOptional = deskService.findById(deskId);

        if (deskOptional.isEmpty()) {
            throw new DataInputException("Desk invalid");
        }

        Desk desk = deskOptional.get();

        Cart cart = cartService.addDeskToCart(deskCartDetailReqDTO, desk, userOptional.get());

        List<DeskCartDetailResDTO> deskCartDetailResDTOS = deskCartDetailService.getAllDeskCartDetailResDTO(cart);

        return new ResponseEntity<>(deskCartDetailResDTOS, HttpStatus.OK);
    }
    @PatchMapping ("/update-product-detail/{productDetailId}")
    public ResponseEntity<?> updateProductDetail(@RequestBody ProductCartDetailReqDTO productCartDetailReqDTO,
                                                 @PathVariable ("productDetailId") String productDetailIdStr) {
        String username = appUtils.getPrincipalUsername();

        Optional<User> userOptional = userService.findByUsername(username);

        Long productDetailId = Long.valueOf(productDetailIdStr);
        ProductCartDetail productCartDetail = productCartDetailService.findById(productDetailId).orElseThrow(() -> {
            throw new DataInputException("Chi tiết sản phẩm không tồn tại");
        });

        Cart cart = cartService.updateCart(productCartDetailReqDTO, productCartDetail);
        ProductCartDetailResDTO productCartDetailResDTO = productCartDetailService.getProductCartDetailItemResDTO(cart, productCartDetail.getProduct().getId());

        return new ResponseEntity<>(productCartDetailResDTO, HttpStatus.OK);
    }
    @GetMapping("/update-desk-time-detail/{deskId}")
    public ResponseEntity<?> updateDeskTimeDetail(@PathVariable ("deskId") String deskId) {
        Desk desk = deskService.findById(Long.valueOf(deskId)).orElseThrow(() -> {
            throw new DataInputException("Bàn không tồn tại");
        });
        Cart cart = cartService.findByDesk(desk.getId(), true).orElseThrow(() -> {
            throw new DataInputException("Cart không tồn tại");
        });

        List<DeskCartDetailResDTO> deskCartDetailResDTO = cartService.updateDeskCart(desk);

        return new ResponseEntity<>(deskCartDetailResDTO.get(0), HttpStatus.OK);
    }

}
