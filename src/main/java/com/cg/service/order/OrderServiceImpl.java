package com.cg.service.order;

import com.cg.model.Order;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements IOrderService{
    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Order save(Order order) {
        return null;
    }

    @Override
    public void delete(Order order) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
