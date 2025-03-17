package PRM392.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private String customerId;
    private String shippingAddress;
    private List<OrderDetailDTO> orderDetails;
}