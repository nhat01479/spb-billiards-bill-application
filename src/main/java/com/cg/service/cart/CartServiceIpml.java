package com.cg.service.cart;

import com.cg.exception.DataInputException;
import com.cg.model.*;
import com.cg.model.dto.cart.desk.DeskCartDetailReqDTO;
import com.cg.model.dto.cart.product.ProductCartDetailReqDTO;
import com.cg.repository.CartRepository;
import com.cg.repository.DeskCartDetailRepository;
import com.cg.repository.ProductCartDetailRepository;
import com.cg.service.desk.IDeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartServiceIpml implements ICartService{
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductCartDetailRepository productCartDetailRepository;
    @Autowired
    private DeskCartDetailRepository deskCartDetailRepository;
    @Autowired
    private IDeskService deskService;

    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Cart> findByUser(User user) {
        return cartRepository.findByUser(user);
    }
    public Optional<Cart> findByDesk(Long deskId, Boolean boo) {
        return cartRepository.findByDesk(deskId, boo);
    }

    @Override
    public Cart addToCart(ProductCartDetailReqDTO productCartDetailReqDTO, Product product, Desk desk, User user) {

        Optional<Cart> cartOptional = cartRepository.findByDesk(desk.getId(), true);
        Cart cart = new Cart();

        if (cartOptional.isEmpty()) {
            cart.setUser(user);
            cart.setTotalAmount(BigDecimal.ZERO);
            cart.setCreatedAt(cartOptional.get().getCreatedAt());
            cartRepository.save(cart);

            BigDecimal price = product.getPrice();
            Long quantity = productCartDetailReqDTO.getQuantity();
            BigDecimal amount = price.multiply(BigDecimal.valueOf(quantity));


            ProductCartDetail productCartDetail = new ProductCartDetail();
            productCartDetail.setCart(cart);
            productCartDetail.setProduct(product);
            productCartDetail.setTitle(product.getTitle());
            productCartDetail.setPrice(product.getPrice());
            productCartDetail.setUnit(product.getUnit());
            productCartDetail.setQuantity(1L);
            productCartDetail.setAmount(amount);
            productCartDetailRepository.save(productCartDetail);

            cart.setTotalAmount(amount);
            cartRepository.save(cart);

        }
        else {
            cart = cartOptional.get();
            Optional<ProductCartDetail> productCartDetailOptional = productCartDetailRepository.findByCartAndProduct(cart, product);

            if (productCartDetailOptional.isEmpty()) {
                BigDecimal price = product.getPrice();
                Long quantity = 1L;
                BigDecimal amount = price.multiply(BigDecimal.valueOf(quantity));

                ProductCartDetail productCartDetail = new ProductCartDetail();
                productCartDetail.setCart(cart);
                productCartDetail.setProduct(product);
                productCartDetail.setTitle(product.getTitle());
                productCartDetail.setPrice(product.getPrice());
                productCartDetail.setUnit(product.getUnit());
                productCartDetail.setQuantity(1L);
                productCartDetail.setAmount(amount);
                productCartDetailRepository.save(productCartDetail);

                List<ProductCartDetail> productCartDetails = productCartDetailRepository.findAllByCart(cart);
                BigDecimal totalAmount = BigDecimal.ZERO;

                for(ProductCartDetail item : productCartDetails) {
                    BigDecimal itemAmount = item.getAmount();
                    totalAmount = totalAmount.add(itemAmount);
                }

                cart.setTotalAmount(totalAmount);
                cartRepository.save(cart);
            }
            else {
                ProductCartDetail productCartDetail = productCartDetailOptional.get();
                long currentQuantity = productCartDetail.getQuantity();
                long quantity = currentQuantity + 1;

                BigDecimal price = product.getPrice();
                BigDecimal amount = price.multiply(BigDecimal.valueOf(quantity));

                productCartDetail.setPrice(price);
                productCartDetail.setQuantity(quantity);
                productCartDetail.setAmount(amount);
                productCartDetailRepository.save(productCartDetail);

                List<ProductCartDetail> productCartDetails = productCartDetailRepository.findAllByCart(cart);
                BigDecimal totalAmount = BigDecimal.ZERO;

                for(ProductCartDetail item : productCartDetails) {
                    BigDecimal itemAmount = item.getAmount();
                    totalAmount = totalAmount.add(itemAmount);
                }

                cart.setTotalAmount(totalAmount);
                cartRepository.save(cart);
            }
        }

        return cart;
    }
    @Override
    public Cart updateCart(ProductCartDetailReqDTO productCartDetailReqDTO,
                            ProductCartDetail productCartDetail) {
        Long cartId = productCartDetail.getCart().getId();
        Cart cart = cartRepository.findById(cartId).orElseThrow(()-> {
            throw new DataInputException("Cart không tồn tại");
        });
        Product product = productCartDetail.getProduct();

//        Long currentQuantity = productCartDetail.getQuantity();
        Long newQuantity = productCartDetailReqDTO.getQuantity();


        BigDecimal price = product.getPrice();
        BigDecimal amount = price.multiply(BigDecimal.valueOf(newQuantity));

        productCartDetail.setPrice(price);
        productCartDetail.setQuantity(newQuantity);
        productCartDetail.setAmount(amount);
        productCartDetailRepository.save(productCartDetail);

        List<ProductCartDetail> productCartDetails = productCartDetailRepository.findAllByCart(cart);
        BigDecimal totalAmount = BigDecimal.ZERO;

        for(ProductCartDetail item : productCartDetails) {
            BigDecimal itemAmount = item.getAmount();
            totalAmount = totalAmount.add(itemAmount);
        }

        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);

        return cart;
    }
    @Override
    public Cart addDeskToCart(DeskCartDetailReqDTO deskCartDetailReqDTO, Desk desk, User user) {

        Optional<Cart> cartOptional = cartRepository.findByDesk(desk.getId(), false);
        Cart cart = new Cart();

        if (cartOptional.isEmpty()) {
            cart.setUser(user);
            cart.setTotalAmount(BigDecimal.ZERO);
            desk.setStatus(true);
            deskService.save(desk);
            cart.setDesk(desk);
            BigDecimal priceTime = desk.getPriceTime();
            cartRepository.save(cart);

            Date starAt = new Date();

            // Lấy số milliseconds giữa hai ngày
//            long millisecondsBetween = endDate.getTime() - startDate.getTime();
//
//            // Chuyển đổi milliseconds thành số phút
//            long minutes = millisecondsBetween / 60000;
//            BigDecimal amount = priceTime.multiply(BigDecimal.valueOf(quantity));

            DeskCartDetail deskCartDetail = new DeskCartDetail();
            deskCartDetail.setCart(cart);


            deskCartDetail.setDesk(desk);

            deskCartDetail.setStartAt(starAt);
            deskCartDetail.setPriceTime(priceTime);
            deskCartDetail.setAmount(BigDecimal.ZERO);
            deskCartDetailRepository.save(deskCartDetail);


        }
        return cart;
    }

    @Override
    public Cart save(Cart cart) {

        return cartRepository.save(cart);
    }

    @Override
    public void delete(Cart cart) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
