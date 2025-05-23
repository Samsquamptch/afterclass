package io.samsquamptch.afterclass.repositories;

import io.samsquamptch.afterclass.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(long id);

    boolean existsByIdAndGroupId(Long userId, Long groupId);
}
