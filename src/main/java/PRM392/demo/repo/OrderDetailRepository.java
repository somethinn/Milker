package PRM392.demo.repo;

import PRM392.demo.model.OrderDetail;
import PRM392.demo.model.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
}
