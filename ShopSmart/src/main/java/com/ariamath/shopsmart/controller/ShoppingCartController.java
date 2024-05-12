package com.ariamath.shopsmart.controller;

import com.ariamath.shopsmart.entity.ShoppingCart;
import com.ariamath.shopsmart.request.ShoppingCartCreateRequest;
import com.ariamath.shopsmart.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/add")
    public void addToCart(@RequestBody ShoppingCartCreateRequest shoppingCart) {
        shoppingCartService.addToCart(shoppingCart);
    }

    @DeleteMapping("/remove/{itemId}")
    public void removeFromCart(@PathVariable Long itemId) {
        shoppingCartService.removeFromCart(itemId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ShoppingCart>> getCartItems(@PathVariable Long userId) {
        return new ResponseEntity<>(shoppingCartService.getShoppingCarts(userId), HttpStatus.OK);
    }

    @DeleteMapping("/buy/{userId}")
    public void buyShoppingCarts(@PathVariable Long userId){
        shoppingCartService.buyShoppingCarts(userId);
    }

}
