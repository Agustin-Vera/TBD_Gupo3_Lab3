package com.example.TBDBackend.services;

import com.example.TBDBackend.dtos.OrderDTO;
import com.example.TBDBackend.entities.OrderEntity;
import com.example.TBDBackend.exceptions.EntityNotFoundException;
import com.example.TBDBackend.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderEntity> findAllOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity findOrderById(String id) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);

        if(orderEntity.isEmpty()) {
            throw new EntityNotFoundException("Order Not Found");
        }
        return orderEntity.get();
    }

    public OrderEntity saveOrder(OrderDTO orderDTO) {
        OrderEntity order = OrderEntity.builder()
                .order_date(orderDTO.getOrder_date())
                .state(orderDTO.getState())
                .client_id(orderDTO.getClient_id())
                .distributor_id(orderDTO.getDistributor_id())
                .total(orderDTO.getTotal())
                .shipping_date(orderDTO.getShipping_date())
                .delivery_location(orderDTO.getDelivery_location())
                .build();
        return orderRepository.save(order);
    }

    public OrderEntity updateOrder(String id, OrderDTO orderDTO) {
        Optional<OrderEntity> order = orderRepository.findById(id);
        if(order.isEmpty()) {
            throw new EntityNotFoundException("Order Not Found");
        }

        OrderEntity updatedOrder = OrderEntity.builder()
                .id(order.get().getId())
                .order_date(orderDTO.getOrder_date())
                .state(orderDTO.getState())
                .client_id(orderDTO.getClient_id())
                .distributor_id(orderDTO.getDistributor_id())
                .total(orderDTO.getTotal())
                .shipping_date(orderDTO.getShipping_date())
                .delivery_location(orderDTO.getDelivery_location())
                .build();
        return orderRepository.save(updatedOrder);
    }

    public void deleteOrder(String id) {
        if(!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Order Not Found");
        }
        orderRepository.deleteById(id);
    }
}
