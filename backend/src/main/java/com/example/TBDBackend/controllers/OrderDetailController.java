package com.example.TBDBackend.controllers;

import com.example.TBDBackend.dtos.OrderDetailDTO;
import com.example.TBDBackend.models.Order;
import com.example.TBDBackend.models.OrderDetail;
import com.example.TBDBackend.services.OrderDetailService;
import com.example.TBDBackend.services.OrderService;
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

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDetail>> getAllOrderDetails() {
        return new ResponseEntity<>(orderDetailService.findAllOrderDetails(), HttpStatus.OK);
    }

    //order detail for order
    @GetMapping("/order/{idOrder}")
    public ResponseEntity<List<OrderDetail>> getOrderDetailByIdOrder(@PathVariable String idOrder){

        return new ResponseEntity<>(orderDetailService.getOrderDetailsByOrder(idOrder), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable String id) {
        return new ResponseEntity<>(orderDetailService.findOrderDetailById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDetail> postOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        return new ResponseEntity<>(orderDetailService.saveOrderDetail(orderDetailDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetail> putOrderDetail(@PathVariable String id, @RequestBody OrderDetailDTO orderDetailDTO) {
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
