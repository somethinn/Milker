package PRM392.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "\"Product\"")
public class Product {
    @Id
    @Size(max = 255)
    @Column(name = "\"ProductID\"", nullable = false)
    private String productID;

    @Size(max = 255)
    @Column(name = "\"ProductName\"")
    private String productName;

    @ColumnDefault("0")
    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Size(max = 255)
    @Column(name = "\"Description\"")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "\"CategoriesID\"")
    private Category categoriesID;

    @Size(max = 255)
    @Column(name = "\"statusDescription\"")
    private String statusDescription;

    @Column(name = "image", length = Integer.MAX_VALUE)
    private String image;

    @Column(name = "\"CreateDate\"")
    private Instant createDate;

    @Column(name = "\"UpdateDate\"")
    private Instant updateDate;

}