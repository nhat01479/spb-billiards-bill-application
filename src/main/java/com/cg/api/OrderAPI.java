package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.model.*;
import com.cg.model.dto.order.OrderReqDTO;
import com.cg.model.dto.order.OrderResDTO;
import com.cg.model.dto.order.desk.DeskOrderDetailResDTO;
import com.cg.model.dto.order.product.ProductOrderDetailResDTO;
import com.cg.model.dto.product.ProductDTO;
import com.cg.service.desk.IDeskService;
import com.cg.service.deskOrderDetail.IDeskOrderDetailService;
import com.cg.service.order.IOrderService;
import com.cg.service.productOrderDetail.IProductOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderAPI {
    @Autowired
    private IDeskService deskService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IProductOrderDetailService productOrderDetailService;
    @Autowired
    private IDeskOrderDetailService deskOrderDetailService;
    @GetMapping
    public ResponseEntity<?> getAllOrder() {

        List<OrderResDTO> orderResDTO = orderService.findAllOrderResDTO();

        return new ResponseEntity<>(orderResDTO, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<?> getAllOrderByKeySearch(@RequestParam("keySearch") String keySearch) {
        keySearch = "%" + keySearch + "%";

        List<OrderResDTO> orderResDTOS = orderService.findAllOrderResDTOByKeySearch(keySearch);

        return new ResponseEntity<>(orderResDTOS, HttpStatus.OK);
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable ("orderId") String orderIdStr) {

        Order order = orderService.findById(Long.valueOf(orderIdStr)).orElseThrow(() ->
                new DataInputException("Mã Order không tồn tại"));

        OrderResDTO orderResDTO = order.toOrderResDTO();

        return new ResponseEntity<>(orderResDTO, HttpStatus.OK);
    }
    @GetMapping("/get-product-order-item/{orderId}")
    public ResponseEntity<?> getProductOrderItem(@PathVariable ("orderId") String orderIdStr) {

        Order order = orderService.findById(Long.valueOf(orderIdStr)).orElseThrow(() ->
                new DataInputException("Mã Order không tồn tại"));

        List<ProductOrderDetailResDTO> productOrderDetailResDTOS = productOrderDetailService.getAllProductOrderDetailDTO(order);

        return new ResponseEntity<>(productOrderDetailResDTOS, HttpStatus.OK);
    }
    @GetMapping("/get-desk-order-item/{orderId}")
    public ResponseEntity<?> getDeskOrderItem(@PathVariable ("orderId") String orderIdStr) {

        Order order = orderService.findById(Long.valueOf(orderIdStr)).orElseThrow(() ->
                new DataInputException("Mã Order không tồn tại"));

        DeskOrderDetailResDTO deskOrderDetailResDTO = deskOrderDetailService.getAllDeskOrderDetailDTO(order).get(0);

        return new ResponseEntity<>(deskOrderDetailResDTO, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderReqDTO orderReqDTO) {
        Desk desk = deskService.findById(orderReqDTO.getDeskId()).orElseThrow(()-> {
            throw new DataInputException("Mã số bàn không tồn tại");
        });
        orderService.create(desk);


        return new ResponseEntity<>(desk, HttpStatus.OK);
    }
}
