package PRM392.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "users") // Fixed: Lowercase, no quotes
public class User {
    @Id
    @Size(max = 255)
    @NotNull
    @Column(name = "customer_id", nullable = false)
    private String customerId; // Adjusted field name to camelCase

    @Size(max = 255)
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 255)
    @Column(name = "last_name")
    private String lastName;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 20) // Adjusted to match database VARCHAR(20)
    @Column(name = "phone_number")
    private String phoneNumber;

    @Size(max = 50) // Adjusted to match database VARCHAR(50)
    @NotNull
    @Column(name = "user_name", nullable = false) // Fixed: Lowercase, no quotes
    private String username; // Adjusted field name to camelCase

    @Size(max = 255)
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "role_id")
    private Role roleId; // Adjusted field name to camelCase
}