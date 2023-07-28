package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.model.Desk;
import com.cg.model.dto.order.OrderReqDTO;
import com.cg.model.dto.order.OrderResDTO;
import com.cg.service.desk.IDeskService;
import com.cg.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderAPI {
    @Autowired
    private IDeskService deskService;
    @Autowired
    private IOrderService orderService;
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderReqDTO orderReqDTO) {
        Desk desk = deskService.findById(orderReqDTO.getDeskId()).orElseThrow(()-> {
            throw new DataInputException("Mã số bàn không tồn tại");
        });
        OrderResDTO orderResDTO = orderService.create(desk);


        return new ResponseEntity<>(orderResDTO, HttpStatus.OK);
    }
}
