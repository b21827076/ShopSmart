package com.ariamath.shopsmart.controller;

import com.ariamath.shopsmart.entity.Rate;
import com.ariamath.shopsmart.request.ProductCreateRequest;
import com.ariamath.shopsmart.request.ProductUpdateRequest;
import com.ariamath.shopsmart.request.RateCreateRequest;
import com.ariamath.shopsmart.request.RateUpdateRequest;
import com.ariamath.shopsmart.response.RateResponse;
import com.ariamath.shopsmart.service.RateService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/rate")
@RequiredArgsConstructor
public class RateController {

    private RateService rateService;

    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping("/{rateId}")
    public ResponseEntity<Rate> getRateByRateId(@PathVariable Long rateId){
        return new ResponseEntity<>(rateService.getRateByRatetId(rateId), HttpStatus.OK);
    }

    @PostMapping()
    public void createNewRate(@RequestBody RateCreateRequest newRateCreateRequest) {
        rateService.createNewRate(newRateCreateRequest);
    }

    @PutMapping("/{rateId}")
    public void updateRate(@PathVariable Long rateId,@RequestBody RateUpdateRequest rateUpdateRequest) {
        rateService.updateRateByProductId(rateId, rateUpdateRequest);
    }

    @DeleteMapping("/{productId}")
    public void deleteRate(@PathVariable Long productId){
        rateService.deleteRateByProductId(productId);
    }
}
