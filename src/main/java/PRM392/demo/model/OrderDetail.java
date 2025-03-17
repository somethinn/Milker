package PRM392.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "orderdetail")
public class OrderDetail {
    @EmbeddedId
    private OrderdetailId id;

    @MapsId("productID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ProductID", nullable = false)
    private Product productID;

    @MapsId("orderID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "OrderID", nullable = false)
    private Order orderID;

    @ColumnDefault("1")
    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "RegistrationDate")
    private Instant registrationDate;

    @Column(name = "CreateDate")
    private Instant createDate;

    @Column(name = "UpdateDate")
    private Instant updateDate;

}