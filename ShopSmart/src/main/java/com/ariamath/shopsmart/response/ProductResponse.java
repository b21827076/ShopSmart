package com.ariamath.shopsmart.response;
import com.ariamath.shopsmart.entity.Product;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class ProductResponse {
    Long id;
    Long userId;
    String userName;
    String productName;
    String text;
    String imageUrl;
    Long stock;
    Long price;
    List<LikeResponse> productLikes;
    List<CommentResponse> productComments;
    Long likeCount;
    Long commentCount;

    public ProductResponse(Product product,
                                 List<LikeResponse> productLikes) {

        this.id = product.getId();
        this.userId = product.getUser().getId();
        this.userName = product.getUser().getUser_name();
        this.productName = product.getName();
        this.text = product.getDescription();
        this.stock = product.getStock();
        this.price = product.getPrice();
        this.productLikes = productLikes;
        this.imageUrl = product.getImg_url();
    }

    public ProductResponse(Product product,
                                 List<LikeResponse> productLikes,
                                 List<CommentResponse> productComments) {
        this.id = product.getId();
        this.userId = product.getUser().getId();
        this.userName = product.getUser().getUser_name();
        this.productName = product.getName();
        this.text = product.getDescription();
        this.stock = product.getStock();
        this.price = product.getPrice();
        this.productLikes = productLikes;
        this.productComments = productComments;


    }

    public ProductResponse(Optional<Product> Product) {
        this.id = Product.get().getId();
        this.userId = Product.get().getUser().getId();
        this.userName = Product.get().getUser().getUser_name();
        this.productName = Product.get().getName();
        this.text= Product.get().getDescription();
        this.stock = Product.get().getStock();
        this.price = Product.get().getPrice();
    }

    public ProductResponse(Product p, Long likeCount, Long commentCount) {
        this.id = p.getId();
        this.userId = p.getUser().getId();
        this.userName = p.getUser().getUser_name();
        this.productName = p.getName();
        this.text = p.getDescription();
        this.stock = p.getStock();
        this.price = p.getPrice();
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.imageUrl = p.getImg_url();
    }
}