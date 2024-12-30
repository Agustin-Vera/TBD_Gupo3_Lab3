package com.example.TBDBackend.services;

import com.example.TBDBackend.dtos.WishListDTO;
import com.example.TBDBackend.exceptions.EntityNotFoundException;
import com.example.TBDBackend.models.Product;
import com.example.TBDBackend.models.WishList;
import com.example.TBDBackend.repositories.ProductRepository;
import com.example.TBDBackend.repositories.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<WishList> findAllWishLists() {
        return wishListRepository.findAll();
    }

    public WishList findWishListById(String id) {
        Optional<WishList> wishList = wishListRepository.findById(id);

        if (wishList.isEmpty()) {
            throw new EntityNotFoundException("Wish List not found");
        }

        return wishList.get();
    }

    public WishList saveWishList(WishListDTO wishListDTO) {

        List<String> productsIds = wishListDTO.getProductsId();
        List<Product> products = new ArrayList<>();

        for(String productId : productsIds) {
            Optional<Product> product = productRepository.findById(productId);
            if(product.isEmpty()) {
                throw new EntityNotFoundException("Product not found");
            }
            products.add(product.get());
        }

        WishList wishList = WishList.builder()
                .products(products)
                .build();
        return wishListRepository.save(wishList);
    }

    public WishList updateWishList(String id, WishListDTO wishListDTO) {
        Optional<WishList> possibleWishList = wishListRepository.findById(id);
        if (possibleWishList.isEmpty()) {
            throw new EntityNotFoundException("Wish List not found");
        }

        List<String> productsIds = wishListDTO.getProductsId();
        List<Product> products = new ArrayList<>();

        for(String productId : productsIds) {
            Optional<Product> product = productRepository.findById(productId);
            if(product.isEmpty()) {
                throw new EntityNotFoundException("Product not found");
            }
            products.add(product.get());
        }
        WishList updatedWishList = WishList.builder()
                .id(possibleWishList.get().getId())
                .products(products)
                .build();
        return wishListRepository.save(updatedWishList);
    }

    public void deleteWishList(String id) {
        if(!wishListRepository.existsById(id)) {
            throw new EntityNotFoundException("Wish List not found");
        }
        wishListRepository.deleteById(id);
    }

}
