package PRM392.demo.service;

import PRM392.demo.dto.OrderDetailDTO;
import PRM392.demo.dto.OrderRequest;
import PRM392.demo.dto.OrderResponse;
import PRM392.demo.dto.OrderUpdateRequest;
import PRM392.demo.model.Order;
import PRM392.demo.model.OrderDetail;
import PRM392.demo.model.Product;
import PRM392.demo.model.User;
import PRM392.demo.repo.OrderDetailRepository;
import PRM392.demo.repo.OrderRepository;
import PRM392.demo.repo.ProductRepository;
import PRM392.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        order.setOrderId(UUID.randomUUID().toString());
        order.setMember(user);
        order.setShippingAddress(orderRequest.getShippingAddress());
        order.setStatus("CREATED");
        order.setOrderDate(Instant.now());

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
            String id = UUID.randomUUID().toString();
            detail.setOrderDetailId(id);
            detail.setProduct(product);
            detail.setOrder(order);
            detail.setQuantity(dto.getQuantity());

            BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(dto.getQuantity()));
            total = total.add(itemTotal);

            orderDetails.add(detail);
        }

        order.setTotal(total);
        orderRepository.save(order);
        orderDetailRepository.saveAll(orderDetails);

        return mapToOrderResponse(order, orderDetails);
    }

    public OrderResponse getOrderById(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderOrderId(orderId);
        return mapToOrderResponse(order, orderDetails);
    }

    public Page<OrderResponse> getAllOrders(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Order> orders = orderRepository.findAll(pageable);
        return orders.map(order -> {
            List<OrderDetail> details = orderDetailRepository.findByOrderOrderId(order.getOrderId());
            return mapToOrderResponse(order, details);
        });
    }

    @Transactional
    public OrderResponse updateOrder(String orderId, OrderUpdateRequest updateRequest) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (updateRequest.getShippingAddress() != null) {
            order.setShippingAddress(updateRequest.getShippingAddress());
        }
        if (updateRequest.getStatus() != null) {
            order.setStatus(updateRequest.getStatus());
        }

        Order updatedOrder = orderRepository.save(order);
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderOrderId(orderId);
        return mapToOrderResponse(updatedOrder, orderDetails);
    }

    @Transactional
    public void deleteOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderDetailRepository.deleteAll(orderDetailRepository.findByOrderOrderId(orderId));
        orderRepository.delete(order);
    }

    private OrderResponse mapToOrderResponse(Order order, List<OrderDetail> orderDetails) {
        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getOrderId()); // Updated to orderID
        response.setCustomerId(order.getMember().getCustomerId()); // Updated to memberID and customerID
        response.setTotal(order.getTotal().doubleValue()); // Convert BigDecimal to double for DTO
        response.setShippingAddress(order.getShippingAddress());
        response.setOrderStatus(order.getStatus());
        response.setOrderDetails(orderDetails.stream().map(this::mapToOrderDetailDTO).collect(Collectors.toList()));
        return response;
    }

    private OrderDetailDTO mapToOrderDetailDTO(OrderDetail detail) {
        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setProductId(detail.getProduct().getProductID()); // Updated to productID
        dto.setQuantity(detail.getQuantity());
        return dto;
    }
}