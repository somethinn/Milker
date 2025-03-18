package PRM392.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "\"Categories\"")
public class Category {
    @Id
    @Size(max = 255)
    @Column(name = "\"CategoriesID\"", nullable = false)
    private String categoriesID;

    @Size(max = 255)
    @Column(name = "\"BrandName\"")
    private String brandName;

    @Size(max = 255)
    @Column(name = "\"AgeRange\"")
    private String ageRange;

    @Size(max = 255)
    @Column(name = "\"SubCategories\"")
    private String subCategories;

    @Size(max = 255)
    @Column(name = "\"packageType\"")
    private String packageType;

    @Size(max = 255)
    @Column(name = "source")
    private String source;

    @Column(name = "\"CreateDate\"")
    private Instant createDate;

    @Column(name = "\"UpdateDate\"")
    private Instant updateDate;

}