package PRM392.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class OrderDetailId implements Serializable {
    @Serial
    private static final long serialVersionUID = 4792247457533079660L;
    @Size(max = 255)
    @NotNull
    @Column(name = "\"ProductID\"", nullable = false)
    private String productID;

    @Size(max = 255)
    @NotNull
    @Column(name = "\"OrderID\"", nullable = false)
    private String orderID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderDetailId entity = (OrderDetailId) o;
        return Objects.equals(this.productID, entity.productID) &&
                Objects.equals(this.orderID, entity.orderID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, orderID);
    }

}