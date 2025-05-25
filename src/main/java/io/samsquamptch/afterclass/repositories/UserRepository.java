package io.samsquamptch.afterclass.repositories;

import io.samsquamptch.afterclass.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByGroupId(Long groupId);

    boolean existsByIdAndGroupId(Long userId, Long groupId);
}
