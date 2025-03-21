package PRM392.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
    @Id
    @Size(max = 255)
    @Column(name = "role_id", nullable = false)
    private String roleID;

    @Size(max = 255)
    @NotNull
    @Column(name = "role_name", nullable = false)
    private String roleName;

}