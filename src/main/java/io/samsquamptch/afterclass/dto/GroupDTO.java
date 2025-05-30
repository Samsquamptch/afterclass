package io.samsquamptch.afterclass.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {
    private long id;
    private String name;
    private String passCode;
    private List<UserDTO> users;
}
