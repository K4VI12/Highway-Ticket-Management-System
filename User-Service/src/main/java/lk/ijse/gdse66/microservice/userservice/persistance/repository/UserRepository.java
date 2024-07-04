package lk.ijse.gdse66.microservice.userservice.persistance.repository;

import lk.ijse.gdse66.microservice.userservice.persistance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findByUserNic(String nic);
    Boolean existsByUserNic(String nic);
    void deleteByUserNic(String nic);
}
