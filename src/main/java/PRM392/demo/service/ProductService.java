package PRM392.demo.service;

import PRM392.demo.dto.ProductDTO;
import PRM392.demo.model.Product;
import PRM392.demo.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAvailableProducts() {
        List<Product> products = productRepository.findByQuantityGreaterThan(0);
        return products.stream().map(this::mapToProductDTO).collect(Collectors.toList());
    }

    private ProductDTO mapToProductDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getProductID());
        dto.setProductName(product.getProductName());
        dto.setQuantity(product.getQuantity());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setStatusDescription(product.getStatusDescription());
        return dto;
    }
}
