package PRM392.demo.dto;

import lombok.Data;

@Data
public class OrderUpdateRequest {
    private String shippingAddress;
    private String status;
}
