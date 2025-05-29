package io.samsquamptch.afterclass.repositories;

import io.samsquamptch.afterclass.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository <Group, Long> {
    boolean existsByPassCode(String passCode);

    Optional<Group> findByPassCode(String passCode);
}
