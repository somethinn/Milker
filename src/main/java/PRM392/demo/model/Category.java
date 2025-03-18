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
@Table(name = "categories")
public class Category {
    @Id
    @Size(max = 255)
    @Column(name = "category_id", nullable = false)
    private String categoryId;

    @Size(max = 255)
    @NotNull
    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

}