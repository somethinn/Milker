package PRM392.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "\"Users\"")
public class User {
    @Id
    @Size(max = 255)
    @Column(name = "\"CustomerID\"", nullable = false)
    private String customerID;

    @Size(max = 255)
    @Column(name = "\"FirstName\"")
    private String firstName;

    @Size(max = 255)
    @Column(name = "\"LastName\"")
    private String lastName;

    @Size(max = 255)
    @NotNull
    @Column(name = "\"Email\"", nullable = false)
    private String email;

    @Column(name = "\"PhoneNumber\"", length = Integer.MAX_VALUE)
    private String phoneNumber;

    @Size(max = 255)
    @NotNull
    @ColumnDefault("'N/A'")
    @Column(name = "\"UserName\"", nullable = false)
    private String userName;

    @Size(max = 255)
    @NotNull
    @Column(name = "\"Password\"", nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "\"RoleID\"")
    private Role roleID;

}