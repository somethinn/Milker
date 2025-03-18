package PRM392.demo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateRequest {
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private String description;
    private String categoryId;
    private String statusDescription;
    private String image;
}
