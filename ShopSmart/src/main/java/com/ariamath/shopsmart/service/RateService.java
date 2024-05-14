package com.ariamath.shopsmart.service;

import com.ariamath.shopsmart.entity.Product;
import com.ariamath.shopsmart.entity.Rate;
import com.ariamath.shopsmart.entity.User;
import com.ariamath.shopsmart.repository.ProductRepository;
import com.ariamath.shopsmart.repository.RateRepository;
import com.ariamath.shopsmart.request.RateCreateRequest;
import com.ariamath.shopsmart.request.RateUpdateRequest;
import com.ariamath.shopsmart.response.RateResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class RateService {

    private RateRepository rateRepository;
    private ProductService productService;
    private UserService userService;

    public void createNewRate(RateCreateRequest newRateCreateRequest) {
        Product product = productService.getOneProductById(newRateCreateRequest.getProductId());
        User user = userService.getOneUserById(newRateCreateRequest.getUserId());
        Rate rate = new Rate(newRateCreateRequest.getRate(),product,user);
        rateRepository.save(rate);

        Double newRate = getRateForOneProduct(product.getId());
        product.setRate(newRate);
        productService.saveProduct(product);
    }

    public void updateRateByProductId(Long rateId, RateUpdateRequest rateUpdateRequest) {
        Rate rate = rateRepository.getByRateId(rateId);
        Product product = productService.getOneProductById(rateUpdateRequest.getProductId());
        rate.setRate(rateUpdateRequest.getRate());
        rateRepository.save(rate);

        Double newRate = getRateForOneProduct(product.getId());
        product.setRate(newRate);
        productService.saveProduct(product);
    }

    public void deleteRateByProductId(Long productId) {
    }

    public Double getRateForOneProduct(Long productId){
        List<Rate> rates = rateRepository.getRatesByProductId(productId);
        int total = 0;
        int count = 0;

        for(Rate i : rates){
            total +=i.getRate();
            count++;
        }
        return (double) (total/count) ;
    }

    public Rate getRateByRatetId(Long rateId) {
        return rateRepository.getByRateId(rateId);
    }
}
