package com.example.TBDBackend.controllers;

import com.example.TBDBackend.dtos.WishListDTO;
import com.example.TBDBackend.models.WishList;
import com.example.TBDBackend.services.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/wishList")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    @GetMapping
    public ResponseEntity<List<WishList>> getAllWishList() {
        return new ResponseEntity<>(wishListService.findAllWishLists(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishList> getWishListById(@PathVariable String id) {
        return new ResponseEntity<>(wishListService.findWishListById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WishList> createWishList(@RequestBody WishListDTO wishListDTO) {
        return new ResponseEntity<>(wishListService.saveWishList(wishListDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WishList> updateWishList(@PathVariable String id, @RequestBody WishListDTO wishListDTO) {
        return new ResponseEntity<>(wishListService.updateWishList(id, wishListDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteWishList(@PathVariable String id) {
        wishListService.deleteWishList(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("success", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
