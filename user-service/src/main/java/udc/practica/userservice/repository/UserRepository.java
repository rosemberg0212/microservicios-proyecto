package udc.practica.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udc.practica.userservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
