package PRM392.demo.repo;

import PRM392.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByQuantityGreaterThan(int quantity);
    Page<Product> findByQuantityGreaterThan(int quantity, Pageable pageable); // To list available products
}
