package com.driver.service.impl;

import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Override
    public OrderDto createOrder(OrderDto order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCost(order.getCost());
        orderEntity.setItems(order.getItems());
        orderEntity.setOrderId(order.getOrderId());
        orderEntity.setUserId(order.getUserId());
        orderEntity.setStatus(order.isStatus());
        OrderEntity savedOrsder = orderRepository.save(orderEntity);
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(savedOrsder.getOrderId());
        orderDto.setId(savedOrsder.getId());
        orderDto.setCost(savedOrsder.getCost());
        orderDto.setUserId(savedOrsder.getUserId());
        orderDto.setItems(savedOrsder.getItems());
        orderDto.setStatus(savedOrsder.isStatus());
        return orderDto;
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(orderEntity.getOrderId());
        orderDto.setId(orderEntity.getId());
        orderDto.setCost(orderEntity.getCost());
        orderDto.setUserId(orderEntity.getUserId());
        orderDto.setItems(orderEntity.getItems());
        orderDto.setStatus(orderEntity.isStatus());
        return orderDto;
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        OrderEntity orderEntit = orderRepository.findByOrderId(orderId);
        orderEntit.setCost(order.getCost());
        orderEntit.setItems(order.getItems());
        OrderEntity orderEntity = orderRepository.save(orderEntit);
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(orderEntity.getOrderId());
        orderDto.setId(orderEntity.getId());
        orderDto.setCost(orderEntity.getCost());
        orderDto.setUserId(orderEntity.getUserId());
        orderDto.setItems(orderEntity.getItems());
        orderDto.setStatus(orderEntity.isStatus());
        return orderDto;
    }

    @Override
    public void deleteOrder(String orderId) throws Exception {
        orderRepository.deleteByOrderId(orderId);
    }

    @Override
    public List<OrderDto> getOrders() {
        return null;
    }
}