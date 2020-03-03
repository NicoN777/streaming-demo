package com.github.nicon777.restkafkaproductviewproducer.resource;


import com.github.nicon777.restkafkaproductviewproducer.model.Product;
import com.github.nicon777.restkafkaproductviewproducer.service.ProductViewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductViewResource {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProductViewService productViewService;

    @Autowired
    ProductViewResource(ProductViewService productViewService){
        this.productViewService = productViewService;
    }

    @PostMapping("/product-view")
    public ResponseEntity addProductView(@RequestHeader HttpHeaders httpHeaders,
                                         @RequestBody Product product){
        try {
            productViewService.addProductView(product);
        }catch (Exception e){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/product-views")
    public ResponseEntity addProductViews(@RequestBody List<Product> products){
        try{
            productViewService.addProductViews(products);
        }catch (Exception e){

        }
        return ResponseEntity.ok().build();
    }
}
