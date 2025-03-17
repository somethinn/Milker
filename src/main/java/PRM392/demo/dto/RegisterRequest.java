package PRM392.demo.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String userName;
    private String password;
}