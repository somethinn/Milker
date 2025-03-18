package PRM392.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "\"Role\"")
public class Role {
    @Id
    @Size(max = 255)
    @Column(name = "\"RoleID\"", nullable = false)
    private String roleID;

    @Size(max = 255)
    @NotNull
    @ColumnDefault("'N/A'")
    @Column(name = "\"RoleName\"", nullable = false)
    private String roleName;

}