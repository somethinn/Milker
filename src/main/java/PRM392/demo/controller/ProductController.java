package PRM392.demo.controller;


import PRM392.demo.dto.ProductDTO;
import PRM392.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAvailableProducts() {
        try {
            List<ProductDTO> products = productService.getAvailableProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
