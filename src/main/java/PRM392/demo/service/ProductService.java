package PRM392.demo.service;

import PRM392.demo.dto.ProductCreateRequest;
import PRM392.demo.dto.ProductDTO;
import PRM392.demo.dto.ProductUpdateRequest;
import PRM392.demo.model.Category;
import PRM392.demo.model.Product;
import PRM392.demo.repo.CategoryRepository;
import PRM392.demo.repo.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public ProductDTO createProduct(ProductCreateRequest request) {
        Product product = new Product();
        product.setProductID(UUID.randomUUID().toString());
        product.setProductName(request.getProductName());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setStatusDescription(request.getQuantity() > 0 ? "Available" : "Sold Out");
        product.setImage(request.getImage());
        product.setCreateDate(Instant.now());
        product.setUpdateDate(Instant.now());

        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategoriesID(category);
        }

        Product savedProduct = productRepository.save(product);
        return mapToProductDTO(savedProduct);
    }

    public ProductDTO getProductById(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapToProductDTO(product);
    }

    public Page<ProductDTO> getAvailableProducts(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Product> products = productRepository.findByQuantityGreaterThan(0, pageable);
        return products.map(this::mapToProductDTO);
    }

    public Page<ProductDTO> getAllProducts(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(this::mapToProductDTO);
    }

    @Transactional
    public ProductDTO updateProduct(String productId, ProductUpdateRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (request.getProductName() != null) product.setProductName(request.getProductName());
        if (request.getQuantity() != null) {
            product.setQuantity(request.getQuantity());
            product.setStatusDescription(request.getQuantity() > 0 ? "Available" : "Sold Out");
        }
        if (request.getPrice() != null) product.setPrice(request.getPrice());
        if (request.getDescription() != null) product.setDescription(request.getDescription());
        if (request.getStatusDescription() != null) product.setStatusDescription(request.getStatusDescription());
        if (request.getImage() != null) product.setImage(request.getImage());
        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategoriesID(category);
        }
        product.setUpdateDate(Instant.now());

        Product updatedProduct = productRepository.save(product);
        return mapToProductDTO(updatedProduct);
    }

    @Transactional
    public void deleteProduct(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }

    private ProductDTO mapToProductDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getProductID());
        dto.setProductName(product.getProductName());
        dto.setQuantity(product.getQuantity());
        dto.setPrice(product.getPrice());
        dto.setImage(product.getImage());
        dto.setDescription(product.getDescription());
        dto.setStatusDescription(product.getStatusDescription());
        return dto;
    }
}
