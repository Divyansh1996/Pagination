package com.example.Pagination.controller;

import com.example.Pagination.model.Product;
import com.example.Pagination.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRespository userRespository;

    @GetMapping("/api/v1/product/pagenumber/{pagenumber}/pagesize/{pagesize}")
    public ResponseEntity<Page<Product>> getAllProducts(@PathVariable("pagenumber") int pageNumber, @PathVariable("pagesize") int pageSize){
        Pageable pageable = PageRequest.of(0,2,Sort.by("price").ascending());

        Page<Product> products = userRespository.findAll(pageable);
        return ResponseEntity.ok(products);
    }

    @PostMapping("/api/v1/product")
    private String saveProduct(@RequestBody Product product){
        userRespository.save(product);
        return "Product saved successfully";
    }
}
