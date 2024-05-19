package com.ariamath.shopsmart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@Table(name = "purchase",schema = "public")
@RequiredArgsConstructor
@AllArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;
    private Long stock;
    private Long price;

    public Purchase(ShoppingCart shoppingCart) {
        this.user = shoppingCart.getUser();
        this.price = shoppingCart.getPrice();
        this.product = shoppingCart.getProduct();
        this.stock = shoppingCart.getStock();
    }
}
