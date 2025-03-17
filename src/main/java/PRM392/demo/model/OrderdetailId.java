package PRM392.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class OrderdetailId implements Serializable {
    @Serial
    private static final long serialVersionUID = 7288349119811558651L;
    @Column(name = "ProductID", nullable = false)
    private String productID;

    @Column(name = "OrderID", nullable = false)
    private String orderID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderdetailId entity = (OrderdetailId) o;
        return Objects.equals(this.productID, entity.productID) &&
                Objects.equals(this.orderID, entity.orderID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, orderID);
    }

}