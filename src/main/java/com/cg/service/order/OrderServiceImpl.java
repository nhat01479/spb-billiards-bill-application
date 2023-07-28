package com.cg.service.order;

import com.cg.exception.DataInputException;
import com.cg.model.*;
import com.cg.model.dto.order.OrderResDTO;
import com.cg.repository.CartRepository;
import com.cg.repository.DeskRepository;
import com.cg.repository.OrderRepository;
import com.cg.service.cart.ICartService;
import com.cg.service.deskCartDetail.IDeskCartDetailService;
import com.cg.service.deskOrderDetail.IDeskOrderDetailService;
import com.cg.service.productCartDetail.IProductCartDetailService;
import com.cg.service.productOrderDetail.IProductOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class OrderServiceImpl implements IOrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ICartService cartService;
    @Autowired
    private IProductCartDetailService productCartDetailService;
    @Autowired
    IDeskCartDetailService deskCartDetailService;
    @Autowired
    private IProductOrderDetailService productOrderDetailService;
    @Autowired
    private IDeskOrderDetailService deskOrderDetailService;
    @Autowired
    private DeskRepository deskRepository;
    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public OrderResDTO create(Desk desk) {
        Cart cart = cartService.findByDesk(desk.getId(), true).orElseThrow(()-> {
            throw new DataInputException("Cart không tồn tại");
        });
        Order order = cart.toOrder();
        order.setStatus(true);
        orderRepository.save(order);
        desk.setStatus(false);
        deskRepository.save(desk);

        List<ProductOrderDetail> productOrderDetails = new ArrayList<>();
        List<DeskOrderDetail> deskOrderDetails = new ArrayList<>();

        List<ProductCartDetail> productCartDetails = productCartDetailService.findAllByCart(cart);
        List<DeskCartDetail> deskCartDetails = deskCartDetailService.findAllByCart(cart);

        for (ProductCartDetail item: productCartDetails) {
            ProductOrderDetail p = item.toProductOrderDetail(order);
            productOrderDetails.add(p);
            productOrderDetailService.save(p);
        }

        for (DeskCartDetail item: deskCartDetails) {
            DeskOrderDetail d = item.toDeskOrderDetail(order);
            deskOrderDetails.add(d);
            deskOrderDetailService.save(d);
        }

        return order.toOrderResDTO();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Order order) {

    }

    @Override
    public void deleteById(Long id) {

    }


}
