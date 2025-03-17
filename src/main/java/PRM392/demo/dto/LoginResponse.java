package PRM392.demo.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String customerId;
    private String userName;
    private String roleName;
}