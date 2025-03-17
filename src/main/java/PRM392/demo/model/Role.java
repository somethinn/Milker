package PRM392.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "RoleID", nullable = false)
    private String roleID;

    @Column(name = "RoleName", nullable = false)
    private String roleName;

}