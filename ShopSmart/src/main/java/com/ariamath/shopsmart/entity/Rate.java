package com.ariamath.shopsmart.entity;

import com.ariamath.shopsmart.request.RateCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "rate",schema = "public")
@NoArgsConstructor
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long rate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable=false)
    private Product product;


    public Rate(Long rate, Product product, User user) {
        this.rate = rate;
        this.product = product;
        this.user = user;
    }

}
