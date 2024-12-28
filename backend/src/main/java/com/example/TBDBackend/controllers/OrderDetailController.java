package com.example.TBDBackend.controllers;

import com.example.TBDBackend.dtos.OrderDetailDTO;
import com.example.TBDBackend.entities.OrderDetailEntity;
import com.example.TBDBackend.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/ordersDetails")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<List<OrderDetailEntity>> getAllOrderDetails() {
        return new ResponseEntity<>(orderDetailService.findAllOrderDetails(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailEntity> getOrderDetailById(@PathVariable String id) {
        return new ResponseEntity<>(orderDetailService.findOrderDetailById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDetailEntity> postOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        return new ResponseEntity<>(orderDetailService.saveOrderDetail(orderDetailDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetailEntity> putOrderDetail(@PathVariable String id, @RequestBody OrderDetailDTO orderDetailDTO) {
        return new ResponseEntity<>(orderDetailService.updateOrderDetail(id, orderDetailDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteOrderDetail(@PathVariable String id) {
        orderDetailService.deleteOrderDetail(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("success", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}