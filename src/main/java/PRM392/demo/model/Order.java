package PRM392.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "\"Orders\"")
public class Order {
    @Id
    @Size(max = 255)
    @Column(name = "\"OrderID\"", nullable = false)
    private String orderID;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "\"MemberID\"")
    private User memberID;

    @NotNull
    @Column(name = "\"Total\"", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Size(max = 255)
    @Column(name = "\"ShippingAddress\"")
    private String shippingAddress;

    @ColumnDefault("false")
    @Column(name = "\"OrderStatus\"")
    private Boolean orderStatus;

    @Column(name = "\"CreateDate\"")
    private Instant createDate;

    @Column(name = "\"UpdateDate\"")
    private Instant updateDate;

}