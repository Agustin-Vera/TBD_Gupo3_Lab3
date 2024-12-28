package com.example.TBDBackend.services;

import com.example.TBDBackend.dtos.OrderDetailDTO;
import com.example.TBDBackend.entities.OrderDetailEntity;
import com.example.TBDBackend.exceptions.EntityNotFoundException;
import com.example.TBDBackend.repositories.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public List<OrderDetailEntity> findAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    public OrderDetailEntity findOrderDetailById(String id) {
        Optional<OrderDetailEntity> orderDetailEntity = orderDetailRepository.findById(id);

        if (orderDetailEntity.isEmpty()) {
            throw new EntityNotFoundException("Order Detail Not Found");
        }
        return orderDetailEntity.get();
    }

    public OrderDetailEntity saveOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetailEntity orderDetail = OrderDetailEntity.builder()
                .order_id(orderDetailDTO.getOrder_id())
                .product_id(orderDetailDTO.getProduct_id())
                .quantity(orderDetailDTO.getQuantity())
                .unit_price(orderDetailDTO.getUnit_price())
                .build();
        return orderDetailRepository.save(orderDetail);
    }

    public OrderDetailEntity updateOrderDetail(String id, OrderDetailDTO orderDetailDTO) {
        Optional<OrderDetailEntity> orderDetailEntity = orderDetailRepository.findById(id);

        if (orderDetailEntity.isEmpty()) {
            throw new EntityNotFoundException("Order Detail Not Found");
        }
        OrderDetailEntity updatedOrderDetail = OrderDetailEntity.builder()
                .id(orderDetailEntity.get().getId())
                .order_id(orderDetailDTO.getOrder_id())
                .product_id(orderDetailDTO.getProduct_id())
                .quantity(orderDetailDTO.getQuantity())
                .unit_price(orderDetailDTO.getUnit_price())
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
