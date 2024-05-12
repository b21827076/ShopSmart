package com.ariamath.shopsmart.service;

import com.ariamath.shopsmart.entity.Product;
import com.ariamath.shopsmart.entity.ShoppingCart;
import com.ariamath.shopsmart.entity.User;
import com.ariamath.shopsmart.repository.ShoppingCartRepository;
import com.ariamath.shopsmart.request.ShoppingCartCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {
    private UserService userService;
    private ProductService productService;
    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(UserService userService, ProductService productService, ShoppingCartRepository shoppingCartRepository) {
        this.userService = userService;
        this.productService = productService;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public void addToCart(ShoppingCartCreateRequest shoppingCartRequest) {
        ShoppingCart temp_shoppingCart = new ShoppingCart();
        User temp_user = userService.getOneUserById(shoppingCartRequest.getUserId());
        Product temp_product = productService.getOneProductById(shoppingCartRequest.getProductId());

        temp_shoppingCart.setUser(temp_user);
        temp_shoppingCart.setProduct(temp_product);
        temp_shoppingCart.setStock(shoppingCartRequest.getStock());
        temp_shoppingCart.setPrice(shoppingCartRequest.getPrice());

        shoppingCartRepository.save(temp_shoppingCart);
    }

    public void removeFromCart(Long shoppingCartId) {
        ShoppingCart shoppingCart = shoppingCartRepository.getById(shoppingCartId);
        shoppingCartRepository.delete(shoppingCart);
    }

    public List<ShoppingCart> getShoppingCarts(Long userId) {
        return shoppingCartRepository.findAllByUserId(userId);
    }

    public void buyShoppingCarts(Long userId) {
        List<ShoppingCart> alinacaklar = shoppingCartRepository.findAllByUserId(userId);
        shoppingCartRepository.deleteAll(alinacaklar);
    }
}
