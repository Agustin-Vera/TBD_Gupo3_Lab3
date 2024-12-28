package com.example.TBDBackend.services;

import com.example.TBDBackend.dtos.OrderDTO;
import com.example.TBDBackend.entities.Client;
import com.example.TBDBackend.entities.Order;
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

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public Order findOrderById(String id) {
        Optional<Order> orderEntity = orderRepository.findById(id);

        if(orderEntity.isEmpty()) {
            throw new EntityNotFoundException("Order Not Found");
        }
        return orderEntity.get();
    }

    public Order saveOrder(OrderDTO orderDTO) {
        Optional<Client> possibleClient = clientRepository.findById(orderDTO.getClientId());

        if(possibleClient.isEmpty()) {
            throw new EntityNotFoundException("Client Not Found");
        }

        Order order = Order.builder()
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

    public Order updateOrder(String id, OrderDTO orderDTO) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isEmpty()) {
            throw new EntityNotFoundException("Order Not Found");
        }

        Optional<Client> possibleClient = clientRepository.findById(orderDTO.getClientId());

        if(possibleClient.isEmpty()) {
            throw new EntityNotFoundException("Client Not Found");
        }

        Order updatedOrder = Order.builder()
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
