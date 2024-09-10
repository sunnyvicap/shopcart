package com.ecommerce.shopcart.controller;

import com.ecommerce.shopcart.dto.ProductDTO;
import com.ecommerce.shopcart.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO,
                                                    @PathVariable Long categoryId) {

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDTO, categoryId));

    }

    @GetMapping("public/product")
    public ResponseEntity<Page<ProductDTO>> getAllProducts(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getAllProducts(pageable));

    }

    @GetMapping("public/categories/{categoryId}/product")
    public ResponseEntity<Page<ProductDTO>> getProductByCategory(@PageableDefault Pageable pageable,
                                                                 @PathVariable Long categoryId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getProductByCategory(pageable, categoryId));

    }

    @GetMapping("public/product/search")
    public ResponseEntity<Page<ProductDTO>> getProductBySearch(@PageableDefault Pageable pageable,
                                                               @RequestParam String keyword) {
        return ResponseEntity.status(HttpStatus.FOUND).body(productService.findProductBySearch(pageable, keyword));
    }

    @PutMapping("admin/product/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO,
                                                    @PathVariable Long productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProductById(productDTO, productId));
    }

    @DeleteMapping("admin/product/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully productId {} : " + productId);
    }
}
