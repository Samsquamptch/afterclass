package io.samsquamptch.afterclass.repositories;

import io.samsquamptch.afterclass.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
