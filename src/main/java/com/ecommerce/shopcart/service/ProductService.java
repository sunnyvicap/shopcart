package com.ecommerce.shopcart.service;

import com.ecommerce.shopcart.dto.ProductDTO;
import com.ecommerce.shopcart.exceptions.ExceptionConstant;
import com.ecommerce.shopcart.exceptions.ResourceNotFoundException;
import com.ecommerce.shopcart.model.Category;
import com.ecommerce.shopcart.model.Product;
import com.ecommerce.shopcart.repository.CategoryRepository;
import com.ecommerce.shopcart.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;


    public ProductDTO createProduct(ProductDTO productDTO, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found", ":" + categoryId, ""));

        Product product = mapper.map(productDTO, Product.class);

        double specialPrice = (product.getPrice() * product.getDiscount()) / 100;
        specialPrice = product.getPrice() - specialPrice;
        product.setSpecialPrice(specialPrice);

        product.setCategory(category);

        return mapper.map(productRepository.save(product), ProductDTO.class);
    }

    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        List<Product> products = productRepository.findAll(pageable)
                .stream().toList();

        List<ProductDTO> productDTOS = products.stream().map(
                product -> mapper.map(product, ProductDTO.class)
        ).toList();

        return new PageImpl<>(productDTOS);
    }

    public Page<ProductDTO> getProductByCategory(Pageable pageable, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionConstant.CATEGORY_NOT_FOUND, "" + categoryId, ""));

        List<Product> products = productRepository.findByCategory(category);
        List<ProductDTO> productDTOS = products.stream().map(
                product -> mapper.map(product, ProductDTO.class)
        ).toList();

        return new PageImpl<>(productDTOS, pageable, products.size());
    }

    public Page<ProductDTO> findProductBySearch(Pageable pageable, String keyword) {
        List<Product> products = productRepository.findByProductNameLikeIgnoreCase("%" + keyword + "%");
        List<ProductDTO> productDTOS = products.stream().map(
                product -> mapper.map(product, ProductDTO.class)
        ).toList();

        return new PageImpl<>(productDTOS, pageable, products.size());
    }

    public ProductDTO updateProductById(ProductDTO productDTO, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product nor found", "productId:" + productId));

         product = mapper.map(productDTO, Product.class);

        double specialPrice = (product.getPrice() * product.getDiscount()) / 100;
        specialPrice = product.getPrice() - specialPrice;
        product.setSpecialPrice(specialPrice);

        return mapper.map(productRepository.save(product), ProductDTO.class);
    }
}
