package io.samsquamptch.afterclass.dto;

import io.samsquamptch.afterclass.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    private Group group;

}
