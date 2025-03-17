package PRM392.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "CategoriesID", nullable = false)
    private String categoriesID;

    @Column(name = "BrandName")
    private String brandName;

    @Column(name = "AgeRange")
    private String ageRange;

    @Column(name = "SubCategories")
    private String subCategories;

    @Column(name = "packageType")
    private String packageType;

    @Column(name = "source")
    private String source;

    @Column(name = "CreateDate")
    private Instant createDate;

    @Column(name = "UpdateDate")
    private Instant updateDate;

}