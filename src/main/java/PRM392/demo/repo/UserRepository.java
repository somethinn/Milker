package PRM392.demo.repo;

import PRM392.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUserName(String userName); // For login

    User findByEmail(String email);       // For registration validation
}