package com.example.TBDBackend.services;

import com.example.TBDBackend.dtos.OrderDTO;
import com.example.TBDBackend.entities.ClientEntity;
import com.example.TBDBackend.entities.OrderEntity;
import com.example.TBDBackend.exceptions.EntityNotFoundException;
import com.example.TBDBackend.repositories.ClientRepository;
import com.example.TBDBackend.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

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
        Optional<ClientEntity> possibleClient = clientRepository.findById(orderDTO.getClientId());

        if(possibleClient.isEmpty()) {
            throw new EntityNotFoundException("Client Not Found");
        }

        OrderEntity order = OrderEntity.builder()
                .orderDate(orderDTO.getOrderDate())
                .state(orderDTO.getState())
                .distributorId(orderDTO.getDistributorId())
                .total(orderDTO.getTotal())
                .shippingDate(orderDTO.getShippingDate())
                .deliveryLocation(orderDTO.getDeliveryLocation())
                .client(possibleClient.get())
                .build();
        return orderRepository.save(order);
    }

    public OrderEntity updateOrder(String id, OrderDTO orderDTO) {
        Optional<OrderEntity> order = orderRepository.findById(id);
        if(order.isEmpty()) {
            throw new EntityNotFoundException("Order Not Found");
        }

        Optional<ClientEntity> possibleClient = clientRepository.findById(orderDTO.getClientId());

        if(possibleClient.isEmpty()) {
            throw new EntityNotFoundException("Client Not Found");
        }

        OrderEntity updatedOrder = OrderEntity.builder()
                .id(order.get().getId())
                .orderDate(orderDTO.getOrderDate())
                .state(orderDTO.getState())
                .distributorId(orderDTO.getDistributorId())
                .total(orderDTO.getTotal())
                .shippingDate(orderDTO.getShippingDate())
                .deliveryLocation(orderDTO.getDeliveryLocation())
                .client(possibleClient.get())
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
