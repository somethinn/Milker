package PRM392.demo.service;

import PRM392.demo.dto.OrderDetailDTO;
import PRM392.demo.dto.OrderRequest;
import PRM392.demo.dto.OrderResponse;
import PRM392.demo.model.*;
import PRM392.demo.repo.OrderDetailRepository;
import PRM392.demo.repo.OrderRepository;
import PRM392.demo.repo.ProductRepository;
import PRM392.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {
        User user = userRepository.findById(orderRequest.getCustomerId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setOrderID(UUID.randomUUID().toString());
        order.setMemberID(user);
        order.setShippingAddress(orderRequest.getShippingAddress());
        order.setOrderStatus(false);
        order.setCreateDate(Instant.now());
        order.setUpdateDate(Instant.now());

        BigDecimal total = BigDecimal.ZERO;
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderDetailDTO dto : orderRequest.getOrderDetails()) {
            Product product = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + dto.getProductId()));

            if (product.getQuantity() < dto.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getProductName());
            }

            product.setQuantity(product.getQuantity() - dto.getQuantity());
            product.setStatusDescription(product.getQuantity() == 0 ? "Sold Out" : "Available");
            product.setUpdateDate(Instant.now());
            productRepository.save(product);

            OrderDetail detail = new OrderDetail();
            OrderDetailId id = new OrderDetailId();
            id.setProductID(product.getProductID());
            id.setOrderID(order.getOrderID());
            detail.setId(id);
            detail.setProductID(product);
            detail.setOrderID(order);
            detail.setQuantity(dto.getQuantity());
            detail.setCreateDate(Instant.now());
            detail.setUpdateDate(Instant.now());

            BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(dto.getQuantity()));
            total = total.add(itemTotal);

            orderDetails.add(detail);
        }

        order.setTotal(total);
        orderRepository.save(order);
        orderDetailRepository.saveAll(orderDetails);

        return mapToOrderResponse(order, orderDetails);
    }

    private OrderResponse mapToOrderResponse(Order order, List<OrderDetail> orderDetails) {
        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getOrderID()); // Updated to orderID
        response.setCustomerId(order.getMemberID().getCustomerID()); // Updated to memberID and customerID
        response.setTotal(order.getTotal().doubleValue()); // Convert BigDecimal to double for DTO
        response.setShippingAddress(order.getShippingAddress());
        response.setOrderStatus(order.getOrderStatus());
        response.setOrderDetails(orderDetails.stream().map(this::mapToOrderDetailDTO).collect(Collectors.toList()));
        return response;
    }

    private OrderDetailDTO mapToOrderDetailDTO(OrderDetail detail) {
        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setProductId(detail.getProductID().getProductID()); // Updated to productID
        dto.setQuantity(detail.getQuantity());
        return dto;
    }
}