package com.example.TBDBackend.services;

import com.example.TBDBackend.dtos.OrderDetailDTO;
import com.example.TBDBackend.entities.OrderDetail;
import com.example.TBDBackend.entities.Order;
import com.example.TBDBackend.entities.Product;
import com.example.TBDBackend.exceptions.EntityNotFoundException;
import com.example.TBDBackend.repositories.OrderDetailRepository;
import com.example.TBDBackend.repositories.OrderRepository;
import com.example.TBDBackend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<OrderDetail> findAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    public OrderDetail findOrderDetailById(String id) {
        Optional<OrderDetail> orderDetailEntity = orderDetailRepository.findById(id);

        if (orderDetailEntity.isEmpty()) {
            throw new EntityNotFoundException("Order Detail Not Found");
        }
        return orderDetailEntity.get();
    }

    public OrderDetail saveOrderDetail(OrderDetailDTO orderDetailDTO) {
        Optional<Order> possibleOrder = orderRepository.findById(orderDetailDTO.getOrderId());

        if (possibleOrder.isEmpty()) {
            throw new EntityNotFoundException("Order Not Found");
        }

        Optional<Product> possibleProduct = productRepository.findById(orderDetailDTO.getProductId());

        if (possibleProduct.isEmpty()) {
            throw new EntityNotFoundException("Product Not Found");
        }

        OrderDetail orderDetail = OrderDetail.builder()
                .order(possibleOrder.get())
                .product(possibleProduct.get())
                .quantity(orderDetailDTO.getQuantity())
                .unitPrice(orderDetailDTO.getUnitPrice())
                .build();
        return orderDetailRepository.save(orderDetail);
    }

    public OrderDetail updateOrderDetail(String id, OrderDetailDTO orderDetailDTO) {
        Optional<OrderDetail> orderDetailEntity = orderDetailRepository.findById(id);

        if (orderDetailEntity.isEmpty()) {
            throw new EntityNotFoundException("Order Detail Not Found");
        }

        Optional<Order> possibleOrder = orderRepository.findById(orderDetailDTO.getOrderId());

        if (possibleOrder.isEmpty()) {
            throw new EntityNotFoundException("Order Not Found");
        }

        Optional<Product> possibleProduct = productRepository.findById(orderDetailDTO.getProductId());

        if (possibleProduct.isEmpty()) {
            throw new EntityNotFoundException("Product Not Found");
        }

        OrderDetail updatedOrderDetail = OrderDetail.builder()
                .id(orderDetailEntity.get().getId())
                .order(possibleOrder.get())
                .product(possibleProduct.get())
                .quantity(orderDetailDTO.getQuantity())
                .unitPrice(orderDetailDTO.getUnitPrice())
                .build();
        return orderDetailRepository.save(updatedOrderDetail);
    }

    public void deleteOrderDetail(String id) {
        if (!orderDetailRepository.existsById(id)) {
            throw new EntityNotFoundException("Order Detail Not Found");
        }
        orderDetailRepository.deleteById(id);
    }


}
