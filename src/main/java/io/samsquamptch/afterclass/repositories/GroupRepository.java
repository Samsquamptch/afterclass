package io.samsquamptch.afterclass.repositories;

import io.samsquamptch.afterclass.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository <Group, Long> {

    Group findByPassCode(String passCode);

    Group findGroupById(long id);
}
