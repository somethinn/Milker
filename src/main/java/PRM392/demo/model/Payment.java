package PRM392.demo.model;

import jakarta.persistence.*;
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
@Table(name = "payment")
public class Payment {
    @Id
    @Column(name = "PaymentID", nullable = false)
    private String paymentID;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "OrderID")
    private Order orderID;

    @Column(name = "Amount", precision = 10, scale = 2)
    private BigDecimal amount;

    @ColumnDefault("0")
    @Column(name = "PaymentStatus")
    private Boolean paymentStatus;

    @Column(name = "PaymentMethod")
    private String paymentMethod;

    @Column(name = "CreateDate")
    private Instant createDate;

    @Column(name = "UpdateDate")
    private Instant updateDate;

}