package PRM392.demo.service;

import PRM392.demo.dto.LoginRequest;
import PRM392.demo.dto.LoginResponse;
import PRM392.demo.dto.RegisterRequest;
import PRM392.demo.model.Role;
import PRM392.demo.model.User;
import PRM392.demo.repo.RoleRepository;
import PRM392.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUserName(loginRequest.getUserName());
        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        LoginResponse response = new LoginResponse();
        response.setCustomerId(user.getCustomerID()); // Updated to customerID
        response.setUserName(user.getUserName());
        response.setRoleName(user.getRoleID() != null ? user.getRoleID().getRoleName() : "USER");
        return response;
    }

    public void register(RegisterRequest registerRequest) {
        // Check if username or email already exists
        if (userRepository.findByUserName(registerRequest.getUserName()) != null) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.findByEmail(registerRequest.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }

        // Create new user
        User user = new User();
        user.setCustomerID(UUID.randomUUID().toString()); // Updated to customerID
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setUserName(registerRequest.getUserName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        // Assign default role (e.g., "USER")
        Role role = roleRepository.findByRoleName("USER");
        if (role == null) {
            role = new Role();
            role.setRoleID(UUID.randomUUID().toString());
            role.setRoleName("USER");
            roleRepository.save(role);
        }
        user.setRoleID(role); // Updated to roleID

        userRepository.save(user);
    }

    // Logout can be implemented later with sessions/JWT
}