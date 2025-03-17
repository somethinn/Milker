package PRM392.demo.controller;

import PRM392.demo.dto.LoginRequest;
import PRM392.demo.dto.LoginResponse;
import PRM392.demo.dto.RegisterRequest;
import PRM392.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse response = authService.login(loginRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        try {
            authService.register(registerRequest);
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // Placeholder: Add session/JWT invalidation later if needed
        return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
    }
}
