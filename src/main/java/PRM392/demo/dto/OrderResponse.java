package PRM392.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {
    private String orderId;
    private String customerId;
    private double total;
    private String shippingAddress;
    private boolean orderStatus;
    private List<OrderDetailDTO> orderDetails;
}