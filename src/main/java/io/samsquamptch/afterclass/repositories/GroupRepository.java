package io.samsquamptch.afterclass.repositories;

import io.samsquamptch.afterclass.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository <Group, Long> {
}
