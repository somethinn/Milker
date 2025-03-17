package PRM392.demo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private String productId;
    private String productName;
    private int quantity;
    private BigDecimal price;
    private String description;
    private String statusDescription;
}